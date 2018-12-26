package cn.mth.mthshowprovider.service;

import cn.mth.mthshowprovider.pojo.Talkcentre;
import com.github.pagehelper.PageInfo;

public interface TalkcentreService {

   //test
    String getall();
    /**
     * 根据商品id查评论
     * @param tshopid 商品id
     * @return
     */
    String getAllTalk(Integer tshopid);

    /**
     * 删除评论只有商家能用
     * @param tid
     * @return
     */
    String delTalk(Integer tid);

    /**
     *收货评价的添加
     * @return
     */
    String addTalk(Talkcentre talkcentre);

 /**
  * 追加评论
  * @param talkcentre
  * @return
  */
 String twoTalk(Talkcentre talkcentre);

 /**
  * 商家回复
  * @param talkcentre
  * @return
  */
 String shopTalk(Talkcentre talkcentre);

 /**
  * 分布
  * @param page
  * @param pageSize
  * @return
  */

 PageInfo<Talkcentre> findall(int page, int pageSize);

 String get();

}
