package cn.mth.mthcartprovider.pojo;

public class ShoppingCart {
    private Long shoPid;  //商品id
    private String  shopName; //商品名字
    private String  shopText; //商品标题
    private String  shopPrice;//商品单价
    private Integer  merchantId;//商品店家id
    private String  merchant;//商品店家名称
    private Integer  itemNum;   //商品数量
    private String  shopPhoto; //商品图片
    private String  shopSku;   //商品属性

    public Long getShoPid() {
        return shoPid;
    }

    public void setShoPid(Long shoPid) {
        this.shoPid = shoPid;
    }

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }


    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopText() {
        return shopText;
    }

    public void setShopText(String shopText) {
        this.shopText = shopText;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }



    public String getShopPhoto() {
        return shopPhoto;
    }

    public void setShopPhoto(String shopPhoto) {
        this.shopPhoto = shopPhoto;
    }

    public String getShopSku() {
        return shopSku;
    }

    public void setShopSku(String shopSku) {
        this.shopSku = shopSku;
    }
}
