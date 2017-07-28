package com.example.administrator.initiatepagedata.HttpUtils;

import java.io.IOException;
import java.util.concurrent.Callable;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/28.
 */

public class UserAuthThread implements Callable {

    String type,message,photo1,photo2,photo3;

    public UserAuthThread(String type, String message, String photo1, String photo2, String photo3) {
        this.type = type;
        this.message = message;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
    }

    @Override
    public Object call() throws Exception {
        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("type", type)
                .add("message", message)
                .add("photo1", photo1)
                .add("photo2", photo2)
                .add("photo3", photo3)
                .build();
        Request request = new Request.Builder()
                .url("http://120.25.192.181/xiaolv/admin/api/add_userauthentication.php")
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        String Result=response.body().string();
        System.out.println("UserAuthThread Result-------------:"+Result);
        return Result;
    }
}
