package cn.mth.mthevaluateprovider.service.Impl;


import cn.mth.mthevaluateprovider.dao.TalkcentreDao;
import cn.mth.mthevaluateprovider.pojo.Talks;
import cn.mth.mthevaluateprovider.service.TalkcentreService;
import cn.mth.mthevaluateprovider.util.Fuck;
import cn.mth.mthevaluateprovider.util.Time;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;

@Service
public class TalkcentreImpl implements TalkcentreService {
    Fuck fuck = new Fuck();
    String time= new Time().showtime(); //获取时间
    @Autowired
    private TalkcentreDao talkcentreDao;


    @Override
    public String getall() {
        return JSON.toJSONString(talkcentreDao.getall());
    }

    @Override
    public String getAllTalk(Integer tshopid) {
        return JSON.toJSONString(talkcentreDao.getAllTalk(tshopid));
    }

    @Override
    public String delTalk(Integer tid) {

       int flag = talkcentreDao.delTalk(tid);
       if (flag < 1){
           return "删除失败,不存在此评论";
       }else {
           return "删除成功";
       }

    }

    @Override
    public String addTalk(Talks talks) {
        if (fuck.talkLength(talks.getTalktext())<1){
            return "评论内容太长,请您控制在50以内";
        }
       if (fuck.talkText(talks.getTalktext())<1){
           return "评论含有非法字符";
       }
        talks.setFirsttalk(time);
         talkcentreDao.addTalk(talks);

            System.out.println(time);
            return "评论成功,祝您收获愉快";

    }

    @Override
    public String twoTalk(Talks talks) {
        if (fuck.talkLength(talks.getAddtalk())<1){
            return "评论内容太长,请您控制在50以内";
        }
        if (fuck.talkText(talks.getAddtalk())<1){
            return "评论含有非法字符";
        }

        talks.setAddtime(time);
        talkcentreDao.twoTalk(talks);
        return "追加评论成功,祝您收获愉快";
    }

    @Override
    public String shopTalk(Talks talks) {
        if (fuck.talkLength(talks.getReplytext())<1){
            return "评论内容太长,请您控制在50以内";
        }
        if (fuck.talkText(talks.getReplytext())<1){
            return "评论含有非法字符";
        }
        talks.setReplytime(time);
        talkcentreDao.shopTalk(talks);
        return "回复用户成功,祝你财运东来";
    }

    @Override
    public String get() {
        return null;
    }
    //  ssss.showtime();


}
