package cn.mth.mthevaluateprovider.dao;


import cn.mth.mthevaluateprovider.pojo.Talks;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TalkcentreDao {
//查看所有replytime
    @Results({
            @Result(property = "tid", column = "tid"),
            @Result(property = "tshopid", column = "tshopid"),
            @Result(property = "trderid", column = "trderid"),
            @Result(property = "talktext", column = "talktext"),
            @Result(property = "tpicture", column = "tpicture"),
            @Result(property = "firsttalk", column = "firsttalk"),
            @Result(property = "addtalk", column = "addtalk"),
            @Result(property = "addtime", column = "addtime"),
            @Result(property = "replyid", column = "replyid"),
            @Result(property = "orderid", column = "orderid"),
            @Result(property = "replytext", column = "replytext"),
            @Result(property = "replytime", column = "replytime")

    })
@Select("select * from talks")
 List<Talks>getall();

    /**
     * 根据商品id查看商品所有评论信息
     * @param tshopid
     * @return
     */

@Select("select trderid,talktext,tpicture,firsttalk,addtalk,addtime,replyid,orderid,replytext,replytime FROM talks where tshopid=#{tshopid}")
List<Talks>getAllTalk(Integer tshopid);

    /**
     * 商家删除此条评论所有信息
     * @param tid 本评论id
     * @return
     */
    // *****后期调用商家的一个状态******
    @Delete("delete from talks where tid = #{tid}")
    int delTalk(Integer tid);

    /**
     * 收获评价  第一次
     * @param talkcentre
     * @return
     */
    @Insert("insert into  talks (tid,trderid,tshopid,talktext,tpicture,firsttalk) values (#{tid},#{trderid}, #{tshopid}, #{talktext}, #{tpicture}, #{firsttalk} )")
    int addTalk(Talks talkcentre);
    /**
     * 追加评价  第二次
     * @param talks 根据订单id进去追加评价
     * @return
     */
    @Update("update talks set addtalk = #{addtalk}, addtime = #{addtime} where (tshopid = #{tshopid})")
    int twoTalk(Talks talks);

    /**
     *商家回复用户
     * @param talks
     * @return
     */
    @Update("update talks  set replytext = #{replytext}, replytime = #{replytime}  where (tid = #{tid})")
    int shopTalk(Talks talks);


}
