package com.example.administrator.initiatepagedata;


import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.administrator.initiatepagedata.JsonData.AcitivityInfo;
import com.example.administrator.initiatepagedata.JsonData.ActivityType;
import com.example.administrator.initiatepagedata.Untils.OkHttpUntils;
import com.example.administrator.initiatepagedata.Untils.PinYingIndex;

import java.io.IOException;
import java.text.Collator;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import static java.lang.Double.parseDouble;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,  AMapLocationListener /*implements View.OnClickListener, AdapterView.OnItemSelectedListener, AMapLocationListener*/ {
    Button btn_choosedate, btn_hasCar, btn_noCar, btn_cancle, btn_next;
    Spinner Sspinner_city, Sspinner_county, Fspinner_city, Fspinner_county, spinner_type, spinner_cosTime, spinner_personLimit;
    EditText edt_introduction, edt_price;



    public static AcitivityInfo aif;

    String introduction=null;
    Boolean hascar;
    String destination[]=new String[2];
    String departure[]=new String[2];
    double price=0;
    String cosTime;
    Calendar c[]=new Calendar[2];
    int personLimit;



    String str_type, str_time, str_brief, str_province, str_city, str_county, locationProvider;
    int NumPeople;
    double money;

    LocationManager locationManager;
    Location location;
    public static String[] citys;
    public static String[] strscountys;
    public static String[] strscountys1;
    public static String defaultcity;
    public static String defaultcounty;
    public static String defaultcounty1;

    Handler mhandler;

    ArrayAdapter<String> cityadapter;
    ArrayAdapter<String> countyadapter;
    ArrayAdapter<String> countyadapter1;
    List<String> countys;
    List<String> countys1;

    public static String latitude;
    public static String longitude;

    String departurecity="";
    String departurecounty="";
    String destinationcity="";
    String destinationcounty="";

    

    public AMapLocationClientOption mLocationOption = null;
    AMapLocationClient mlocationClient;

    public static  final int Sspinner_city1=1;
    public static  final int Fspinner_city1=2;
    public static  final int spinner_cosTime1=3;
    public static  final int spinner_type1=4;
    public static  final int spinner_personLimit1=5;
//    public static  final int Sspinner_city1=1;
//    public static  final int Sspinner_city1=1;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        event();

    }


    /*初始化控件*/
    private void init() {
        btn_choosedate = (Button) findViewById(R.id.activityValidity);
        btn_hasCar = (Button) findViewById(R.id.haveCar);
        btn_noCar = (Button) findViewById(R.id.NoCar);
        btn_cancle = (Button) findViewById(R.id.cancel);
        btn_next = (Button) findViewById(R.id.next);

        edt_introduction = (EditText) findViewById(R.id.Introduction);
        edt_price = (EditText) findViewById(R.id.price);

        Sspinner_city = (Spinner) findViewById(R.id.startcity);
        Sspinner_county = (Spinner) findViewById(R.id.startcounty);
        Fspinner_county = (Spinner) findViewById(R.id.finshcounty);
        Fspinner_city = (Spinner) findViewById(R.id.finshcity);
        spinner_cosTime = (Spinner) findViewById(R.id.needtime);
        spinner_personLimit = (Spinner) findViewById(R.id.peoples);
        spinner_type = (Spinner) findViewById(R.id.typecontent);



        Sspinner_city.setTag(Sspinner_city1);
        Fspinner_city.setTag(Fspinner_city1);
        spinner_cosTime.setTag(spinner_cosTime1);
        spinner_type.setTag(spinner_type1);
        spinner_personLimit.setTag(spinner_personLimit1);

        aif=new AcitivityInfo();
        mhandmessage();
        try {
            new OkHttpUntils().getcity("中国", mhandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        getlocation();







//
//        String introduction=edt_introduction.getText().toString();
//        double price=Double.parseDouble(edt_price.getText().toString());
//        asimple.setIntroduction(introduction);
//        asimple.setPrice(price);


    }

    /*设置事件*/
    private void event() {
        btn_choosedate.setOnClickListener(this);
        btn_hasCar.setOnClickListener(this);
        btn_noCar.setOnClickListener(this);
        btn_cancle.setOnClickListener(this);
        btn_next.setOnClickListener(this);


            final String d1[]=new String[2];
            final String d2[]=new String[2];

        Sspinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (citys != null) {
                    try {
                        new OkHttpUntils().getcounty(citys[position], mhandler);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this, citys[position], Toast.LENGTH_SHORT).show();
                    departurecity=citys[position];
                    d1[0]=departurecity;
                } else {
                    return;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Fspinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (citys != null) {
                    try {
                        new OkHttpUntils().getcounty1(citys[position], mhandler);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this, citys[position], Toast.LENGTH_SHORT).show();
                    destinationcity =citys[position];
                    d2[0]=destinationcity;
                } else {
                    return;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_cosTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String costime[]=getResources().getStringArray(R.array.needtime);
                    String str_costime=costime[position];
                    double dcostime= parseDouble(str_costime);
                    aif.setCostTime(dcostime);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String type[]=getResources().getStringArray(R.array.activity);
                String str_tpye=type[position];
                if (str_tpye.equals("活动")) {
                    aif.setMyActivityType(ActivityType.activity.getValue());
                } else if (str_tpye.equals("旅行")) {
                    aif.setMyActivityType(ActivityType.tourism.getValue());
                } else if (str_tpye.equals("玩乐")) {
                    aif.setMyActivityType(ActivityType.play.getValue());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_personLimit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String personLimit[]=getResources().getStringArray(R.array.peoples);
                String str_personLimit=personLimit[position];
                int dpersonLmit=Integer.parseInt(str_personLimit);
                aif.setPersonLimit(dpersonLmit);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Sspinner_county.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (strscountys!= null) {
                    departurecounty=strscountys[position];
                    d1[1] = departurecounty;
                    Toast.makeText(MainActivity.this, strscountys[position], Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "11", Toast.LENGTH_SHORT).show();
                }
//
//                departurecounty=strscountys[position];
//                d1[1]=departurecounty;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Fspinner_county.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //TODO
//                destinationcounty=strscountys1[position];
//                d2[1]=destinationcity;
                if (strscountys1 != null) {
                    destinationcity= strscountys1[position];
                    d2[1]=destinationcity;
                    Toast.makeText(MainActivity.this, strscountys1[position], Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "11", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*城市暂时加载不出来，先写死测试 2017年7月28日10:29:19*/
        d1[0]="赣州市";
        d1[1]="于都县";
        d2[0]="赣州市";
        d1[1]="于都县";

        aif.setDeparture(d1);
        aif.setDestination(d2);


    }
    String str_price=null;
    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.activityValidity:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, NextActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.haveCar:
                btn_hasCar.setBackgroundColor(Color.parseColor("#ffff00"));
                btn_noCar.setBackgroundColor(Color.parseColor("#ffffff"));
                hascar=true;
                aif.setHasCar(hascar);
                break;
            case R.id.NoCar:
                btn_noCar.setBackgroundColor(Color.parseColor("#ffff00"));
                btn_hasCar.setBackgroundColor(Color.parseColor("#ffffff"));
                hascar=false;
                aif.setHasCar(hascar);
                break;
            case R.id.cancel:
                this.finish();
                break;
            case R.id.next:

                //Todo
//                introduction =edt_introduction.getText().toString();
                str_price=edt_price.getText().toString();
                if (str_price !=null) {

                    price= parseDouble(str_price);
                    aif.setPrice(price);
//                    aif.setIntroduction(introduction);
                    Intent i = new Intent(MainActivity.this, Acitivity_main1.class);
                    startActivity(i);
                } else {
                    Toast.makeText(this, "请输入简介和价格！", Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }




//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//        Toast.makeText(this, view.getId()+"", Toast.LENGTH_SHORT).show();;
////        switch (view.getTag()) {
////            case spinner_personLimit1:
////                try {
////                    new OkHttpUntils().getcounty(citys[position], mhandler);
////                    Toast.makeText(this,citys[position], Toast.LENGTH_SHORT).show();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////                break;
////            case spinner_personLimit1:
////                try {
////                    new OkHttpUntils().getcounty(citys[position], mhandler);
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////                break;
////            case R.id.needtime:
////                cosTime= spinner_cosTime.getSelectedItem().toString();
////                Toast.makeText(this, cosTime, Toast.LENGTH_SHORT).show();
////                break;
////            case R.id.peoples:
////
////                break;
//////            case R.id.typecontent:
////
////                break;
//        }
//
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }

    public void mhandmessage() {
        mhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    countys = (List<String>) msg.obj;
                    strscountys = toStringArray(countys);
                    strscountys = sort(strscountys);
                    countyadapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, strscountys);
                    countyadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Sspinner_county.setAdapter(countyadapter);

                    if (defaultcounty == null) {
                        return;
                    } else {
                        for (int i = 0; i < strscountys.length; i++) {
                            if (defaultcounty.equals(strscountys[i])) {
                                Sspinner_county.setSelection(i);
                            }
                        }
                    }


                } else if (msg.what == 3) {
                    List<String> location = (List<String>) msg.obj;
                    defaultcity = location.get(0);
                    defaultcounty = location.get(1);
                    System.out.println("---main----" + defaultcity + defaultcounty);
                } else if (msg.what == 4) {
                    List<String> citys11 = (List<String>) msg.obj;
                    citys = toStringArray(citys11);
                    citys = sort(citys);

                    for (int i = 0; i < citys.length; i++) {
                        String cityindex = (String) citys[i].subSequence(0, 1);
                        System.out.println("-取出第一个字---" + cityindex);
                        String pyindex = new PinYingIndex().getPYIndexStr(cityindex, true);
                        System.out.println("\n首字母:" + pyindex);
                    }
                    cityadapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, citys);
                    cityadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Sspinner_city.setAdapter(cityadapter);
                    Fspinner_city.setAdapter(cityadapter);

                    if (defaultcity == null) {
                        return;
                    } else {
                        for (int i = 0; i < citys.length; i++) {
                            if (defaultcity.equals(citys[i])) {
                                Sspinner_city.setSelection(i);
                            }
                        }
                    }

                } else if (msg.what == 5) {
                    countys1 = (List<String>) msg.obj;
                    strscountys1 = toStringArray(countys1);
                    strscountys1 = sort(strscountys1);
                    countyadapter1 = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, strscountys1);
                    countyadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Fspinner_county.setAdapter(countyadapter1);

                    if (defaultcounty == null) {
                        return;
                    } else {
                        for (int i = 0; i < strscountys1.length; i++) {
                            if (defaultcounty.equals(strscountys1[i])) {
                                Fspinner_county.setSelection(i);
                            }
                        }
                    }

                }
            }
        };
    }


//
//
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.SendToWeb:
//                ActivitySimple a=getinfo();
//
//                String json = new GsonUntils().tojson(a);
//                try {
//                    new OkHttpUntils().get(json);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case R.id.GetFormWeb:
//                try {
//
//                    Handler handler = new Handler() {
//                        @Override
//                        public void handleMessage(Message msg) {
//                            super.handleMessage(msg);
////                            OrderJsonData o = (OrderJsonData) msg.obj;
//
//                        }
//                    };
//                    new OkHttpUntils().getjson(handler);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                break;
//
//        }
//
//    }
//


    public String[] sort(String[] str) {
        Comparator<Object> com = Collator.getInstance(Locale.CHINA);
        Arrays.sort(str, com);
        for (String i : str) {
            System.out.print(i + " -- ");
        }
        return str;
    }


    //    //发送当前位置经纬度，获取城市区县
//
//    //获取经纬度
    public void getlocation() {


        mlocationClient = new AMapLocationClient(this);
        mLocationOption = new AMapLocationClientOption();
        mlocationClient.setLocationListener(this);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(30000);
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();
    }


    public static String[] toStringArray(List<String> strList) {
        String[] array = new String[strList.size()];
        strList.toArray(array);
        return array;
    }

//
//
//

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                latitude = String.valueOf(amapLocation.getLatitude());//获取纬度
                longitude = String.valueOf(amapLocation.getLongitude());//获取经度

                String location = "经度:" + longitude + "纬度:" + latitude;
                Toast.makeText(this, location, Toast.LENGTH_SHORT).show();

                try {
                    new OkHttpUntils().getlocation(longitude, latitude, mhandler);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());

                Toast.makeText(this, amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}


//
//
//
//
