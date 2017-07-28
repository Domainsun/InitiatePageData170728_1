package com.example.administrator.initiatepagedata.JsonData;

/**
 * Created by Administrator on 2017/4/19.
 */

import java.util.List;

/**
 * Copyright 2017 bejson.com
 */

public class Suggestion {

    private List<String> keywords;
    private List<String> cities;
    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
    public List<String> getKeywords() {
        return keywords;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
    public List<String> getCities() {
        return cities;
    }

}