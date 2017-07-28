package com.example.administrator.initiatepagedata.HttpUtils;

import java.io.IOException;
import java.util.concurrent.Callable;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/28.
 */

public class SignThread implements Callable {

    String name,pw;

    public SignThread(String name, String pw) {
        this.name = name;
        this.pw = pw;
    }

    @Override
    public Object call() throws Exception {
        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("name", name)
                .add("pw", pw)
                .build();
        Request request = new Request.Builder()
                .url("http://120.25.192.181/xiaolv/admin/api/login.php")
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);


        Headers responseHeaders = response.headers();
        String cookie=responseHeaders.get("Set-Cookie");
        System.out.println("cookie:"+cookie);


        String Result=response.body().string();
        System.out.println("SignThread Result-------------:"+Result);
        return cookie;

    }
}
