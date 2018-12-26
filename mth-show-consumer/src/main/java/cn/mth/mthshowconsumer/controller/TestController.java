package cn.mth.mthshowconsumer.controller;

import cn.mth.mthshowconsumer.pojo.Talkcentre;
import cn.mth.mthshowconsumer.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @Autowired
    private TalkService talkService;

    @RequestMapping("/getall")
    public String getall(){

       return talkService.getall();
    }
    @RequestMapping(value = "/alltalk", produces = "text/plain;charset=utf-8")
    public String idTalk(Integer shopid){

        return talkService.getAllTalk(shopid);
    }

    @RequestMapping(value = "/addtalk", produces = "text/plain;charset=utf-8")
    public String idTalk(Talkcentre talkcentre){

        return talkService.addTalk(talkcentre);
    }

    @RequestMapping(value = "/twotalk", produces = "text/plain;charset=utf-8")
    public String twoTalk(Talkcentre talkcentre){

        return talkService.twoTalk(talkcentre);
    }


    @RequestMapping(value = "/shoptalk", produces = "text/plain;charset=utf-8")
    public String shopTalk(@RequestBody  Talkcentre talkcentre){

        return talkService.shopTalk(talkcentre);
    }


}
