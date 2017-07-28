package com.example.administrator.initiatepagedata.JsonData;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */


public class Gaodecitys {

    private String status;

    private String info;

    private String infocode;

    private String count;

    private Suggestion suggestion;

    private List<Districts> districts ;

    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setInfo(String info){
        this.info = info;
    }
    public String getInfo(){
        return this.info;
    }
    public void setInfocode(String infocode){
        this.infocode = infocode;
    }
    public String getInfocode(){
        return this.infocode;
    }
    public void setCount(String count){
        this.count = count;
    }
    public String getCount(){
        return this.count;
    }
    public void setSuggestion(Suggestion suggestion){
        this.suggestion = suggestion;
    }
    public Suggestion getSuggestion(){
        return this.suggestion;
    }
    public void setDistricts(List<Districts> districts){
        this.districts = districts;
    }
    public List<Districts> getDistricts(){
        return this.districts;
    }

}