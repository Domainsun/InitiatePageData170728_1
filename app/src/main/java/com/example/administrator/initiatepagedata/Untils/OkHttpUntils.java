package com.example.administrator.initiatepagedata.Untils;

import android.os.Handler;
import android.os.Message;

import com.example.administrator.initiatepagedata.JsonData.ComplexInfoRoot;
import com.example.administrator.initiatepagedata.JsonData.SimInfoRoot;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/4/6.
 */

public class OkHttpUntils {

    MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain");
    private OkHttpClient client;
    public static List<String> stringListcitys=new ArrayList<String>();


    //发送订单信息
    public void get(final String json) throws IOException {
        client=new OkHttpClient();
        new Thread(){
            @Override
            public void run() {
                MultipartBody.Builder requestBody1 = new MultipartBody.Builder().setType(MultipartBody.FORM);
                requestBody1.addFormDataPart("data",json);
                Request request = new Request.Builder()
                        .url("http://120.25.192.181/xiaolv/admin/api/add_activity.php")
                        .post(requestBody1.build())
                        .tag(json)
                        .build();
                Response response = null;
                try {

                    response = client.newCall(request).execute();
                    String  str_result=response.body().string();
                    System.out.println("发送下单页面结果:"+str_result);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void getjson(final Handler handler, final int i) throws IOException {
        client=new OkHttpClient();
        new Thread(){
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("http://120.25.192.181/xiaolv/admin/api/get_activity.php?listcount="+i)
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    String  str_result=response.body().string();
                    System.out.println("从网页得到的下单json---\n"+str_result);

                    String str="{\n" +
                            "    \"SimpleInfo\": {\n" +
                            "        \"Info\": [\n" +
                            "            {\n" +
                            "                \"_id\": \"54\",\n" +
                            "                \"activityID\": \"2017060486944\",\n" +
                            "                \"title\": \"三张图片测试\",\n" +
                            "                \"price\": \"11\",\n" +
                            "                \"costTime\": \"0.5\",\n" +
                            "                \"destCity\": \"阿坝藏族羌族自治州\",\n" +
                            "                \"destCounty\": \"阿坝县\",\n" +
                            "                \"hasCar\": true,\n" +
                            "                \"photoID1\": \"photo1\",\n" +
                            "                \"activityType\": \"2\"\n" +
                            "            }\n" +
                            "        ]\n" +
                            "    }\n" +
                            "}";

                    String json="{\n" +
                            "    \"SimpleInfo\": {\n" +
                            "        \"Info\": [\n" +
                            "            {\n" +
                            "                \"_id\": \"54\",\n" +
                            "                \"activityID\": \"2017060486944\",\n" +
                            "                \"title\": \"三张图片测试\",\n" +
                            "                \"price\": \"11\",\n" +
                            "                \"costTime\": \"0.5\",\n" +
                            "                \"destCity\": \"阿坝藏族羌族自治州\",\n" +
                            "                \"destCounty\": \"阿坝县\",\n" +
                            "                \"hasCar\": true,\n" +
                            "                \"photoID1\": \"photo1\",\n" +
                            "                \"activityType\": \"2\"\n" +
                            "            },\n" +
                            "            {\n" +
                            "                \"_id\": \"53\",\n" +
                            "                \"activityID\": \"2017060474890\",\n" +
                            "                \"title\": \"五张图片测试\",\n" +
                            "                \"price\": \"11\",\n" +
                            "                \"costTime\": \"0.5\",\n" +
                            "                \"destCity\": \"阿坝藏族羌族自治州\",\n" +
                            "                \"destCounty\": \"阿坝县\",\n" +
                            "                \"hasCar\": true,\n" +
                            "                \"photoID1\": \"photo1\",\n" +
                            "                \"activityType\": \"2\"\n" +
                            "            },\n" +
                            "            {\n" +
                            "                \"_id\": \"1\",\n" +
                            "                \"activityID\": \"201705059999900001\",\n" +
                            "                \"title\": \"赣州一日游\",\n" +
                            "                \"price\": \"99\",\n" +
                            "                \"costTime\": \"1\",\n" +
                            "                \"destCity\": \"赣州市\",\n" +
                            "                \"destCounty\": \"章贡区\",\n" +
                            "                \"hasCar\": true,\n" +
                            "                \"photoID1\": \"照片1\",\n" +
                            "                \"activityType\": \"0\"\n" +
                            "            }\n" +
                            "        ]\n" +
                            "    }\n" +
                            "}";

                    System.out.println("自己写的json:"+json);

//                    Pattern p = Pattern.compile("\\s*|\t|\r|\n");
//                    Matcher m = p.matcher(str);
//                    str_result = m.replaceAll("");

                    SimInfoRoot s=new GsonUntils().SimpleTJO(str_result);
//
                    Message message=Message.obtain();
                    message.obj=s;
                    message.what=1;
                    handler.sendMessage(message);
//                    System.out.println("解析后是：---\n");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    public void getComplexInfo(final Handler handler, final String i) throws IOException {
        client=new OkHttpClient();
        new Thread(){
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("http://120.25.192.181/xiaolv/admin/api/get_activity_complex.php?id="+i)
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    String  str_result=response.body().string();
                    System.out.println("从网页得到的complex json---\n"+str_result);


                    ComplexInfoRoot c=new GsonUntils().ComplexTJO(str_result);
//
                    Message message=Message.obtain();
                    message.obj=c;
                    message.what=1;
                    handler.sendMessage(message);
//                    System.out.println("解析后是：---\n");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //获取订单信息
    public void getorderjson(/*final Handler handler*/) throws IOException {
        client=new OkHttpClient();
        new Thread(){
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("http://120.25.192.181/xiaolv/admin/api/get_activity.php")
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    String  str_result=response.body().string();
                    System.out.println("从网页得到订单的json---\n"+str_result);
//                    OrderJsonData o=new GsonUntils().toJO(str_result);


//                    Message message=Message.obtain();
////                    message.obj=o;
//                    handler.sendMessage(message);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }




    public void  getcounty(final String city, final Handler handler) throws IOException {
        client=new OkHttpClient();

        new Thread(){
            @Override
            public void run() {
                String key="e538686c33d65e6851a11b2276f245a5";
                String cityname= URLEncoder.encode(city);
                Request request = new Request.Builder()
                        .url("http://restapi.amap.com/v3/config/district?key="+key+"&keywords="+cityname+"&subdistrict=1&output=JSON")
                        .build();
                Response response = null;
                try {

                    response = client.newCall(request).execute();

                    String  str_result=response.body().string();
                    System.out.println("+++"+response.toString());
                    System.out.println("返回的城市信息:"+str_result);
                    List<String> county=new GsonUntils().getcityjava(str_result);
                  Message message=Message.obtain();
                    message.obj=county;
                    message.what =1;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }



    public void  getcounty1(final String city, final Handler handler) throws IOException {
        client=new OkHttpClient();

        new Thread(){
            @Override
            public void run() {
                String key="e538686c33d65e6851a11b2276f245a5";
                String cityname= URLEncoder.encode(city);
                Request request = new Request.Builder()
                        .url("http://restapi.amap.com/v3/config/district?key="+key+"&keywords="+cityname+"&subdistrict=1&output=JSON")
                        .build();
                Response response = null;
                try {

                    response = client.newCall(request).execute();

                    String  str_result=response.body().string();
                    System.out.println("+++"+response.toString());
                    System.out.println("返回的城市信息:"+str_result);
                    List<String> county=new GsonUntils().getcityjava(str_result);
                  Message message=Message.obtain();
                    message.obj=county;
                    message.what =5;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }



    //得的中国所有城市

    public void  getcity(final String city, final Handler handler) throws IOException {
        client=new OkHttpClient();

        new Thread(){
            @Override
            public void run() {
                String key="e538686c33d65e6851a11b2276f245a5";
                String cityname= URLEncoder.encode(city);
                Request request = new Request.Builder()
                        .url("http://restapi.amap.com/v3/config/district?key="+key+"&keywords="+cityname+"&subdistrict=2&output=JSON")
                        .build();
                Response response = null;
                try {

                    response = client.newCall(request).execute();
                    String  str_result=response.body().string();
                    System.out.println("返回的城市信息:"+str_result);
                    List<String> citys=new GsonUntils().getcity(str_result);
                    Message message=Message.obtain();
                    message.obj=citys;
                    message.what =4;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }



    public void  getlocation(final String Latitude, final String Longitude, final Handler handler) throws IOException {
        client=new OkHttpClient();

        new Thread(){
            @Override
            public void run() {
                String key="e538686c33d65e6851a11b2276f245a5";

                Request request = new Request.Builder()
                        .url("http://restapi.amap.com/v3/geocode/regeo?output=json&location="+Latitude+","+Longitude+"&key="+key)
                        .build();
                Response response = null;
                try {

                    response = client.newCall(request).execute();

                    String  str_result=response.body().string();
                    System.out.println("经纬度查询返回的信息:"+str_result);
                    List<String> location=new ArrayList<String>();
                    location=new GsonUntils().getlocationjava(str_result);
                    System.out.println("--okhttpuntils--城市:"+location.get(0)+location.get(1));
                    Message message=Message.obtain();
                    message.obj=location;
                    //2  发送 当前位置的城市 区县
                    message.what =3;
                    handler.sendMessage(message);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
