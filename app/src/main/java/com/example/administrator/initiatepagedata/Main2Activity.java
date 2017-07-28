package com.example.administrator.initiatepagedata;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.lljjcoder.citypickerview.widget.CityPicker;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    TextView new_address_area;
    Spinner spinner_1;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
        new_address_area.setOnClickListener(this);
    }

    private void init() {

        new_address_area = (TextView) findViewById(R.id.new_address_area);
        spinner_1= (Spinner) findViewById(R.id.spinner_1);

        adapter = new ArrayAdapter<String>(this,
                R.layout.simple_spinner_item,R.id.tv_1);

        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);

        adapter.add("A");
        adapter.add("B");
        adapter.add("C");
        adapter.add("D");


        spinner_1.setAdapter(adapter);


    }

    private void selectAddress() {
        CityPicker cityPicker = new CityPicker.Builder(Main2Activity.this)
                .textSize(14)
                .title("地址选择")
                .titleBackgroundColor("#FFFFFF")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province("江苏省")
                .city("常州市")
                .district("天宁区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();
        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                //为TextView赋值
                new_address_area.setText(province.trim() + "-" + city.trim() + "-" + district.trim());
            }
        });
    }

    @Override
    public void onClick(View v) {
        //判断输入法的隐藏状态
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            selectAddress();//调用CityPicker选取区域
        }
    }
}
