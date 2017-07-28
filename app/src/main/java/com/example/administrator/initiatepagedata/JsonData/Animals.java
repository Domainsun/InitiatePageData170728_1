package com.example.administrator.initiatepagedata.JsonData;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */

public class Animals {
    private List<Dog> dog;
    public void setDog(List<Dog> dog) {
        this.dog = dog;
    }
    public List<Dog> getDog() {
        return dog;
    }
}
