package cn.mth.mthshowprovider.service.Impl;

import cn.mth.mthshowprovider.controller.ssss;
import cn.mth.mthshowprovider.dao.TalkcentreDao;
import cn.mth.mthshowprovider.pojo.Talkcentre;
import cn.mth.mthshowprovider.service.TalkcentreService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalkcentreImpl implements TalkcentreService {
    @Autowired
    private TalkcentreDao talkcentreDao;
   String time= new ssss().showtime();
  //  ssss.showtime();

    @Override
    public String getall() {
       List<Talkcentre>get = talkcentreDao.getall();
        return JSON.toJSONString(get);
    }

    @Override
    public String getAllTalk(Integer tshopid) {
        System.out.println(talkcentreDao.getAllTalk(tshopid));
        return JSON.toJSONString( talkcentreDao.getAllTalk(tshopid));
    }

    @Override
    public String delTalk(Integer tid) {
        return JSON.toJSONString(talkcentreDao.delTalk(tid));
    }

    @Override
    public String addTalk(Talkcentre talkcentre) {
        String text=talkcentre.getTalktext();
        System.out.println("评论"+text);
        if(text.length()>10){
            return "评论内容太多";
        }
        if(addMe (talkcentre.getTalktext())< 1){
            return "输入内容有敏感字符";
        }
        talkcentre.setTid(0);
        talkcentre.setFirsttalk(time); //评论的时间

                JSON.toJSONString(talkcentreDao.addTalk(talkcentre));
        return "评论成功";
    }

    @Override
    public String twoTalk(Talkcentre talkcentre) {
        if (talkcentre.getAddtalk().length() > 10){

            return "评论超出长度,请控制字数";

            }
        if(addMe (talkcentre.getAddtalk())< 1){
            return "输入内容有敏感字符";

        }

        talkcentre.setAddtime(time);
        JSON.toJSONString(talkcentreDao.twoTalk(talkcentre));
        return "追加评论成功";
    }

    @Override
    public String shopTalk(Talkcentre talkcentre) {
        if (talkcentre.getReplytext().length() > 10){
            return "评论超出长度,请控制字数";
        }
        if(addMe (talkcentre.getReplytext())< 1){
            return "输入内容有敏感字符";
        }
        JSON.toJSONString(talkcentreDao.shopTalk(talkcentre));
        return "对此用户回复成功";
    }


    /**
     * 分页查询
     * @param page 当前页数
     * @param pageSize 每页个数
     * @return
     */
    public PageInfo<Talkcentre>findall(int page, int pageSize){
        PageHelper.startPage(page,pageSize);
        List<Talkcentre>all =talkcentreDao.get();
        PageInfo<Talkcentre>info =new PageInfo<>(all);
        return info;

    }

    @Override
    public String get() {
        List<Talkcentre>getff=talkcentreDao.get();
        return JSON.toJSONString(getff);
    }


    /**
     * 判断敏感字符
     * @param
     * @return
     */
    public static boolean isLetterDigitOrChinese (String realname){
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
        return realname.matches(regex);
    }
    public int addMe (String talk){

            //判断出了字母,汉字,特殊符号 就出异常
            boolean am = isLetterDigitOrChinese(talk);
            if (am) {
                return 1;
            } else {
                return 0;
            }


    }

}
