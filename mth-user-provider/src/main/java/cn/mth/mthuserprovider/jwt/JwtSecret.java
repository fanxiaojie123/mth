package cn.mth.mthuserprovider.jwt;


import javax.print.attribute.standard.PrinterURI;
import java.util.HashMap;
import java.util.Map;

public class JwtSecret {
    /**
     * 秘钥，uuid 可以 修改为 生成秘钥的方法
     */
    public static final String TheJwtScret = UUIDUtil.getUUID();

    // 过期时间 24 小时
    public static final int EXPIRE_TIME = 60 * 24 * 60 * 1000;


    public static Map<Integer,String> tokenMap = new HashMap<>();
 }
