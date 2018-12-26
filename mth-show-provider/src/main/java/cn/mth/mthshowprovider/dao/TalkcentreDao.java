package cn.mth.mthshowprovider.dao;

import cn.mth.mthshowprovider.pojo.Talkcentre;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TalkcentreDao {
//查看所有
    @Results({
            @Result(property = "tid", column = "tid"),
            @Result(property = "tshopid", column = "tshopid"),
            @Result(property = "trderid", column = "tid"),
            @Result(property = "talktext", column = "tid"),
            @Result(property = "tpicture", column = "tid"),
            @Result(property = "firsttalk", column = "tid"),
            @Result(property = "addtalk", column = "tid"),
            @Result(property = "addtime", column = "tid"),
            @Result(property = "replyid", column = "tid"),
            @Result(property = "orderid", column = "tid"),
            @Result(property = "replytext", column = "tid")

    })
    //测试
    @Select("select tid,tshopid,trderid,talktext,tpicture,firsttalk,addtalk,addtime,replyid,orderid,replytext FROM talkcentre")
    List<Talkcentre>getall();
@Select("select * from talkcentre ")
List<Talkcentre>get();

    /**
     * 商家,用户 查看此商品下的各种评论
     * @param tshopid
     * @return
     */
    @Select("SELECT Talktext,tpicture,firsttalk,addtalk,addtime,replytext FROM talkcentre WHERE tshopid = #{tshopid}")
    List<Talkcentre> getAllTalk(Integer tshopid);


    /**
     * 商家删除此条评论所有信息
     * @param tid 本评论id
     * @return
     */
    // *****后期调用商家的一个状态******
    @Delete("delete from talkcentre where tid = #{tid}")
    int delTalk(Integer tid);
    /**
     *收货评论的添加
     * @param talkcentre
     * @return
     */
    @Insert("insert into  talkcentre (tid,talktext,tpicture,firsttalk) values (#{tid},#{talktext}, #{tpicture}, #{firsttalk} )")
    int addTalk(Talkcentre talkcentre);


    @Update("update talkcentre set addtalk = #{addtalk}, addtime = #{addtime} where (tid = #{tid})")
    int twoTalk(Talkcentre talkcentre);

    /**
     * 商家回复
     * @param talkcentre
     * @return
     */
    @Update("update talkcentre  set replytext = #{replytext} where (tid = #{tid})")
    int shopTalk(Talkcentre talkcentre);





}
