package cn.mth.mthevaluateprovider.pojo;


import java.util.Date;

public class Talks {

  private Integer tid;//评论id
  private Integer tshopid;//商品id
  private Integer trderid;//订单id
  private String talktext;//收货评价内容
  private String tpicture;//收获评价图片
  private String firsttalk;//第一次评价时间
  private String addtalk;//追加评论内容
  private String addtime;//追加评论时间
  private Integer replyid;//回复信息id
  private Integer orderid;//回复商家id
  private String replytext;//商家回复内容
  private String replytime; //回复时间

  public String getAddtime() {
    return addtime;
  }

  public void setAddtime(String addtime) {
    this.addtime = addtime;
  }

  public String getReplytime() {
    return replytime;
  }

  public void setReplytime(String replytime) {
    this.replytime = replytime;
  }

  public String getFirsttalk() {
    return firsttalk;
  }

  public void setFirsttalk(String firsttalk) {
    this.firsttalk = firsttalk;
  }



  public Integer getTid() {
    return tid;
  }

  public void setTid(Integer tid) {
    this.tid = tid;
  }

  public Integer getTshopid() {
    return tshopid;
  }

  public void setTshopid(Integer tshopid) {
    this.tshopid = tshopid;
  }

  public Integer getTrderid() {
    return trderid;
  }

  public void setTrderid(Integer trderid) {
    this.trderid = trderid;
  }

  public String getTalktext() {
    return talktext;
  }

  public void setTalktext(String talktext) {
    this.talktext = talktext;
  }

  public String getTpicture() {
    return tpicture;
  }

  public void setTpicture(String tpicture) {
    this.tpicture = tpicture;
  }



  public String getAddtalk() {
    return addtalk;
  }

  public void setAddtalk(String addtalk) {
    this.addtalk = addtalk;
  }



  public Integer getReplyid() {
    return replyid;
  }

  public void setReplyid(Integer replyid) {
    this.replyid = replyid;
  }

  public Integer getOrderid() {
    return orderid;
  }

  public void setOrderid(Integer orderid) {
    this.orderid = orderid;
  }

  public String getReplytext() {
    return replytext;
  }

  public void setReplytext(String replytext) {
    this.replytext = replytext;
  }




}
