package com.example.administrator.initiatepagedata.JsonData;

import java.util.List;

public class Showapi_res_body {
    private int ret_code;

    private boolean flag;

    private List<Data> data ;

    public void setRet_code(int ret_code){
        this.ret_code = ret_code;
    }
    public int getRet_code(){
        return this.ret_code;
    }
    public void setFlag(boolean flag){
        this.flag = flag;
    }
    public boolean getFlag(){
        return this.flag;
    }
    public void setData(List<Data> data){
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }

}
