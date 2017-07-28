package com.example.administrator.initiatepagedata.JsonData;

/**
 * Created by Administrator on 2017/4/20.
 */

public class Regeocode {

    private String formatted_address;

    private AddressComponent addressComponent;

    public void setFormatted_address(String formatted_address){
        this.formatted_address = formatted_address;
    }
    public String getFormatted_address(){
        return this.formatted_address;
    }
    public void setAddressComponent(AddressComponent addressComponent){
        this.addressComponent = addressComponent;
    }
    public AddressComponent getAddressComponent(){
        return this.addressComponent;
    }
}
