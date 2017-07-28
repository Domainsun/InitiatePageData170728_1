package com.example.administrator.initiatepagedata.Untils;
/**
 * 2017/04/24      @author is zhichu
 * 支持普通上传，普通下载
 */
import android.graphics.Bitmap;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;


public class OssService {

    private OSS oss;
    private String bucket;
    private UIDisplayer UIDisplayer;
    private String callbackAddress;
    //根据实际需求改变分片大小
    private Bitmap bm;

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    public OssService(OSS oss, String bucket, UIDisplayer UIDisplayer) {
        this.oss = oss;
        this.bucket = bucket;
        this.UIDisplayer = UIDisplayer;
    }

    public void SetBucketName(String bucket) {
        this.bucket = bucket;
    }

    public void InitOss(OSS _oss) {
        this.oss = _oss;
    }

    public void setCallbackAddress(String callbackAddress) {
        this.callbackAddress = callbackAddress;
    }

    public void asyncGetImage(String object) {
        if ((object == null) || object.equals("")) {
            Log.w("AsyncGetImage", "ObjectNull");
//            return null;
        }

        GetObjectRequest get = new GetObjectRequest(bucket, object);

        OSSAsyncTask task = oss.asyncGetObejct(get, new OSSCompletedCallback<GetObjectRequest, GetObjectResult>() {
            @Override
            public void onSuccess(GetObjectRequest request, GetObjectResult result) {
                // 请求成功
                InputStream inputStream = result.getObjectContent();
                //重载InputStream来获取读取进度信息
                ProgressInputStream progressStream = new ProgressInputStream(inputStream, new OSSProgressCallback<GetObjectRequest>() {
                    @Override
                    public void onProgress(GetObjectRequest o, long currentSize, long totalSize) {
                        Log.d("GetObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                        int progress = (int) (100 * currentSize / totalSize);
                        UIDisplayer.updateProgress(progress);
                        UIDisplayer.displayInfo("下载进度: " + String.valueOf(progress) + "%");
                        System.out.println("下载进度:"+String.valueOf(progress) + "%");
                    }
                }, result.getContentLength());

                try {
                    //需要根据对应的View大小来自适应缩放
                    bm = UIDisplayer.autoResizeFromStream(progressStream);
                    System.out.println("ossService-----:"+bm);
                    UIDisplayer.downloadComplete(bm);
                    UIDisplayer.displayInfo("Bucket: " + bucket + "\nObject: " + request.getObjectKey() + "\nRequestId: " + result.getRequestId());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(GetObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                String info = "";
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                    info = clientExcepion.toString();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                    info = serviceException.toString();
                }
                UIDisplayer.downloadFail(info);
                UIDisplayer.displayInfo(info);
            }
        });
    }


    public void asyncPutImage(String object, String localFile) {
        if (object.equals("")) {
            Log.w("AsyncPutImage", "ObjectNull");
            return;
        }

        File file = new File(localFile);

        if (!file.exists()) {
            Log.w("AsyncPutImage", "FileNotExist");
            Log.w("LocalFile", localFile);
            return;
        }


        // 构造上传请求
        final PutObjectRequest put = new PutObjectRequest(bucket, object, localFile);

        if (callbackAddress != null) {
            // 传入对应的上传回调参数

            put.setCallbackParam(new HashMap<String, String>() {
                {
                    put("callbackUrl", "http://120.25.192.181/sts-server/sts.php");
                    put("callbackHost", "oss-cn-shenzhen.aliyuncs.com");
                    put("callbackBodyType", "application/json");
                    put("callbackBody", "{\"object\":${object},\"size\":${size},\"my_var1\":${x:var1},\"my_var2\":${x:var2}}");
                }
            });
            put.setCallbackVars(new HashMap<String, String>() {
                {
                   put( "callbackUrl","121.43.113.8:23456/index.html");
                       put( "callbackBody","bucket=${bucket}&object=${object}&etag=${etag}&size=${size}" +
                               "&mimeType=${mimeType}&imageInfo.height=${imageInfo.height}&imageInfo.width="+
                               "${imageInfo.width}&imageInfo.format=${imageInfo.format}&my_var=${x:my_var}");

                }
            });
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                int progress = (int) (100 * currentSize / totalSize);
                UIDisplayer.updateProgress(progress);
                UIDisplayer.displayInfo("上传进度: " + String.valueOf(progress) + "%");
            }
        });

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");

                Log.d("ETag", result.getETag());
                Log.d("RequestId", result.getRequestId());

                UIDisplayer.uploadComplete();
                UIDisplayer.displayInfo("Bucket: " + bucket
                        + "\nObject: " + request.getObjectKey()
                        + "\nETag: " + result.getETag()
                        + "\nRequestId: " + result.getRequestId()
                        + "\nCallback: " + result.getServerCallbackReturnBody());
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                String info = "";
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                    info = clientExcepion.toString();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                    info = serviceException.toString();
                }
                UIDisplayer.uploadFail(info);
                UIDisplayer.displayInfo(info);
            }
        });
      }

    }

}
