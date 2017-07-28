package com.example.administrator.initiatepagedata.JsonData;

/**
 * Created by Administrator on 2017/5/4.
 */

public enum ActivityType {
    /**/
    activity( 1 ), tourism( 2 ), play( 3 ) {
        @Override
        public boolean isRest() {
            return true ;
        }
    };

    private int value;
    private ActivityType( int value) {
        this .value = value;
    }
    public int getValue() {
        return value;
    }
    public boolean isRest() {
        return false ;
    }
}
