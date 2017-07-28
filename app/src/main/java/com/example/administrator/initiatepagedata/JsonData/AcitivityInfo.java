package com.example.administrator.initiatepagedata.JsonData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */

public class AcitivityInfo {


    private String personID;/*发起人id*/
    private Calendar[] validDuration;/*有效期*/
    private String []departure;/*出发市县*/
    private String details;/*详情*/
    private double stars;/*评价星级*/
    private int dealCount;/*成交量*/
    private int likeCount;/*收藏量*/
    private int personLimit;/*人数限制*/
    private List<String> photos;/*所有图片*/
    private ActivityState myActivityState;  /*状态*///已激活、未激活、进行中、已保存、已提交
    private String activityID;  //20170505+ID+00001  活动id
    private String title;/*标题*/
    private double price;/*价格*/
    private double costTime;/*用时*/
    private String []destination;/*目的市县*/
//    private String introduction;/*简介*/
    private Boolean hasCar;/*有无车*/
    private String photoID1;/*图片1*/
    private int myActivityType;/*活动类型*/



    public String[] getDeparture() {
        return departure;
    }

    public void setDeparture(String[] departure) {
        this.departure = departure;
    }



    public String getPersonID() {

        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public Calendar[] getValidDuration() {
        return validDuration;
    }

    public void setValidDuration(Calendar[] validDuration) {
        this.validDuration = validDuration;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public int getDealCount() {
        return dealCount;
    }

    public void setDealCount(int dealCount) {
        this.dealCount = dealCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getPersonLimit() {
        return personLimit;
    }

    public void setPersonLimit(int personLimit) {
        this.personLimit = personLimit;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public ActivityState getMyActivityState() {
        return myActivityState;
    }

    public void setMyActivityState(ActivityState myActivityState) {
        this.myActivityState = myActivityState;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    public String getPhotoID1() {
        return photoID1;
    }

    public void setPhotoID1(String photoID1) {
        this.photoID1 = photoID1;
    }


    public int getMyActivityType() {
        return myActivityType;
    }

    public void setMyActivityType(int myActivityType) {
        this.myActivityType = myActivityType;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public double getCostTime() {
        return costTime;
    }

    public void setCostTime(double costTime) {
        this.costTime = costTime;
    }

    public String[] getDestination() {
        return destination;
    }

    public void setDestination(String[] destination) {
        this.destination = destination;
    }

    public Boolean getHasCar() {
        return hasCar;
    }

    public void setHasCar(Boolean hasCar) {
        this.hasCar = hasCar;
    }


    public AcitivityInfo(){
        personID ="";
        validDuration=new Calendar[2];
        departure=new String[2];
        details="";
        stars=0.0;
        dealCount=0;
        likeCount=0;
        personLimit=0;
        photos =new ArrayList<String>();
        ActivityState myActivityState=ActivityState.saved;


        title="";
        price=0.0;
        costTime=0.0;
        destination=new String[2];
        hasCar=true;
        photoID1="";
        ActivityType myActivityType=ActivityType.activity;
    }





}
