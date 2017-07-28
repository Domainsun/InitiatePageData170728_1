package com.example.administrator.initiatepagedata;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.initiatepagedata.HttpUtils.ApplyActivityThread;
import com.example.administrator.initiatepagedata.HttpUtils.GetAcitivtyComplexInfoThread;
import com.example.administrator.initiatepagedata.HttpUtils.SignThread;
import com.example.administrator.initiatepagedata.JsonData.ComplexInfoRoot;
import com.example.administrator.initiatepagedata.Untils.GsonUntils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComplexActivity extends AppCompatActivity {
    Handler mhandler;
    String aid = null;
    TextView tv_validDuration, tv_departCounty, tv_dealCount, tv_likeCount;
    ImageView iv1, iv2, iv3, iv4, iv5;
    @Bind(R.id.edt_signpNum)
    EditText edtSignpNum;
    @Bind(R.id.edt_signpw)
    EditText edtSignpw;
    @Bind(R.id.btn_sign)
    Button btnSign;
    @Bind(R.id.btn_ApplyActivty)
    Button btnApplyActivty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        tv_dealCount = (TextView) findViewById(R.id.dealCount);
        tv_departCounty = (TextView) findViewById(R.id.departCounty);
        tv_likeCount = (TextView) findViewById(R.id.likeCount);
        tv_validDuration = (TextView) findViewById(R.id.validDuration);


//        mhandler=new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                if (msg.what==1) {
//                    ComplexInfoRoot c= (ComplexInfoRoot) msg.obj;
//                    tv_dealCount.setText("成交量:"+c.getComplexInfo().get(0).getDealCount());
//                    tv_departCounty.setText("开始区县:"+c.getComplexInfo().get(0).getDepartCounty());
//                    tv_likeCount.setText("收藏量:"+c.getComplexInfo().get(0).getLikeCount());
//                    tv_validDuration.setText("活动有效期："+c.getComplexInfo().get(0).getValidDurationStart()+"到"+c.getComplexInfo().get(0).getValidDurationEnd());
//                    String p1=c.getComplexInfo().get(0).getPhotoID1();
//
//
//                }
//            }
//        };
        Intent i = getIntent();
        aid = i.getStringExtra("aid");
        String registerThreadResult = null;

        GetAcitivtyComplexInfoThread tGetAcitivtyComplexInfo = new GetAcitivtyComplexInfoThread(aid);
        FutureTask<String> ftGetAcitivtyComplexInfo = new FutureTask<>(tGetAcitivtyComplexInfo);
        Thread GetAcitivtyComplexInfoThread = new Thread(ftGetAcitivtyComplexInfo);
        GetAcitivtyComplexInfoThread.start();

        try {
            registerThreadResult = ftGetAcitivtyComplexInfo.get();
            System.out.println(registerThreadResult);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ComplexInfoRoot c = new GsonUntils().ComplexTJO(registerThreadResult);

        tv_dealCount.setText("成交量:" + c.getComplexInfo().get(0).getDealCount());
        tv_departCounty.setText("开始区县:" + c.getComplexInfo().get(0).getDepartCounty());
        tv_likeCount.setText("收藏量:" + c.getComplexInfo().get(0).getLikeCount());
        tv_validDuration.setText("活动有效期：" + c.getComplexInfo().get(0).getValidDurationStart() + "到" + c.getComplexInfo().get(0).getValidDurationEnd());


//        try {
//            new OkHttpUntils().getComplexInfo(mhandler,aid);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    String signPnum=null;
    String signpw=null;
    String cookie=null;

    @OnClick({R.id.btn_sign, R.id.btn_ApplyActivty})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sign:

                signPnum=edtSignpNum.getText().toString();
                signpw=edtSignpw.getText().toString();
                SignThread tSign = new SignThread(signPnum,signpw);
                FutureTask<String> ftSign = new FutureTask<>(tSign);
                Thread SignThread = new Thread(ftSign);
                SignThread.start();

                try {
                    cookie=ftSign.get();
                    System.out.println(cookie);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.btn_ApplyActivty:




                ApplyActivityThread tApplyActivity =new ApplyActivityThread(aid,cookie);
                FutureTask<String> ftApplyActivity = new FutureTask<>(tApplyActivity);
                Thread ApplyActivityThread = new Thread(ftApplyActivity);
                ApplyActivityThread.start();

                try {
                    String registerThreadResult=ftApplyActivity.get();
                    System.out.println(registerThreadResult);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                break;
        }
    }
}
