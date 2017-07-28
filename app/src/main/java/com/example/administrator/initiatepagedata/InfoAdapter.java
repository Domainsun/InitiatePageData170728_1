package com.example.administrator.initiatepagedata;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.initiatepagedata.JsonData.ActivitySimple;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */

public class InfoAdapter extends BaseAdapter {

    private List<ActivitySimple> data;
    private LayoutInflater layoutInflater;


    public InfoAdapter(LayoutInflater inflater, List<ActivitySimple> data) {
        //传入的data，就是我们要在listview中显示的信息

        this.data = data;
        layoutInflater = inflater;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View simpleview = layoutInflater.inflate(R.layout.item_simple, null);
        ImageView iv_1_1= (ImageView) simpleview.findViewById(R.id.iv_1_1);
        TextView tv_costTime= (TextView) simpleview.findViewById(R.id.tv_costTime);
        TextView tv_county= (TextView) simpleview.findViewById(R.id.tv_county);
        TextView tv_dealCount= (TextView) simpleview.findViewById(R.id.tv_dealCount);
        TextView tv_introduction= (TextView) simpleview.findViewById(R.id.tv_introduction);
        TextView tv_price= (TextView) simpleview.findViewById(R.id.tv_price);
        TextView tv_start= (TextView) simpleview.findViewById(R.id.tv_start);


        ActivitySimple as = data.get(position);
        iv_1_1.setImageBitmap(as.getPhoto1());
        tv_costTime.setText(as.getCostTime());
        tv_county.setText(as.getCounty());
        tv_dealCount.setText(as.getDealCount());
        tv_introduction.setText(as.getIntroduction());
        tv_price.setText(as.getPrice());
        tv_start.setText(as.getStart());
        return simpleview;
    }
}
