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

public class GetMatchCodeThread implements Callable {

    String phone,action;

    public GetMatchCodeThread(String phone, String action) {
        this.phone = phone;
        this.action = action;
    }


    @Override
    public Object call() throws Exception {
        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("phone", phone)
                .add("action", action)
                .build();
        Request request = new Request.Builder()
                .url("http://120.25.192.181/xiaolv/admin/api/send_message.php")
                                .post(formBody)
                                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        Headers responseHeaders = response.headers();
        String cookie=responseHeaders.get("Set-Cookie");
        System.out.println("cookie:"+cookie);

        String Result=response.body().string();
        System.out.println("GetMatchCodeThread Result-------------:"+Result);

        return cookie;


    }

}
