package cn.mth.mthuserprovider.util;

import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Md5 {
    private static Logger log = Logger.getLogger(Md5.class.getClass());

    public static final String KEY_MD5 = "MD5";


    public static  String  getResult(String inputStr)
    {
        BigInteger bigInteger=null;
        log.info("加密前" + inputStr);
        try {
            MessageDigest md = MessageDigest.getInstance(KEY_MD5);
            byte[] inputData = inputStr.getBytes();
            md.update(inputData);
            bigInteger = new BigInteger(md.digest());
        } catch (Exception e) {e.printStackTrace();}

        String string = bigInteger.toString(16);
        log.info("加密后" + string);
        return string;
    }

    public static void main(String args[])
    {
        try {
            String inputStr = "123456";
            String result = getResult(inputStr);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
