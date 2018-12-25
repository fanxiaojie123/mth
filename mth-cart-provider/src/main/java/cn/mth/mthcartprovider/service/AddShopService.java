package cn.mth.mthcartprovider.service;

import cn.mth.mthcartprovider.pojo.ShoppingCart;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AddShopService {
    /**
     *   登陆 存redis   未登录 存cookie
     * @param request
     * @param response
     * @param shopCarsList
     * @param shoPid
     * @return
     */

    String addShopCar(@RequestParam HttpServletRequest request, @RequestParam HttpServletResponse response, ShoppingCart shopCarsList, Long shoPid)throws IOException;



}
