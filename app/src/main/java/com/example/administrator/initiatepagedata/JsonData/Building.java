package com.example.administrator.initiatepagedata.JsonData;

import java.lang.reflect.Type;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by Administrator on 2017/4/20.
 */

public class Building {

    private List<Attributes.Name> name ;

    private List<Type> type ;

    public void setName(List<Attributes.Name> name){
        this.name = name;
    }
    public List<Attributes.Name> getName(){
        return this.name;
    }
    public void setType(List<Type> type){
        this.type = type;
    }
    public List<Type> getType(){
        return this.type;
    }
}
