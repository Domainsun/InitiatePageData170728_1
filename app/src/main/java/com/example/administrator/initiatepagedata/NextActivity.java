package com.example.administrator.initiatepagedata;
/**
 * Created by 16531 on 2017/4/16.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static com.example.administrator.initiatepagedata.MainActivity.aif;


public class NextActivity extends Activity {
        private ImageView iv_left;
        private ImageView iv_right;
        private TextView tv_date;
        private TextView tv_week;
        private TextView tv_today;
        private MonthDate monthDateView;
        private int year,month,day,year1,month1,day1;
        private int selectiontimes=0;//日期的选择次数

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //activity转dialog之后把标题去掉
            View bv = this.findViewById(android.R.id.title);
            bv.setVisibility(View.GONE);

            setContentView(R.layout.activity_date);
            iv_left = (ImageView) findViewById(R.id.iv_left);
            iv_right = (ImageView) findViewById(R.id.iv_right);
            monthDateView = (MonthDate) findViewById(R.id.monthDateView);
            tv_date = (TextView) findViewById(R.id.date_text);
            tv_week  =(TextView) findViewById(R.id.week_text);
            tv_today = (TextView) findViewById(R.id.tv_today);
            monthDateView.setTextView(tv_date,tv_week);

            monthDateView.setDateClick(new MonthDate.DateClick(){
                Calendar c1[]=new Calendar[2];
               @Override
                public void onClickOnDate() {
                    if(selectiontimes==0){
                        year=monthDateView.getmSelYear();
                        month=monthDateView.getmSelMonth()+1;
                        day=monthDateView.getmSelDay();
                        selectiontimes++;
                        Toast.makeText(getApplication(), "你第一次选择的日期是:"+year+"年"
                                +month+"月"+day+"日", Toast.LENGTH_SHORT).show();


                        Toast.makeText(NextActivity.this, ""+year+month+day, Toast.LENGTH_SHORT).show();
                       Calendar c=Calendar.getInstance();
                        c.set(Calendar.YEAR,year);
                        c.set(Calendar.MONTH,month);
                        c.set(Calendar.DATE,day);

                        c1[0]=c;



                    }else if(selectiontimes==1){
                        year1=monthDateView.getmSelYear();
                        month1=monthDateView.getmSelMonth()+1;
                        day1=monthDateView.getmSelDay();
                        selectiontimes--;
                        if(year1<year){
                            Toast.makeText(getApplication(), "时间无效请重新选择！", Toast.LENGTH_SHORT).show();
                        }else if(year1==year&&month1<month){
                            Toast.makeText(getApplication(), "时间无效请重新选择！", Toast.LENGTH_SHORT).show();
                        }
                        else if(year1==year&&month1==month&&day1<day){
                            Toast.makeText(getApplication(), "时间无效请重新选择！", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(getApplication(), "你第二次选择的日期是:"+year1+"年"
                                    +month1+"月"+day1+"日", Toast.LENGTH_SHORT).show();


                            Calendar c=Calendar.getInstance();
                            c.set(Calendar.YEAR,year1);
                            c.set(Calendar.MONTH,month1);
                            c.set(Calendar.DATE,day1);
                            c1[1]=c;



                        }
                        monthDateView.setEnabled(false);
                    }
                   aif.setValidDuration(c1);
                }
            });
            setOnlistener();

        }
        //三个按钮的事件
        private void setOnlistener(){
            iv_left.setOnClickListener(new OnClickListener() {
                //上一个月的点击事件
                @Override
                public void onClick(View v) {
                    monthDateView.onLeftClick();
                }
            });

            iv_right.setOnClickListener(new OnClickListener() {
                //下一个月的点击事件
                @Override
                public void onClick(View v) {
                    monthDateView.onRightClick();
                }
            });
            tv_today.setOnClickListener(new OnClickListener() {
                //“今”的点击事件
                @Override
                public void onClick(View v) {
                    monthDateView.setTodayToView();
                }
            });
        }
    }
