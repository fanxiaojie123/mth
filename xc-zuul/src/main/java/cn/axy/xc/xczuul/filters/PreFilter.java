package cn.axy.xc.xczuul.filters;

import cn.axy.xc.xczuul.jwt.JWTUtil;
import cn.axy.xc.xczuul.jwt.JwtSecret;
import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Component
public class PreFilter extends ZuulFilter {

    @Override
    public String filterType() {//过滤器类型
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {//同类型的 数字越大 优先级越低
        return 0;
    }

    @Override
    public boolean shouldFilter() {//是否开启此过滤器
        return true;
    }

    @Override
    public Object run() {
        log.info("进入PRE过滤器....");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Map<String,Object> map = new HashMap<>();
        String token = request.getHeader("token");
        String info = request.getHeader("info");

        if ("1".equals(info)) {
            //如果是1表示需要进行 jwt 的判断
            log.info("进行jwt判断....");
            //从token中获取用户id
            Integer userid = JWTUtil.getUserId(token);
            //判断token是否是在全局变量tokenMap中
            if(JwtSecret.tokenMap.containsKey(userid)) {
                //在tokrnmap中
                if (token == null || token.equals("")) {
                    log.info("token为空...");
                    ctx.setSendZuulResponse(false);
                    ctx.setResponseStatusCode(401);
                    map.put("code","401");
                    map.put("msg","No token information");
                    ctx.setResponseBody(JSON.toJSONString(map));
                    return "access denied";
                }else if(JWTUtil.verify(token)){
                    //如果验证通过
                    log.info("token验证通过...");
                    ctx.getResponse().addHeader("token",token);
                    ctx.set("postfilter", false);
                    return "pass";

                }else {
                    log.info("token验证没通过...");
                    ctx.setSendZuulResponse(false);
                    ctx.setResponseStatusCode(500);
                    map.put("code","500");
                    map.put("msg","token verification failed");
                    ctx.setResponseBody(JSON.toJSONString(map));
                    return "access denied";
                }
            }else{
                //不在tokenmap中
                log.info("token不在tokenmap中...");
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(401);
                map.put("code","402");
                map.put("msg","No token not in tokenMap");
                ctx.setResponseBody(JSON.toJSONString(map));
                return "access denied";
            }
        } else if ("0".equals(info)) {
            //如果是0 表示不需要进行 token的判断
            //需要走一下返回的值，吧token放到 header 中
            log.info("登陆请求，无需进行token判断...");
            ctx.set("postfilter", true);

            return "pass";
        } else if("2".equals(info)) {
            log.info("不需要进行登陆验证就可以通过的...");
            ctx.getResponse().addHeader("token",token);
            ctx.set("postfilter", false);

            if(token == null || token.equals("")){
                ctx.getResponse().addHeader("code","202");

            }else {
                Integer userid = JWTUtil.getUserId(token);
                if(JWTUtil.verify(token) && JwtSecret.tokenMap.containsKey(userid)){
                    //有tokrn验证通过
                    ctx.getResponse().addHeader("code","200");
                }else {
                    ctx.getResponse().addHeader("code","201");
                }
            }

            return "pass";
        } else {
            //如果是其他数字，传参错误 直接返回
            log.info("参数info错误...");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(400);
            map.put("code","400");
            map.put("msg","parameter error");
            ctx.setResponseBody(JSON.toJSONString(map));
            return "access denied";
        }
    }
}

