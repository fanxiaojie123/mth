package cn.mth.mthuserprovider.util;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class Util {
    /**
     * 判断字符串是否是电话号码
     * @param phone 要判断的字符串
     * @return 返回true表示是电话号码 false 不是电话号码
     */
    public static boolean isPhone(String phone){
        Pattern pattern = Pattern
                .compile("^(13[0-9]|15[0-9]|153|15[6-9]|180|18[2-3]|176|18[5-9])\\d{8}$");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 描述：是否是邮箱.
     * @param str 指定的字符串
     * @return 是否是邮箱:是为true，否则false
     */
    public static Boolean isEmail(String str) {
        Boolean isEmail = false;
        String expr = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$";

        if (str.matches(expr)) {
            isEmail = true;
        }
        return isEmail;
    }

    /*
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean isNumeric(String str){
        if(str != null){
            for(int i=str.length();--i>=0;){
                int chr=str.charAt(i);
                if(chr<48 || chr>57)
                    return false;
            }
        }else{
            return false;
        }
        return true;
    }


    /**
     * 描述：是否是银行卡.
     * @param str 指定的字符串
     * @return 是否是银行卡:是为true，否则false
     */
    public static Boolean isBankCard(String str) {
        Boolean isEmail = false;
        String expr = "^([1-9]{1})(\\d{12}|\\d{18})$";

        if (str.matches(expr)) {
            isEmail = true;
        }
        return isEmail;
    }

    /**
     * 获得一个流水号
     */
    public static Integer getSerialNum(){
        Date current = new Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "MMddHHmmss");
        String time = sdf.format(current);
        int i = Integer.parseInt(time);
        System.out.println(i);
        return i;
    }

    //判断邮政编码
    public static boolean isEms(String ems){
        String reg = "[1-9]\\d{5}";
        return Pattern.matches(reg, ems);
    }

    //去除字符创中的\
    public static String delThe(String str) {

        String s2[] = str.split("\\\\");
        String temp1 = "";
        for(int i = 0;i<s2.length;i++){
            temp1 += s2[i];
        }

        System.out.println(temp1);
        return temp1;
    }

}
