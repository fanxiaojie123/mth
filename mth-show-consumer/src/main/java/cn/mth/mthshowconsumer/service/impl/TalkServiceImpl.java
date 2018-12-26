package cn.mth.mthshowconsumer.service.impl;

import cn.mth.mthshowconsumer.pojo.Talkcentre;
import cn.mth.mthshowconsumer.service.TalkService;
import org.springframework.stereotype.Component;

@Component
public class TalkServiceImpl implements TalkService {
    @Override
    public String getall() {

        return "服务器繁忙,请稍后再试";
    }

    @Override
    public String getAllTalk(Integer tshopid) {
        return "服务器繁忙,请稍后再试";
    }

    @Override
    public String addTalk(Talkcentre talkcentre) {
        return "服务器繁忙,请稍后再试";
    }

    @Override
    public String twoTalk(Talkcentre talkcentre) {
        return "服务器繁忙,请稍后再试";
    }

    @Override
    public String shopTalk(Talkcentre talkcentre) {
        return "服务器繁忙,请稍后再试";
    }
}
