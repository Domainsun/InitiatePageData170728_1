package com.example.administrator.initiatepagedata.Untils;

import com.example.administrator.initiatepagedata.JsonData.AcitivityInfo;
import com.example.administrator.initiatepagedata.JsonData.ActivityComplex;
import com.example.administrator.initiatepagedata.JsonData.ComplexInfoRoot;
import com.example.administrator.initiatepagedata.JsonData.Gaodecitys;
import com.example.administrator.initiatepagedata.JsonData.Gaodelocation;
import com.example.administrator.initiatepagedata.JsonData.SimInfoRoot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/6.
 */

public class GsonUntils {
   public  static  Map<String,String> mapcitynameid=new HashMap<>();


    public String tojson(AcitivityInfo a){
        Gson gson=new Gson();
        String result = gson.toJson(a);
        System.out.println("向网络发送info数据"+result);
        return result;
    }

    public String tojson1(ActivityComplex a){
        Gson gson=new Gson();
        String result = gson.toJson(a);
        System.out.println("向网络发送complex数据"+result);
        return result;
    }


    public AcitivityInfo toJO(String str_json){
        AcitivityInfo o=new AcitivityInfo();
        Gson gson=new Gson();
        o=gson.fromJson(str_json,AcitivityInfo.class);
        System.out.println("需用时"+o.getCostTime());
        return  o;
    }


    public SimInfoRoot SimpleTJO(String str_json){
        SimInfoRoot o=new SimInfoRoot();
        Gson gson=new Gson();
        o=gson.fromJson(str_json,SimInfoRoot.class);
        System.out.println("id"+o.getSimpleInfo().getInfo().get(0).getActivityID());
        return o;
    }

    public ComplexInfoRoot ComplexTJO(String str_json){
        ComplexInfoRoot o=new ComplexInfoRoot();
        Gson gson=new Gson();
        o=gson.fromJson(str_json,ComplexInfoRoot.class);
        System.out.println("成交量:"+o.getComplexInfo().get(0).getDealCount());
        return o;
    }




    public List<String> getcityjava(String json){
        Gaodecitys gaodecitys=new Gaodecitys();
        Gson gson=new Gson();
        gaodecitys=gson.fromJson(json,Gaodecitys.class);
        List<String> citys=new ArrayList<String>();
        for (int i = 0; i <gaodecitys.getDistricts().get(0).getDistricts().size() ; i++) {
            citys.add(gaodecitys.getDistricts().get(0).getDistricts().get(i).getName());
            System.out.println("第"+i+"个城市："+citys.get(i));
        }


        return citys;
    }


    public List<String> getcity(String json){
        Gaodecitys gaodecitys=new Gaodecitys();
        Gson gson=new Gson();
        gaodecitys=gson.fromJson(json,Gaodecitys.class);
        List<String> citys=new ArrayList<String>();



        int providesize=gaodecitys.getDistricts().get(0).getDistricts().size();
        System.out.println("所有省市"+providesize);
        int citysize=gaodecitys.getDistricts().get(0).getDistricts().get(5).getDistricts().size();
        System.out.println(citysize);
//
        for (int i=0;i<providesize;i++) {

            for (int j=0;j<gaodecitys.getDistricts().get(0).getDistricts().get(i).getDistricts().size();j++) {
                String city=gaodecitys.getDistricts().get(0).getDistricts().get(i).getDistricts().get(j).getName();
                System.out.println("第"+i+"个城市："+city);
                citys.add(city);
            }
        }


        return citys;
    }

    public List<String> getlocationjava(String json){
        Gaodelocation l =new Gaodelocation();
        Gson gson=new Gson();
        l=gson.fromJson(json,Gaodelocation.class);
        List<String> location=new ArrayList<String>();


            String latitude=l.getRegeocode().getAddressComponent().getCity();
            String longitude=l.getRegeocode().getAddressComponent().getDistrict();
            System.out.println("城市："+latitude);
            System.out.println("区县："+longitude);
            location.add(latitude);
            location.add(longitude);


        return location;
    }
}
