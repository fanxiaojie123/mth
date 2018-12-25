package cn.mth.mthcartprovider.service.impl;

import cn.mth.mthcartprovider.pojo.ShoppingCart;
import cn.mth.mthcartprovider.pojo.ShoppingCarts;
import cn.mth.mthcartprovider.service.AddShopService;
import cn.mth.mthcartprovider.util.PutRedis;
import cn.mth.mthcartprovider.util.RedisUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.bcel.internal.classfile.Code;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import sun.rmi.runtime.Log;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Service
public class AddShopServiceImpl implements AddShopService {
    @Autowired
    private PutRedis putRedis;
    @Override
    public String addShopCar(@RequestParam HttpServletRequest request, @RequestParam HttpServletResponse response, ShoppingCart shoppingCart, @RequestParam Long shoPid)throws IOException {
        //结果集
        Map<String, Object> mapRe = new HashMap<>();
        //获取他的 登陆信息和用户id
        String code = request.getHeader("code");
        String userId = request.getHeader("userId");

      /*  if (code == null || userId == null) {
          mapRe.put("code","400");
          mapRe.put("info","找不到header信息");
            String string = JSON.toJSONString(mapRe);
            return string;
        }*/
        //获取本地cookie中的商品  无论是否登录都会获取cookie

        Cookie[] cookies = request.getCookies();
        ObjectMapper om = new ObjectMapper();
        ShoppingCarts shoppingCarts = null;
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        if(null != (cookies) && cookies.length > 0){
            for (Cookie cookie : cookies){
                if ("BUYCAT".equals(cookie.getName())){//验证有没有 cookie
                    String value = cookie.getValue();
                    String decode =URLDecoder.decode(value, "utf-8");

                    // 购物车 对象 与json字符串互转  反序列化
                    shoppingCarts = om.readValue(decode, ShoppingCarts.class);
                    break;
                }
            }
        }
        //如果cookie中没有购物车信息
        if(shoppingCarts == null){
            shoppingCarts = new ShoppingCarts();
        }
        Long itemId1 = shoppingCart.getShoPid();//商品id
        Integer itemNum = shoppingCart.getItemNum();//商品数量
        //添加购物车
        if (itemId1 != 0 && itemNum > 0){
            boolean b =shoppingCarts.addItemInCard(shoPid,shoppingCart.getItemNum(),shoppingCart);
            if (!b) {
                mapRe.put("code","500");
                mapRe.put("info","添加购物车失败，参数错误...");
                mapRe.put("result",shoppingCarts);
                String string = JSON.toJSONString(mapRe);
                return string;
            }
        }
        if (userId != null) {
            //有id信息的放到redis里并且把cookie里的也放到cookies
            System.out.println("开启redis");
            putRedis.insertBuyerCartToRedis(shoppingCarts,userId);
            //清空cookie
            Cookie cookie =new Cookie("BUYCAT",null);
            cookie.setPath("/");
            cookie.setMaxAge(-0);
            response.addCookie(cookie);

            ShoppingCarts shoppingCarts1 =putRedis.get(userId);
            mapRe.put("result",shoppingCarts1);

        } else {
            Writer w = new StringWriter();
            om.writeValue(w, shoppingCarts);
            System.out.println("未登录,开始添加cookie");
            System.out.println("写入cookie的信息：" + w.toString());
            String cookieString = w.toString();

            String encode = URLEncoder.encode(cookieString, "utf-8");
            Cookie cookie = new Cookie("BUYCAT", encode);
            //设置path是可以共享cookie 咋样也能看见
            cookie.setPath("/");
            //设置Cookie过期时间: -1 表示关闭浏览器失效  0: 立即失效  >0: 单位是秒, 多少秒后失效
            cookie.setMaxAge(24 * 60 * 60);//一天失效
            response.addCookie(cookie);
            mapRe.put("result",shoppingCarts);  //Cookie写回浏览器

        }
        mapRe.put("code","200");
        mapRe.put("info","添加成功");

        String string = JSON.toJSONString(mapRe);
        return string;
    }
    }

