package cn.mth.mthshowprovider.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ssss {
   /* public static void main(String[] args) {
       String talktime= showtime();
        System.out.println(talktime);
    }*/
    public   String showtime(){
        Date day=new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


       String time= df.format(day);
      //  System.out.println(time);
        return time;
    }
}
