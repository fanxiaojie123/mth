package cn.mth.mthcartprovider.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface SelectShopService {
    /**
     * 展示购物车商品
     * @return
     */

    String showItemInCard(HttpServletRequest request, HttpServletResponse response)throws IOException;

}
