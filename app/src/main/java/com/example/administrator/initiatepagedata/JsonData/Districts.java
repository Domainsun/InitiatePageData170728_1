package com.example.administrator.initiatepagedata.JsonData;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */


public class Districts {

//    private String citycode;

    private String adcode;

    private String name;

    private String center;

    private String level;

    private List<Districts> districts ;

//    public void setCitycode(String citycode){
//        this.citycode = citycode;
//    }
//    public String getCitycode(){
//        return this.citycode;
//    }
    public void setAdcode(String adcode){
        this.adcode = adcode;
    }
    public String getAdcode(){
        return this.adcode;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setCenter(String center){
        this.center = center;
    }
    public String getCenter(){
        return this.center;
    }
    public void setLevel(String level){
        this.level = level;
    }
    public String getLevel(){
        return this.level;
    }
    public void setDistricts(List<Districts> districts){
        this.districts = districts;
    }
    public List<Districts> getDistricts(){
        return this.districts;
    }

}