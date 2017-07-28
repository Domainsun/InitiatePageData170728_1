package com.example.administrator.initiatepagedata;

/**
 * Created by 16531 on 2017/4/16.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.initiatepagedata.Untils.DateUtils;

import java.util.Calendar;

/*
 * 使用canvas绘制日历图
 * */
public class MonthDate extends View {
    private static final int NUM_COLUMNS = 7;
    private static final int NUM_ROWS = 6;
    private Paint mPaint;
    private int mDayColor = Color.parseColor("#000000");
    private int mSelectDayColor = Color.parseColor("#aabbcc");
    private int mSelectBGColor = Color.parseColor("#ffffff");
    private int mCurrentColor = Color.parseColor("#ff0000");
    private int mCurrYear,mCurrMonth,mCurrDay;//现在的年月日
    private int mSelYear,mSelMonth,mSelDay;//选择的年月日
    private int mColumnSize,mRowSize;
    private DisplayMetrics mDisplayMetrics;
    private int mDaySize = 18;
    private TextView tv_date;
    private int [][] daysString;
    private DateClick dateClick;
    public MonthDate(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDisplayMetrics = getResources().getDisplayMetrics();
        Calendar calendar = Calendar.getInstance();
        mPaint = new Paint();
        mCurrYear = calendar.get(Calendar.YEAR);
        mCurrMonth = calendar.get(Calendar.MONTH);
        mCurrDay = calendar.get(Calendar.DATE);
        setSelectYearMonth(mCurrYear,mCurrMonth,mCurrDay);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initSize();
        daysString = new int[6][7];
        mPaint.setTextSize(mDaySize*mDisplayMetrics.scaledDensity);
        String dayString;
        int mMonthDays = DateUtils.getMonthDays(mSelYear, mSelMonth);
        int weekNumber = DateUtils.getFirstDayWeek(mSelYear, mSelMonth);
        Log.d("DateView", "DateView:" + mSelMonth+"月1号周" + weekNumber);
        for(int day = 0;day < mMonthDays;day++){
            dayString = (day + 1) + "";
            int column = (day+weekNumber - 1) % 7;
            int row = (day+weekNumber - 1) / 7;
            daysString[row][column]=day + 1;
            int startX = (int) (mColumnSize * column + (mColumnSize - mPaint.measureText(dayString))/2);
            int startY = (int) (mRowSize * row + mRowSize/2 - (mPaint.ascent() + mPaint.descent())/2);
            if(dayString.equals(mSelDay+"")){
                //绘制背景色矩形
                int startRecX = mColumnSize * column;
                int startRecY = mRowSize * row;
                int endRecX = startRecX + mColumnSize;
                int endRecY = startRecY + mRowSize;
                mPaint.setColor(mSelectBGColor);
                canvas.drawRect(startRecX, startRecY, endRecX, endRecY, mPaint);

            }

            if(dayString.equals(mSelDay+"")){
                mPaint.setColor(mSelectDayColor);
            }else if(dayString.equals(mCurrDay+"") && mCurrDay != mSelDay && mCurrMonth == mSelMonth){
                //正常月，选中其他日期，则今日为红色
                mPaint.setColor(mCurrentColor);
            }else{
                mPaint.setColor(mDayColor);
            }
            canvas.drawText(dayString, startX, startY, mPaint);
            if(tv_date != null){
                tv_date.setText(mSelYear + "-"+ (mSelMonth + 1));
            }
        }
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    private int downX = 0,downY = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventCode=  event.getAction();
        switch(eventCode){
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                downY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                int upX = (int) event.getX();
                int upY = (int) event.getY();
                if(Math.abs(upX-downX) < 10 && Math.abs(upY - downY) < 10){
                    performClick();
                    doClickAction((upX + downX)/2,(upY + downY)/2);
                }
                break;
        }
        return true;
    }

    //初始化列宽行高
    private void initSize(){
        mColumnSize = getWidth() / NUM_COLUMNS;
        mRowSize = getHeight() / NUM_ROWS;
    }

    //设置年月
    private void setSelectYearMonth(int year,int month,int day){
        mSelYear = year;
        mSelMonth = month;
        mSelDay = day;
    }
    //执行点击事件
    private void doClickAction(int x,int y){
        int row = y / mRowSize;
        int column = x / mColumnSize;
        setSelectYearMonth(mSelYear,mSelMonth,daysString[row][column]);
        invalidate();
        //执行activity发送过来的点击处理事件
        if(dateClick != null){
            dateClick.onClickOnDate();
        }
    }
//左点击，日历向后翻页

    public void onLeftClick(){
        int year = mSelYear;
        int month = mSelMonth;
        int day = mSelDay;
        if(month == 0){//如果是1月份则变成上一年的十二月份
            year = mSelYear-1;
            month = 11;
        }else if(DateUtils.getMonthDays(year, month) == day){
            //如果当前日期为该月最后一点，当向前推的时候，就需要改变选中的日期
            month = month-1;
            day = DateUtils.getMonthDays(year, month);
        }else{
            month = month-1;
        }
        setSelectYearMonth(year,month,day);
        invalidate();
    }
    //右点击，日历向后翻页
    public void onRightClick(){
        int year = mSelYear;
        int month = mSelMonth;
        int day = mSelDay;
        if(month == 11){//如果是12月份则变成下一年的1月份
            year = mSelYear+1;
            month = 0;
        }else if(DateUtils.getMonthDays(year, month) == day){
            month = month + 1;
            day = DateUtils.getMonthDays(year, month);
        }else{
            month = month + 1;
        }
        setSelectYearMonth(year,month,day);
        invalidate();
    }

    //获取选择的年份
    public int getmSelYear() {
        return mSelYear;
    }
    //获取选择的月份
    public int getmSelMonth() {
        return mSelMonth;
    }
    //获取选择的日期
    public int getmSelDay() {
        return this.mSelDay;
    }
    //普通日期的字体颜色黑色
    public void setmDayColor(int mDayColor) {
        this.mDayColor = mDayColor;
    }

    //选择日期的颜色白色
    public void setmSelectDayColor(int mSelectDayColor) {
        this.mSelectDayColor = mSelectDayColor;
    }

    //选中日期的背景颜色蓝色
    public void setmSelectBGColor(int mSelectBGColor) {
        this.mSelectBGColor = mSelectBGColor;
    }
    //今天不是选中的日期红色
    public void setmCurrentColor(int mCurrentColor) {
        this.mCurrentColor = mCurrentColor;
    }
    //日期的大小
    public void setmDaySize(int mDaySize) {
        this.mDaySize = mDaySize;
    }
    //显示当前日期的控件
    public void setTextView(TextView tv_date,TextView tv_week){
        this.tv_date = tv_date;
        invalidate();
    }

    //设置日期的点击回调事件
    public interface DateClick{
        public void onClickOnDate();
    }

    //设置日期点击事件
    public void setDateClick(DateClick dateClick) {
        this.dateClick = dateClick;
    }

    //点击今天按钮，跳转至今天
    public void setTodayToView(){
        setSelectYearMonth(mCurrYear,mCurrMonth,mCurrDay);
        invalidate();
    }
}
