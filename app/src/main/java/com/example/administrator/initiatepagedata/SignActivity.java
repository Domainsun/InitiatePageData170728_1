package com.example.administrator.initiatepagedata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.initiatepagedata.HttpUtils.GetMatchCodeThread;
import com.example.administrator.initiatepagedata.HttpUtils.RegisterThread;
import com.example.administrator.initiatepagedata.HttpUtils.SignThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignActivity extends AppCompatActivity {

    @Bind(R.id.edt_pNum)
    EditText edtPNum;
    @Bind(R.id.btn_getMatchCode)
    Button btnGetMatchCode;
    @Bind(R.id.edt_matchCode)
    EditText edtMatchCode;
    @Bind(R.id.edt_pw)
    EditText edtPw;
    @Bind(R.id.btn_register)
    Button btnRegister;
    @Bind(R.id.edt_signpNum)
    EditText edtSignpNum;
    @Bind(R.id.edt_signpw)
    EditText edtSignpw;
    @Bind(R.id.btn_sign)
    Button btnSign;

    public static final String signCookie=null;
    String registerpNum,matchCode,registerpw,signPnum,signpw;
    String getMatchCodeCookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sign);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_getMatchCode, R.id.btn_register,R.id.btn_sign})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_getMatchCode:
                registerpNum=edtPNum.getText().toString();
                GetMatchCodeThread tGetMatchCode = new GetMatchCodeThread(registerpNum,"register");
                FutureTask<String> ftGetMatchCode = new FutureTask<>(tGetMatchCode);
                Thread GetMatchCodeThread = new Thread(ftGetMatchCode);
                GetMatchCodeThread.start();
                try {
                    getMatchCodeCookie=ftGetMatchCode.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.btn_register:
                registerpNum=edtPNum.getText().toString();
                registerpw=edtPw.getText().toString();
                matchCode=edtMatchCode.getText().toString();

                RegisterThread tRegister = new RegisterThread(registerpNum,"photo1",registerpw,matchCode,getMatchCodeCookie);
                FutureTask<String> ftRegister = new FutureTask<>(tRegister);
                Thread RegisterThread = new Thread(ftRegister);
                RegisterThread.start();

                try {
                    String registerThreadResult=ftRegister.get();
                    System.out.println(registerThreadResult);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btn_sign:
                signPnum=edtSignpNum.getText().toString();
                signpw=edtSignpw.getText().toString();


                SignThread tSign = new SignThread(signPnum,signpw);
                FutureTask<String> ftSign = new FutureTask<>(tSign);
                Thread SignThread = new Thread(ftSign);
                SignThread.start();

                try {
                    String registerThreadResult=ftSign.get();
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
