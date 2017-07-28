package com.example.administrator.initiatepagedata.JsonData;

/**
 * Created by Administrator on 2017/4/20.
 */

public class AddressComponent {
    private String country;

    private String province;

    private String city;

    private String citycode;

    private String district;

    private String adcode;

    private String township;

    private String towncode;

    private Neighborhood neighborhood;

    private Building building;

    private StreetNumber streetNumber;

//    private List<BusinessAreas> businessAreas ;

    public void setCountry(String country){
        this.country = country;
    }
    public String getCountry(){
        return this.country;
    }
    public void setProvince(String province){
        this.province = province;
    }
    public String getProvince(){
        return this.province;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){
        return this.city;
    }
    public void setCitycode(String citycode){
        this.citycode = citycode;
    }
    public String getCitycode(){
        return this.citycode;
    }
    public void setDistrict(String district){
        this.district = district;
    }
    public String getDistrict(){
        return this.district;
    }
    public void setAdcode(String adcode){
        this.adcode = adcode;
    }
    public String getAdcode(){
        return this.adcode;
    }
    public void setTownship(String township){
        this.township = township;
    }
    public String getTownship(){
        return this.township;
    }
    public void setTowncode(String towncode){
        this.towncode = towncode;
    }
    public String getTowncode(){
        return this.towncode;
    }
    public void setNeighborhood(Neighborhood neighborhood){
        this.neighborhood = neighborhood;
    }
    public Neighborhood getNeighborhood(){
        return this.neighborhood;
    }
    public void setBuilding(Building building){
        this.building = building;
    }
    public Building getBuilding(){
        return this.building;
    }
    public void setStreetNumber(StreetNumber streetNumber){
        this.streetNumber = streetNumber;
    }
    public StreetNumber getStreetNumber(){
        return this.streetNumber;
    }
//    public void setBusinessAreas(List<BusinessAreas> businessAreas){
//        this.businessAreas = businessAreas;
//    }
////    public List<BusinessAreas> getBusinessAreas(){
//        return this.businessAreas;
//    }
}
