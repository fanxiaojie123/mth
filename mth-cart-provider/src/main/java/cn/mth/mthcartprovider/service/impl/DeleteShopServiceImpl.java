package cn.mth.mthcartprovider.service.impl;

import cn.mth.mthcartprovider.pojo.ShoppingCarts;
import cn.mth.mthcartprovider.service.DeleteShopService;
import cn.mth.mthcartprovider.util.PutRedis;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.StringWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service

public class DeleteShopServiceImpl implements DeleteShopService {
    @Autowired
    private PutRedis putRedis;

    @Override
    public String deleteItemInCard( HttpServletRequest request,  HttpServletResponse response, List<Long> shoPids) throws IOException {
        //判断是否登陆
        String code = request.getHeader("code");
        String userId = request.getHeader("userId");

        Map<String, Object> mapRe = new HashMap<>();

        if (code == null || userId == null) {
            mapRe.put("code", "400");
            mapRe.put("info", "找不到header信息...");
            String string = JSON.toJSONString(mapRe);
            return string;
        }
        if (!code.equals("200")) {//不等于200就是非登录

            //获取cookie中购物车信息
            Cookie[] cookies = request.getCookies();
            ObjectMapper om = new ObjectMapper();
            ShoppingCarts shoppingCarts = null;
            om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            //获取cookie中的购物车数据
            if (null != cookies && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if ("BUYCAT".equals(cookie.getName())) {
                        String value = cookie.getValue();
                        String decode = URLDecoder.decode(value, "utf-8");
                        System.out.println("删除cookie");
                        // 购物车 对象 与json字符串互转
                        shoppingCarts = om.readValue(decode, ShoppingCarts.class);
                        break;
                    }
                }
            }
            //如果cookie中没有购物车信息
            if (shoppingCarts == null) {
                shoppingCarts = new ShoppingCarts();
            }
            for (Long itmeId : shoPids) {
                boolean b = shoppingCarts.removeItem(itmeId);
                if (!b) {
                    mapRe.put("code", "500");
                    mapRe.put("info", "删除商品列表失败...");
                    mapRe.put("result", shoppingCarts);
                    System.out.println("删除商品列表失败，失败id" + itmeId);
                    String string = JSON.toJSONString(mapRe);
                    return string;

                }
            }
            //如果未登陆就加入cookie
            Writer w = new StringWriter();
            om.writeValue(w, shoppingCarts);

            System.out.println("写入cookie的信息：" + w.toString());
            String cookieString = w.toString();
            //url
            String encode = URLEncoder.encode(cookieString, "utf-8");

            Cookie cookie = new Cookie("BUYCAT",encode);

            cookie.setPath("/");
            //设置Cookie过期时间: -1 表示关闭浏览器失效  0: 立即失效  >0: 单位是秒, 多少秒后失效
            //一天失效
            cookie.setMaxAge(24 * 60 * 60);
            //Cookie写回浏览器
            response.addCookie(cookie);
            mapRe.put("code","200");
            mapRe.put("info","删除商品成功...");
            mapRe.put("result",shoppingCarts);
            String s = JSON.toJSONString(mapRe);
            return s;

        }else {
            ShoppingCarts shoppingCarts = putRedis.get(userId);
            //删除购物车中的信息
            for (Long itmeId : shoPids){
                boolean b = shoppingCarts.removeItem(itmeId);
                if(!b){
                    mapRe.put("code","500");
                    mapRe.put("info","删除商品列表失败...");
                    mapRe.put("result",shoppingCarts);
                    String string = JSON.toJSONString(mapRe);
                    System.out.println("删除商品列表失败，失败id" + itmeId);
                    return string;
                }
            }
            putRedis.set(userId,shoppingCarts);

            mapRe.put("code","200");
            mapRe.put("info","删除商品成功...");
            mapRe.put("result",shoppingCarts);
            String string = JSON.toJSONString(mapRe);
            return string;
        }
    }
}