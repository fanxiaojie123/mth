package cn.mth.mthshowconsumer.service;

import cn.mth.mthshowconsumer.pojo.Talkcentre;
import cn.mth.mthshowconsumer.service.impl.TalkServiceImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "MTH-SHOWW-PROVIDER",fallback = TalkServiceImpl.class)
public interface TalkService {
    /**
     * 测试
     * @return
     */
    @RequestMapping(value = "/getall", method = RequestMethod.GET)
   public String getall();
    /**
     * 商家,用户 查看此商品下的各种评论
     * @param
     * @return
     */
    @RequestMapping(value = "/alltalk", method = RequestMethod.POST)
    public String getAllTalk(@RequestParam("shopid") Integer shopid);

    /**
     *收货评价的添加
     * @return
     */
    @RequestMapping(value = "/addtalk", produces = "text/plain;charset=utf-8")
    public String addTalk(@RequestBody Talkcentre talkcentre);

    /**
     * 追加评论
     * @param talkcentre
     * @return
     */
    @RequestMapping(value = "/twotalk", produces = "text/plain;charset=utf-8")
    public String twoTalk(@RequestBody Talkcentre talkcentre);

    /**
     *
     * @param talkcentre
     * @return
     */
    @RequestMapping(value = "/shoptalk",method = RequestMethod.POST)
    public String shopTalk(@RequestBody Talkcentre talkcentre);





}
