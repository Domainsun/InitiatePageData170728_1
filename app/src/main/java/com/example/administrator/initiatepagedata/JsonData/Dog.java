package com.example.administrator.initiatepagedata.JsonData;

/**
 * Created by Administrator on 2017/6/16.
 */

public class Dog {

    private String name;
    private String breed;
    private int count;
    private boolean twoFeet;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
    public String getBreed() {
        return breed;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public int getCount() {
        return count;
    }

    public void setTwoFeet(boolean twoFeet) {
        this.twoFeet = twoFeet;
    }
    public boolean getTwoFeet() {
        return twoFeet;
    }
}
