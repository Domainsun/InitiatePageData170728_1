package com.example.administrator.initiatepagedata;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.example.administrator.initiatepagedata.HttpUtils.AddActivityThread;
import com.example.administrator.initiatepagedata.HttpUtils.SignThread;
import com.example.administrator.initiatepagedata.Untils.GsonUntils;
import com.example.administrator.initiatepagedata.Untils.ImageService;
import com.example.administrator.initiatepagedata.Untils.OssService;
import com.example.administrator.initiatepagedata.Untils.STSGetter;
import com.example.administrator.initiatepagedata.Untils.UIDisplayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.administrator.initiatepagedata.MainActivity.aif;


/**
 * Created by Administrator on 2017/5/6.
 */

public class Acitivity_main1 extends Activity implements View.OnClickListener {

    Button btn_choosePicture, btn_back, btn_commit;
    EditText edt_details;


    private static final String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
    private static final String imgEndpoint = "http://img-cn-shenzhen.aliyuncs.com";
    private static final String callbackAddress = "http://120.25.192.181/sts-server/callback.php";
    private static final String bucket = "timor";
    private static final String stsServer = "http://120.25.192.181/sts-server/sts.php";
    @Bind(R.id.edt_signpNum)
    EditText edtSignpNum;
    @Bind(R.id.edt_signpw)
    EditText edtSignpw;
    @Bind(R.id.btn_sign)
    Button btnSign;
    //负责所有的界面更新
    private UIDisplayer UIDisplayer1, UIDisplayer2, UIDisplayer3, UIDisplayer4, UIDisplayer5;
    //OSS的上传下载
    private OssService ossService1, ossService2, ossService3, ossService4, ossService5;
    private ImageService imageService1, imageService2, imageService3, imageService4, imageService5;
    private String picturePath = "";
    private static final int RESULT_LOAD_IMAGE = 1;

    //初始化一个OssService用来上传下载
    public OssService initOSS(String endpoint, String bucket, UIDisplayer displayer) {
        OSSCredentialProvider credentialProvider;
        //应用服务器的地址
        credentialProvider = new STSGetter();
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider, conf);
        return new OssService(oss, bucket, displayer);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ButterKnife.bind(this);
        init();
        event();
    }

    private void init() {
        btn_back = (Button) findViewById(R.id.last);
        btn_choosePicture = (Button) findViewById(R.id.picture);
        btn_commit = (Button) findViewById(R.id.SubmitAudit);
        edt_details = (EditText) findViewById(R.id.EventDetails);

        final ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        final ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        final ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
        final ImageView imageView4 = (ImageView) findViewById(R.id.imageView4);
        final ImageView imageView5 = (ImageView) findViewById(R.id.imageView5);


        UIDisplayer1 = new UIDisplayer(imageView1, this);
        UIDisplayer2 = new UIDisplayer(imageView2, this);
        UIDisplayer3 = new UIDisplayer(imageView3, this);
        UIDisplayer4 = new UIDisplayer(imageView4, this);
        UIDisplayer5 = new UIDisplayer(imageView5, this);

        ossService1 = initOSS(endpoint, bucket, UIDisplayer1);

        //设置上传的callback地址，目前暂时只支持putObject的回调
        ossService1.setCallbackAddress(callbackAddress);
        //图片服务和OSS使用不同的endpoint，但是可以共用SDK，因此只需要初始化不同endpoint的OssService即可
        imageService1 = new ImageService(initOSS(imgEndpoint, bucket, UIDisplayer1));

        ossService2 = initOSS(endpoint, bucket, UIDisplayer2);
        ossService2.setCallbackAddress(callbackAddress);
        imageService2 = new ImageService(initOSS(imgEndpoint, bucket, UIDisplayer2));
        ossService3 = initOSS(endpoint, bucket, UIDisplayer3);
        ossService3.setCallbackAddress(callbackAddress);
        imageService3 = new ImageService(initOSS(imgEndpoint, bucket, UIDisplayer3));
        ossService4 = initOSS(endpoint, bucket, UIDisplayer4);
        ossService4.setCallbackAddress(callbackAddress);
        imageService4 = new ImageService(initOSS(imgEndpoint, bucket, UIDisplayer4));
        ossService5 = initOSS(endpoint, bucket, UIDisplayer5);
        ossService5.setCallbackAddress(callbackAddress);
        imageService5 = new ImageService(initOSS(imgEndpoint, bucket, UIDisplayer5));
    }

    private void event() {
        btn_back.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
        btn_choosePicture.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.last:
                this.finish();
                break;
            case R.id.picture:
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
                break;
            case R.id.SubmitAudit:
//
//                String jasimple = new GsonUntils().tojson(asimple);
//                String jacomplex = new GsonUntils().tojson1(acomplex);


                double a = Math.random() * 100;
                String aid = "标题" + String.valueOf(a);
                aif.setTitle(aid);

                String str_details = "";
                str_details = edt_details.getText().toString();
                aif.setDetails(str_details);
                String json = new GsonUntils().tojson(aif);


                AddActivityThread tSign = new AddActivityThread(this.signcookie, json);
                FutureTask<String> ftSign = new FutureTask<>(tSign);
                Thread SignThread = new Thread(ftSign);
                SignThread.start();

                try {
                    String registerThreadResult = ftSign.get();
                    System.out.println(registerThreadResult);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


                break;
        }
    }

    Bitmap b1, b2, b3, b4, b5;
    List<String> photos1 = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            Log.d("PickPicture", picturePath);
            cursor.close();
            if (b1 == null) {
                try {
                    b1 = UIDisplayer1.autoResizeFromLocalFile(picturePath);
                    UIDisplayer1.displayImage(b1);
                    File file = new File(picturePath);
                    UIDisplayer1.displayInfo("文件: " + picturePath + "\n大小: " + String.valueOf(file.length()));
                    String objectName1 = "photo1";
                    ossService1.asyncPutImage(objectName1, picturePath);
                    aif.setPhotoID1(objectName1);
                    photos1.add(objectName1);

                } catch (IOException e) {
                    e.printStackTrace();
                    UIDisplayer1.displayInfo(e.toString());
                }
                return;
            } else if (b2 == null) {
                try {
                    b2 = UIDisplayer2.autoResizeFromLocalFile(picturePath);
                    UIDisplayer2.displayImage(b2);
                    File file = new File(picturePath);
                    UIDisplayer2.displayInfo("文件: " + picturePath + "\n大小: " + String.valueOf(file.length()));
                    String objectName1 = "photo2";
                    ossService2.asyncPutImage(objectName1, picturePath);
                    photos1.add(objectName1);
                } catch (IOException e) {
                    e.printStackTrace();
                    UIDisplayer2.displayInfo(e.toString());
                }
                return;
            } else if (b3 == null) {
                try {
                    b3 = UIDisplayer3.autoResizeFromLocalFile(picturePath);
                    UIDisplayer3.displayImage(b3);
                    File file = new File(picturePath);
                    UIDisplayer3.displayInfo("文件: " + picturePath + "\n大小: " + String.valueOf(file.length()));
                    String objectName1 = "photo3";
                    ossService3.asyncPutImage(objectName1, picturePath);
                    photos1.add(objectName1);
                } catch (IOException e) {
                    e.printStackTrace();
                    UIDisplayer3.displayInfo(e.toString());
                }
                return;
            } else if (b4 == null) {
                try {
                    b4 = UIDisplayer4.autoResizeFromLocalFile(picturePath);
                    UIDisplayer4.displayImage(b4);
                    File file = new File(picturePath);
                    UIDisplayer4.displayInfo("文件: " + picturePath + "\n大小: " + String.valueOf(file.length()));
                    String objectName1 = "photo4";
                    ossService4.asyncPutImage(objectName1, picturePath);
                    photos1.add(objectName1);
                } catch (IOException e) {
                    e.printStackTrace();
                    UIDisplayer4.displayInfo(e.toString());
                }
                return;
            }
            if (b5 == null) {
                try {
                    b5 = UIDisplayer5.autoResizeFromLocalFile(picturePath);
                    UIDisplayer5.displayImage(b5);
                    File file = new File(picturePath);
                    UIDisplayer5.displayInfo("文件: " + picturePath + "\n大小: " + String.valueOf(file.length()));
                    String objectName1 = "photo5";
                    ossService5.asyncPutImage(objectName1, picturePath);
                    photos1.add(objectName1);
                } catch (IOException e) {
                    e.printStackTrace();
                    UIDisplayer5.displayInfo(e.toString());
                }
                return;
            }
        }
    }
    String signcookie=null;
    @OnClick(R.id.btn_sign)
    public void onViewClicked() {

        String signPnum=edtSignpNum.getText().toString();
        String signpw=edtSignpw.getText().toString();


        SignThread tSign = new SignThread(signPnum,signpw);
        FutureTask<String> ftSign = new FutureTask<>(tSign);
        Thread SignThread = new Thread(ftSign);
        SignThread.start();

        try {
            signcookie=ftSign.get();
            System.out.println(signcookie);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }





    }
}
