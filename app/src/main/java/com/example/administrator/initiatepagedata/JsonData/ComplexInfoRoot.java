package com.example.administrator.initiatepagedata.JsonData;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17.
 */

public class ComplexInfoRoot {
    private List<ComplexInfo> ComplexInfo;

    public List<com.example.administrator.initiatepagedata.JsonData.ComplexInfo> getComplexInfo() {
        return ComplexInfo;
    }

    public void setComplexInfo(List<com.example.administrator.initiatepagedata.JsonData.ComplexInfo> complexInfo) {
        ComplexInfo = complexInfo;
    }
}
