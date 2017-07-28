package com.example.administrator.initiatepagedata;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityLocation extends AppCompatActivity {
    private double latitude=0.0;
    private double longitude =0.0;
    TextView tv_location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        tv_location= (TextView) findViewById(R.id.tv_location);

        getlocation();


    }


    public void getlocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(location != null){
                latitude = location.getLatitude();
                longitude = location.getLongitude();


                showLocation(location);

            }
        }else{
            LocationListener locationListener = new LocationListener() {

                // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                // Provider被enable时触发此函数，比如GPS被打开
                @Override
                public void onProviderEnabled(String provider) {

                }

                // Provider被disable时触发此函数，比如GPS被关闭
                @Override
                public void onProviderDisabled(String provider) {

                }

                //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
                @Override
                public void onLocationChanged(Location location) {
                    if (location != null) {
                        Log.e("Map", "Location changed : Lat: "
                                + location.getLatitude() + " Lng: "
                                + location.getLongitude());
                    }
                }
            };
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000, 0,locationListener);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(location != null){
                latitude = location.getLatitude(); //经度
                longitude = location.getLongitude();//纬度
                showLocation(location);
            }
        }
    }


    public void showLocation(Location location){

        String locationStr = "维度：" + location.getLatitude() +"\n"
                + "经度：" + location.getLongitude();
        System.out.println(locationStr);
        tv_location.setText(locationStr);

        Toast.makeText(this, locationStr, Toast.LENGTH_SHORT).show();
    }
}
