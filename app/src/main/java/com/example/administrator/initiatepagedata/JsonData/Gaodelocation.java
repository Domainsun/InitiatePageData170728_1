package com.example.administrator.initiatepagedata.JsonData;

/**
 * Created by Administrator on 2017/4/20.
 */

public class Gaodelocation {

    private String status;

    private String info;

    private String infocode;

    private Regeocode regeocode;

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
    public void setRegeocode(Regeocode regeocode){
        this.regeocode = regeocode;
    }
    public Regeocode getRegeocode(){
        return this.regeocode;
    }
}
