package com.example.administrator.initiatepagedata.JavaBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 */

public class UserApplyInfoBean {

    /**
     * success : true
     * data : [{"orderID":"201707101551944573","UserID":"15888888888","userState":"1","orderDate":"2017-07-10 15:51:33"}]
     */

    private boolean success;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * orderID : 201707101551944573
         * UserID : 15888888888
         * userState : 1
         * orderDate : 2017-07-10 15:51:33
         */

        private String orderID;
        private String UserID;
        private String userState;
        private String orderDate;

        public String getOrderID() {
            return orderID;
        }

        public void setOrderID(String orderID) {
            this.orderID = orderID;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public String getUserState() {
            return userState;
        }

        public void setUserState(String userState) {
            this.userState = userState;
        }

        public String getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }
    }
}
