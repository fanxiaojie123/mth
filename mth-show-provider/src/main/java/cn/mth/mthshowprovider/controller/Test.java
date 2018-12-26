package cn.mth.mthshowprovider.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public  void showtime(){
        Date day=new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(df.format(day));
    }

}
