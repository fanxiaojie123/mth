package cn.mth.mthcartprovider.controller;

import cn.mth.mthcartprovider.pojo.ShoppingCart;
import cn.mth.mthcartprovider.service.AddShopService;
import cn.mth.mthcartprovider.service.DeleteShopService;
import cn.mth.mthcartprovider.service.SelectShopService;
import cn.mth.mthcartprovider.service.UpdateShopCarService;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import java.io.IOException;
import java.util.List;
@Api(value="/shopping", tags="购物车provider接口")

@RestController
public class ShoppingController {
@Autowired
private AddShopService addShopService;
@Autowired
private DeleteShopService deleteShopServicee;
@Autowired
private SelectShopService selectShopService;
@Autowired
private UpdateShopCarService updateShopCarService;
//测试
    @RequestMapping("/s")
    public String getall(){
        return "ss";
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name="shoppingCart", value="购物车对象", dataType = "ShoppingCart")
            ,@ApiImplicitParam(name="itemIdKey", value="商品ID", dataType = "java.lang.Long")
    })
    @RequestMapping(value = "/addshop" ,method = RequestMethod.POST)
    public String addShopping(HttpServletRequest request,
                              HttpServletResponse response,
                             @RequestBody ShoppingCart shoppingCart,
                             @RequestParam("itemIdKey") Long itemIdKey){
        response.setHeader("Access-Control-Allow-Origin","*");
        String re = null;
    System.out.println(JSON.toJSONString(shoppingCart));
    try {
        re = addShopService.addShopCar(request, response, shoppingCart, itemIdKey);
    } catch (IOException e) {
        e.printStackTrace();
    }
    return re;

}
    /**
     * 删除
     * @param request
     * @param response
     * @param
     * @return
     */

    @ApiOperation(value="删除购物车商品", notes = "删除购物车商品")

    @ApiImplicitParams({
            @ApiImplicitParam(name="itmeId", value="itmeId", dataType = "List")
    })
    @RequestMapping(value = "/deleteShopping",method = RequestMethod.POST)
    public String deleteShopping( HttpServletRequest request,
                                  HttpServletResponse response,
                                 @RequestBody List<Long> shoPid)  {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String re = null;
        try {
            re = deleteShopServicee.deleteItemInCard(request,response,shoPid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return re;
    }
    /**
     * 显示购物车商品
     * 传入购物车实体类
     * @return
     */
    @ApiOperation(value="显示购物车商品", notes = "显示购物车商品")
    @RequestMapping(value = "/showShopping",method = RequestMethod.POST)
    public String showShopping( HttpServletRequest request,
                                HttpServletResponse response)  {
        response.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println("前台传入");
        String re = null;
        try {
            re = selectShopService .showItemInCard(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return re;
    }
    @ApiOperation(value="修改购物车商品", notes = "修改购物车商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name="num", value="num", dataType = "int")
            ,@ApiImplicitParam(name="itmeId", value="itmeId", dataType = "Long")
    })
    @RequestMapping("/updateShopping")
    public String update(HttpServletRequest request,
                         HttpServletResponse response,
                         @RequestParam("shoPid") Long shoPid ,
                         @RequestParam("num") Integer num ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String re = null;
        try {
            re = updateShopCarService.updateShopcar(request, response, shoPid, num);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return re;
    }
}
