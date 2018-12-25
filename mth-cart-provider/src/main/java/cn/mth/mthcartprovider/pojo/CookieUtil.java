package cn.mth.mthcartprovider.pojo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    /**
     * 功能描述: 获取cookie
     *
     * @param:
     * @return:
     * @auther: Yrj
     * @date: 2018/12/19 16:20
     */
    public static String getCookie(HttpServletRequest request, String cookieName) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }



    public static void writeCookie(HttpServletResponse response, String cookieName, String value) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
