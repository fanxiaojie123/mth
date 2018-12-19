package cn.mth.mthuserprovider.controller;


import cn.mth.mthuserprovider.pojo.Student;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Api(value="/shopping", tags="购物车provider接口")
@RestController
public class ShoppingController {

    /**
     * 增加购物车商品
     * @param request
     * @param response
     * @param itemIdKey
     * @return
     */
    @ApiOperation(value="增加购物车商品", notes = "增加购物车商品")

    @ApiImplicitParams({
            @ApiImplicitParam(name="student", value="购物车对象", dataType = "Student")
            ,@ApiImplicitParam(name="itemIdKey", value="商品ID", dataType = "java.lang.Long")
    })
    @RequestMapping(value = "/addShopping",method = RequestMethod.POST)
    public String addShopping( HttpServletRequest request,
                               HttpServletResponse response,
                                @RequestBody Student student,
                                @RequestParam("itemIdKey") Long itemIdKey)  {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return JSON.toJSONString(student);
    }

    /**
     * 删除购物车商品
     * 传入购物车实体类
     * @return
     */
    @ApiOperation(value="删除购物车商品", notes = "删除购物车商品")

    @ApiImplicitParams({
            @ApiImplicitParam(name="itmeId", value="itmeId", dataType = "List")
    })
    @RequestMapping(value = "/deleteShopping",method = RequestMethod.POST)
    public String deleteShopping(@RequestParam("request") HttpServletRequest request,
                                 @RequestParam("response") HttpServletResponse response,
                               @RequestBody List<Long> itmeId)  {
        response.setHeader("Access-Control-Allow-Origin", "*");

        return "123";
    }

    /**
     * 显示购物车商品
     * 传入购物车实体类
     * @return
     */
    @ApiOperation(value="显示购物车商品", notes = "显示购物车商品")
    @RequestMapping(value = "/showShopping",method = {RequestMethod.POST,RequestMethod.GET})
    public String showShopping( HttpServletRequest request,
                               HttpServletResponse response)  {
        response.setHeader("Access-Control-Allow-Origin", "*");
       return "heheh";
    }



    /**
     * 修改购物车商品
     * 传入购物车实体类
     * @return
     */
    @ApiOperation(value="修改购物车商品", notes = "修改购物车商品，num>0,增加,num<0,减少")

    @ApiImplicitParams({
            @ApiImplicitParam(name="num", value="num", dataType = "int")
            ,@ApiImplicitParam(name="itmeId", value="itmeId", dataType = "Long")
    })
    @RequestMapping(value = "/changeShopping",method = RequestMethod.POST)
    public String changeShopping(@RequestParam("request") HttpServletRequest request,
                                 @RequestParam("response") HttpServletResponse response,
                                 @RequestParam("itmeId") Long itmeId ,
                                 @RequestParam("num") Integer num)  {
        response.setHeader("Access-Control-Allow-Origin", "*");

        return "hahah";
    }

}
