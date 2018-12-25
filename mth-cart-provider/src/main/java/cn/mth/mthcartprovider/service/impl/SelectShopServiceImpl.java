package cn.mth.mthcartprovider.service.impl;

import cn.mth.mthcartprovider.pojo.ShoppingCarts;
import cn.mth.mthcartprovider.service.SelectShopService;
import cn.mth.mthcartprovider.util.PutRedis;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class SelectShopServiceImpl implements SelectShopService {

    @Autowired
    private PutRedis putRedis;

    /**
     * 显示商品
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @Override
    public String showItemInCard(HttpServletRequest request, HttpServletResponse response) throws IOException {
//判断是否登陆
        String code = request.getHeader("code");
        String userId = request.getHeader("userId");
        Map<String,Object> mapRe = new HashMap<>();
        System.out.println("code"+code);
        if (code == null || userId == null) {
            mapRe.put("code", "400");
            mapRe.put("info", "找不到header信息...");
            String string = JSON.toJSONString(mapRe);
            return string;
        }

        //获取cookie中购物车信息
        Cookie[] cookies = request.getCookies();
        ObjectMapper om = new ObjectMapper();
        ShoppingCarts shoppingCarts = null;
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //获取cookie中的购物车数据
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("BUYCAT".equals(cookie.getName())) {
                    System.out.println("进入service2.0"+cookie.getName());
                    String value = cookie.getValue();
                    String decode = URLDecoder.decode(value, "utf-8");
                    // 购物车 对象 与json字符串互转
                    shoppingCarts = om.readValue(decode, ShoppingCarts.class);
                    break;
                }
            }
        }
        //如果cookie中没有购物车信息
        if(shoppingCarts == null){
            shoppingCarts = new ShoppingCarts();
        }
        if(!code.equals("200")){
            //未登录，将信息存到cookie中
            System.out.println("weidenglu!!!");
            mapRe.put("code","200");
            mapRe.put("info","展示商品成功...");
            mapRe.put("result",shoppingCarts);
            String string = JSON.toJSONString(mapRe);
            return string;
        }else{
            ShoppingCarts shoppingCartsinRedis = putRedis.get(userId);

            putRedis.insertBuyerCartToRedis(shoppingCarts,userId);

            mapRe.put("code","200");
            mapRe.put("info","展示商品成功...");
            mapRe.put("result",shoppingCartsinRedis);
            String string = JSON.toJSONString(mapRe);
            Cookie nescookie = new Cookie("BUYCAT", null);
            System.out.println("删除cookie保存到了redis"+nescookie);
            nescookie.setMaxAge(0); //立即删除型

            nescookie.setPath("/"); //项目所有目录均有效，这句很关键，否则不敢保证删除
            response.addCookie(nescookie);
            return string;
        }


    }
}

