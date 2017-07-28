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

public class ApplyActivityThread implements Callable {


    String activityid;
    String cookie;

    public ApplyActivityThread(String activityid,String cookie) {
        this.activityid = activityid;
        this.cookie = cookie;
    }

    @Override
    public Object call() throws Exception {
        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("activityid", activityid)
                .build();
        Request request = new Request.Builder()
                .url("http://120.25.192.181/xiaolv/admin/api/add_userinactivity.php")
                .post(formBody)
                .addHeader("cookie",cookie)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        String Result=response.body().string();
        System.out.println("ApplyActivityThread Result-------------:"+Result);
        return Result;
    }
}
