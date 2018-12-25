package cn.mth.mthcartprovider.pojo;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCarts {
    /**
     * 购物车列表
     */
    private Map<Long,ShoppingCart> shoppingCartMap;
    /**
     * 购物车总金额
     */
    private Double totalPrice;
    /**
     * 构造函数
     */
    public ShoppingCarts(){
        shoppingCartMap = new HashMap<Long,ShoppingCart>();

        totalPrice = 0.0;
    }

    public Map<Long, ShoppingCart> getShoppingCartMap() {
        return shoppingCartMap;
    }

    public void setShoppingCartMap(Map<Long, ShoppingCart> shoppingCartMap) {
        this.shoppingCartMap = shoppingCartMap;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 从购物车删除商品
     * @param itemId 购物车id
     * @return
     */
    public boolean removeItem(Long itemId){
        if (shoppingCartMap.containsKey(itemId)){
            shoppingCartMap.remove(itemId);
            getTotalPrice();//计算总价格
            return true;
        }else {
            return false;
        }
    }
    public boolean addItemInCard(Long shoPid, int num, ShoppingCart shoppingCart){
        //获取到这个商品
        ShoppingCart shoppingCartInCard = shoppingCartMap.get(shoPid);
        if (shoppingCartInCard != null){
            //购物车中存在这个商品
            //修改数量
            shoppingCartInCard.setItemNum(shoppingCartInCard.getItemNum() + num);
            //修改
            shoppingCartMap.put(shoPid, shoppingCartInCard);
        }else {
            if (shoppingCart != null){
                shoppingCartMap.put(shoPid,shoppingCart);
            }else {
                return false;
            }
        }
        getTotalPrice();//计算总金额
        return true;
    }

    public boolean reduceItem(Long shoPid,int num){
        if (shoppingCartMap.containsKey(shoPid)){
            //购物车有这个实体类
            //减少后 是否为负
            int numnow = shoppingCartMap.get(shoPid).getItemNum() - num;
            if(numnow < 0){
                //减为负数了
                return false;
            }else{
                //获得购物车实体类
                ShoppingCart cart = shoppingCartMap.get(shoPid);
                cart.setItemNum(numnow);
                shoppingCartMap.put(shoPid,cart);
                getTotalPrice();//计算总金额
                return true;
            }
        }else{
            //购物车没有这个实体类
            return false;
        }
    }
}
