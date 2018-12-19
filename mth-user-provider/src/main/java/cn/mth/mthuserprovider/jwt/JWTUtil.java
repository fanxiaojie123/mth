package cn.mth.mthuserprovider.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 *
 * @Description JWT 工具类
 * @Date 2018-04-07
 * @Time 22:48
 */
public class JWTUtil {

    // 密钥
    private static final String SECRET = JwtSecret.TheJwtScret;

    /**
     * 生成 jwt
     *
     * @param username 用户名
     * @return 加密的token
     */
    public static String createToken(String username,Integer userId) {
        try {
            Date date = new Date(System.currentTimeMillis() + JwtSecret.EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 附带username信息
            return JWT.create()
                    .withClaim("username", username)
                    .withClaim("userId",userId)
                    .withClaim("time",System.currentTimeMillis())
                    //到期时间
                    .withExpiresAt(date)
                    //创建一个新的JWT，并使用给定的算法进行标记
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 校验 jwt 是否正确
     *
     * @param token    密钥
     * @param username 用户名
     * @param userId 用户Id
     * @return 是否正确
     */
    public static boolean verify(String token, String username, Integer userId,Long time) {
        try {
            System.out.println(SECRET);

            Algorithm algorithm = Algorithm.HMAC256(SECRET);

            //在token中附带了username信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .withClaim("userId",userId)
                    .withClaim("time",time)
                    .build();
            //验证 jwt
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            //exception.printStackTrace();
            return false;
        }
    }

    /**
     * 不需要参数 验证 token
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        Integer userId = getUserId(token);
        String username = getUsername(token);
        Long time = getTime(token);


        boolean verify = verify(token, username, userId,time);
        return verify;
    }

    private static Long getTime(String token) {

        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("time").asLong();
        } catch (JWTDecodeException e) {
            return null;
        }

    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @return token中包含的用户Id
     */
    public static Integer getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

}
