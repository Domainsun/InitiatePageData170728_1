package com.example.administrator.initiatepagedata.JsonData;

/**
 * Created by Administrator on 2017/7/28.
 */

public enum UserState {

    /* 0.已申请 1.已预约 2.开始未确认 3.已开始 4.未支付 5.已支付 6.已取消*/


    applied(0),appointment(1),unconfirmed(2),started(3),notPay(4),paid(5),canceled(6){

        @Override
        public boolean isRest() {
            return true ;
        }
    };

    private int value;
    private UserState( int value) {
            this .value = value;
            }
    public int getValue() {
            return value;
            }
    public boolean isRest() {
            return false ;
            }
}
