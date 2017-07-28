package com.example.administrator.initiatepagedata;

import com.example.administrator.initiatepagedata.HttpUtils.ChangeAcitvityStateThread;
import com.example.administrator.initiatepagedata.JsonData.ActivityState;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2017/7/28.
 */

public class AcitivtyManagement  {


    private String personID;/*发起人id*/
    private Calendar[] validDuration;/*有效期*/
    private String []departure;/*出发市县*/
    private String details;/*详情*/
    private double stars;/*评价星级*/
    private int dealCount;/*成交量*/
    private int likeCount;/*收藏量*/
    private int personLimit;/*人数限制*/
    private List<String> photos;/*所有图片*/
    private ActivityState myActivityState;  /*0.已保存 1.已提交 2.已激活 3.已开始 4.已结束*/
    private String activityID;  //20170505+ID+00001  活动id
    private String title;/*标题*/
    private double price;/*价格*/
    private double costTime;/*用时*/
    private String []destination;/*目的市县*/
    private Boolean hasCar;/*有无车*/
    private String photoID1;/*图片1*/
    private int myActivityType;/*活动类型*/





    String changeAcitvityStateThreadResult;
    public void changeAcitivityState(int stateCode) {
       switch (stateCode){
           case 0:
                this.myActivityState= ActivityState.activated;
               // TODO: 2017/7/28 保存订单信息到本地
               break;
           case 1:

               ChangeAcitvityStateThread tChangeAcitvityState =new ChangeAcitvityStateThread(activityID,"1");
               FutureTask<String> ftChangeAcitvityState = new FutureTask<>(tChangeAcitvityState);
               Thread ChangeAcitvityStateThread = new Thread(ftChangeAcitvityState);
               ChangeAcitvityStateThread.start();

               try {
                   changeAcitvityStateThreadResult=ftChangeAcitvityState.get();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               } catch (ExecutionException e) {
                   e.printStackTrace();
               }
               System.out.println(changeAcitvityStateThreadResult);

               this.myActivityState=ActivityState.submitted;

               break;
           case 2:


               break;
           case 3:
                /*1.所有用户预约已处理状态为已预约*
                  2.活动状态为已激活
                  3.改变服务器活动状态
                  4.改变本地活动状态
                 */


               break;
           case 4:

               break;
       }
    }





}
