package com.example.administrator.initiatepagedata.JsonData;

/**
 * Created by Administrator on 2017/5/4.
 */

public enum ActivityState {


    /*0.已保存 1.已提交 2.已激活 3.已开始 已结束*/

    saved( 0 ), submitted( 1 ), activated( 2 ),started(3),finished(4) {
        @Override
        public boolean isRest() {
            return true ;
        }
    };

    private int value;
    private ActivityState( int value) {
        this .value = value;
    }
    public int getValue() {
        return value;
    }
    public boolean isRest() {
        return false ;
    }
}
