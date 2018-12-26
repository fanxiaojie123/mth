package cn.mth.mthevaluateprovider.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) {

    }

    public  void showtime(){
      /*  Date day=new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(df.format(day));*/
        Date time=new Date();
        Timestamp timestamp=new Timestamp(time.getTime());
        System.out.println(timestamp);


    }


}
