package com.example.administrator.initiatepagedata.JsonData;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/5/4.
 */

public class ActivitySimple {



    private Bitmap photo1;
    private String costTime,county,dealCount,introduction,price,start,activityID;



    public Bitmap getPhoto1() {
        return photo1;
    }

    public String getActivityID() {
        return activityID;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public void setPhoto1(Bitmap photo1) {
        this.photo1 = photo1;
    }

    public String getCostTime() {
        return costTime;
    }

    public void setCostTime(String costTime) {
        this.costTime = costTime;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDealCount() {
        return dealCount;
    }

    public void setDealCount(String dealCount) {
        this.dealCount = dealCount;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public ActivitySimple(Bitmap photo1,String costTime,String county,String dealCount,String introduction,String price,String start,String activityID){
        this.photo1=photo1;
        this.costTime=costTime;
        this.county=county;
        this.dealCount=dealCount;
        this.introduction=introduction;
        this.price=price;
        this.start=start;
        this.activityID=activityID;

    }
}
