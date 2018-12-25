package cn.mth.mthcartprovider.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface DeleteShopService {
    /**
     * 删除购物车中的商品
     * @param
     * @return
     */
    String deleteItemInCard(HttpServletRequest request, HttpServletResponse response, List<Long> shoPids)throws IOException; ;

}
