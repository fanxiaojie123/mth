package cn.axy.xc.xczuul.filters;

import cn.axy.xc.xczuul.jwt.JWTUtil;
import cn.axy.xc.xczuul.jwt.JwtSecret;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;


@Slf4j
@Component
public class PostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 100;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.sendZuulResponse() && (Boolean) ctx.get("postfilter");
    }

    @Override
    public Object run() {
        log.info("进入POST过滤器....");
        try {
            RequestContext context = RequestContext.getCurrentContext();

            InputStream stream = context.getResponseDataStream();
            String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));

            Map map = JSONObject.parseObject(body, Map.class);
            Integer userId = (Integer) map.get("userId");
            String userName = (String) map.get("userName");
            log.info("post过滤器获取到的userId:" + userId);
            log.info("post过滤器获取到的userName:" + userName);
            String token = JWTUtil.createToken(userName, userId);

            //token放入tokenmap
            JwtSecret.tokenMap.put(userId,token);
            //token放入返回头中
            context.getResponse().addHeader("token",token);
            //token放入cookie中
            Cookie cookie = new Cookie("token",token);
            cookie.setMaxAge(JwtSecret.EXPIRE_TIME);
            cookie.setValue(token);
            context.getResponse().addCookie(cookie);



            log.info("token的值：" + token);
            log.info("返回页面的值" + body);
            context.setResponseBody(body);

        } catch (IOException e) {
            log.error("Post过滤器IO异常....");
        }

        return null;
    }
}
