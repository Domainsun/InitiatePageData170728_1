package com.example.administrator.initiatepagedata.Untils;
/**
 * 2017/04/24      @author is zhichu
 * 使用图片服务处理图片
 */
import android.util.Base64;
import android.util.Log;


public class ImageService {
    private OssService ossService;
    //字体，默认文泉驿正黑，可以根据文档自行更改
    private static final String font = "d3F5LXplbmhlaQ==";

    public ImageService(OssService ossService) {
        this.ossService = ossService;
    }

    //给图片打上文字水印，除了大小字体之外其他都是默认值，有需要更改的可以参考图片服务文档自行调整
    public void  textWatermark(String object, String text, int size) {
        String base64Text = Base64.encodeToString(text.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);
        String queryString = "@400w|watermark=2&type=" + font + "&text=" + base64Text + "&size=" + String.valueOf(size);
        System.out.println("下载地址:"+queryString);

//        String base64Object = Base64.encodeToString(object.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);
//        System.out.println("base64Object---------:"+base64Object);
        ossService.asyncGetImage(object+queryString);

    }

    //强制缩放，其他缩放方式可以参考图片服务文档
    public void resize(String object, int width, int height) {
        String queryString = "@" + String.valueOf(width) + "w_" + String.valueOf(height) + "h_1e_1c";

        Log.d("ResizeImage", object);
        Log.d("Width", String.valueOf(width));
        Log.d("Height", String.valueOf(height));
        Log.d("QueryString", queryString);

        ossService.asyncGetImage(object + queryString);
    }



}
