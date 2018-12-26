package cn.mth.mthevaluateprovider.controller;


import cn.mth.mthevaluateprovider.pojo.Talks;
import cn.mth.mthevaluateprovider.service.TalkcentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/getshoptalk")
    public String getAllTalk( Integer shopid){
      return talkcentreService.getAllTalk(shopid);
    }


    /**
     * 收获评论
     */
    @RequestMapping("/addtalks")
    public String addTalk(Talks talks){
        return talkcentreService.addTalk(talks);
    }

    /**
     * 追评
     * @param talks
     * @return
     */
    @RequestMapping("/twotalks")
    public String twoTalk(Talks talks){

        return talkcentreService.twoTalk(talks);
    }

    /**
     * 商家回复
     * @param talks
     * @return
     */
    @RequestMapping("/shoptalks")
    public String shopTalk(Talks talks){

        return talkcentreService.shopTalk(talks);
    }

    /**
     * 删除此违规评论
     * @param tid  整条评论的id
     * @return
     */
    @RequestMapping("/deltalks")
    public String deTalk(Integer tid){

        return talkcentreService.delTalk(tid);
    }



}
