package cn.mth.mthevaluateprovider.service;

import cn.mth.mthevaluateprovider.pojo.Talks;


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
    String addTalk(Talks talkcentre);

 /**
  * 追加评论
  * @param talks
  * @return
  */
 String twoTalk(Talks talks);

 /**
  * 商家回复
  * @param
  * @return
  */
 String shopTalk(Talks talks);



 /**
  * 分布
  * @param
  * @param
  * @return
  */

 //Pageinfo<Talks> findall(int page, int pageSize);

 String get();

}
