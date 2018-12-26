package cn.mth.mthshowprovider.controller;

import cn.mth.mthshowprovider.pojo.Talkcentre;
import cn.mth.mthshowprovider.service.TalkcentreService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

@RestController
public class TalkController {
    // @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    /**
     * 测试
     */
    @Autowired
    private TalkcentreService talkcentreService;
    @RequestMapping(value = "/getall", produces = "text/plain;charset=utf-8")
    public String getall(){
      return    talkcentreService.getall();
    }

    /**
     * 根据商品id查看各种评论
     * @param shopid
     * @return
     */
    @RequestMapping(value = "/alltalk", produces = "text/plain;charset=utf-8")
    public String allTalk(Integer shopid){
        return talkcentreService.getAllTalk(shopid);

    }

    @RequestMapping(value = "/deltalk", produces = "text/plain;charset=utf-8")
    public String delTalk(Integer tid){
        return talkcentreService.delTalk(tid);

    }


    @RequestMapping(value = "/addtalk", produces = "text/plain;charset=utf-8")
    public String addTalk(@RequestBody Talkcentre talkcentre){


        return talkcentreService.addTalk(talkcentre);

    }

    @RequestMapping(value = "/twotalk", produces = "text/plain;charset=utf-8")
    public String twoTalk(@RequestBody Talkcentre talkcentre){
        return  talkcentreService.twoTalk(talkcentre);
    }


    @RequestMapping(value = "/shoptalk",method = RequestMethod.POST)
    public String shopTalk(@RequestBody Talkcentre talkcentre){
        System.out.println("进入controller");
        System.out.println(talkcentre);
        return  talkcentreService.shopTalk(talkcentre);
    }

    /**
     * 分页
     * @param page
     * @param pageSize
     * @return
     */

    @RequestMapping("/page")
    public String finall(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize){

        return JSON.toJSONString( talkcentreService.findall(page,pageSize));
    }

    @RequestMapping("/aaa")
    public String add(){
        return talkcentreService.get();
    }
}
