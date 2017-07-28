package com.example.administrator.initiatepagedata;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.example.administrator.initiatepagedata.HttpUtils.GetActivitySimpleInfoThread;
import com.example.administrator.initiatepagedata.JsonData.ActivitySimple;
import com.example.administrator.initiatepagedata.JsonData.JsonAnimalsBoot;
import com.example.administrator.initiatepagedata.JsonData.SimInfoRoot;
import com.example.administrator.initiatepagedata.Untils.GsonUntils;
import com.example.administrator.initiatepagedata.Untils.ImageService;
import com.example.administrator.initiatepagedata.Untils.OssService;
import com.example.administrator.initiatepagedata.Untils.STSGetter;
import com.example.administrator.initiatepagedata.Untils.UIDisplayer;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FoundActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView lv_simple;
    private List<ActivitySimple> mData;
    private Button btnrefresh;
    Handler mhandler;
    private UIDisplayer UIDisplayer1;
    private ImageService imageService1;
    private OssService ossService1;
    ImageView imageView1;


    private static final String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
    private static final String imgEndpoint = "http://img-cn-shenzhen.aliyuncs.com";
    private static final String bucket = "timor";
    String text="南阳小旅";

    Bitmap bm[]=new Bitmap[5];

    int size=50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);

        init();
        event();


    }

    private void init() {

        lv_simple= (ListView) findViewById(R.id.lview_show);
        btnrefresh= (Button) findViewById(R.id.btn_refresh);

//        LayoutInflater factory = LayoutInflater.from(FoundActivity.this);
//        View layout = factory.inflate(R.layout.item_simple, null);
//        imageView1= (ImageView) layout.findViewById(R.id.iv_1_1);
        imageView1= (ImageView) this.findViewById(R.id.iv11);

        UIDisplayer1= new UIDisplayer(imageView1,this);
        ossService1 = initOSS(imgEndpoint, bucket, UIDisplayer1);

        imageService1 = new ImageService(ossService1);


        mhandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what==1) {
                    SimInfoRoot s= (SimInfoRoot) msg.obj;
//                    List<SimpleInfo> costTime= (List<SimpleInfo>) s.getSimpleInfo();
//                    System.out.println("需用时:"+costTime);


                    imageService1.textWatermark((s.getSimpleInfo().getInfo().get(0).getPhotoID1()),text,size);
                    Toast.makeText(FoundActivity.this,s.getSimpleInfo().getInfo().get(0).getPhotoID1(), Toast.LENGTH_SHORT).show();

                    Bitmap bm = ossService1.getBm();

                    System.out.println("FoundAcitivity-----:"+bm);

                    mData=initData(s);
                    LayoutInflater inflater=getLayoutInflater();
                    InfoAdapter infoAdapter = new InfoAdapter(inflater,mData);
                    lv_simple.setAdapter(infoAdapter);
                }

            }
        };


        /*2017年7月28日10:37:51改 用返回值线程做，上面的handler暂时不用*/

        String GetActivityThreadResult=null;
        GetActivitySimpleInfoThread tGetActivitySimpleInfo = new GetActivitySimpleInfoThread("1");
        FutureTask<String> ftGetActivitySimpleInfo = new FutureTask<>(tGetActivitySimpleInfo);
        Thread GetActivityThread = new Thread(ftGetActivitySimpleInfo);
        GetActivityThread.start();

        try {
            GetActivityThreadResult=ftGetActivitySimpleInfo.get();
            System.out.println(GetActivityThreadResult);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        SimInfoRoot s=new GsonUntils().SimpleTJO(GetActivityThreadResult);

        imageService1.textWatermark((s.getSimpleInfo().getInfo().get(0).getPhotoID1()),text,size);
        Toast.makeText(FoundActivity.this,s.getSimpleInfo().getInfo().get(0).getPhotoID1(), Toast.LENGTH_SHORT).show();

        mData=initData(s);
        LayoutInflater inflater=getLayoutInflater();
        InfoAdapter infoAdapter = new InfoAdapter(inflater,mData);
        lv_simple.setAdapter(infoAdapter);





//        json();
//        try {
//            new OkHttpUntils().getjson(mhandler,1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
    private void event() {
        btnrefresh.setOnClickListener(this);
        lv_simple.setOnItemClickListener(this);
    }


    private List<ActivitySimple> initData(SimInfoRoot s) {
        mData=new ArrayList<ActivitySimple>();

        for (int i=0;i<s.getSimpleInfo().getInfo().size();i++) {
            ActivitySimple as=new ActivitySimple(null,"城市:"+s.getSimpleInfo().getInfo().get(i).getCostTime(),"活动用时:"+s.getSimpleInfo().getInfo().get(i).getCostTime(),"价格:"+s.getSimpleInfo().getInfo().get(i).getPrice(), "简介:","标题:"+s.getSimpleInfo().getInfo().get(i).getTitle(),"有无车:"+s.getSimpleInfo().getInfo().get(i).getHasCar(),s.getSimpleInfo().getInfo().get(i).getActivityID());
            mData.add(as);

        }

//        mData.addAll(this.mData);
        return mData;
    }


    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_refresh:

                    refresh();

                    break;
            }
    }

    int i=2;
    public void refresh(){

        String GetActivityThreadResult=null;
        GetActivitySimpleInfoThread tGetActivitySimpleInfo = new GetActivitySimpleInfoThread(String.valueOf(i));
        FutureTask<String> ftGetActivitySimpleInfo = new FutureTask<>(tGetActivitySimpleInfo);
        Thread GetActivityThread = new Thread(ftGetActivitySimpleInfo);
        GetActivityThread.start();

        try {
            GetActivityThreadResult=ftGetActivitySimpleInfo.get();
            System.out.println(GetActivityThreadResult);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        SimInfoRoot s=new GsonUntils().SimpleTJO(GetActivityThreadResult);
        imageService1.textWatermark((s.getSimpleInfo().getInfo().get(0).getPhotoID1()),text,size);
        Toast.makeText(FoundActivity.this,s.getSimpleInfo().getInfo().get(0).getPhotoID1(), Toast.LENGTH_SHORT).show();
        mData=initData(s);
        LayoutInflater inflater=getLayoutInflater();
        InfoAdapter infoAdapter = new InfoAdapter(inflater,mData);
        lv_simple.setAdapter(infoAdapter);

        i++;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String activityID=mData.get(position).getActivityID();
        Toast.makeText(this, activityID, Toast.LENGTH_SHORT).show();
        Intent i=new Intent(FoundActivity.this,ComplexActivity.class);
        i.putExtra("aid",activityID);
        startActivity(i);

    }

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


    public void json(){
        String json="{\"animals\":{\"dog\":[{\"name\":\"Rufus\",\"breed\":\"labrador\",\"count\":1,\"twoFeet\":false},{\"name\":\"Marty\",\"breed\":\"whippet\",\"count\":1,\"twoFeet\":false}]}}";
        JsonAnimalsBoot a= new JsonAnimalsBoot();
        Gson gson=new Gson();
        a=gson.fromJson(json,JsonAnimalsBoot.class);
        System.out.println("名字："+a.getAnimals().getDog().get(0).getName());;


    }
}
