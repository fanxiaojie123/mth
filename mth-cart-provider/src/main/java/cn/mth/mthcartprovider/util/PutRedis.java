package cn.mth.mthcartprovider.util;



import cn.mth.mthcartprovider.pojo.ShoppingCart;
import cn.mth.mthcartprovider.pojo.ShoppingCarts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * redis中的集合（用户id，skuid，值）
 */
@Slf4j
@Component
public class PutRedis {

    @Autowired
    private RedisUtil redisUtil;



    /**
     * 向reids中插入商品（修改购物车商品数量）
     * @param shoppingCarts
     * @param userId
     */
    public  void insertBuyerCartToRedis(ShoppingCarts shoppingCarts, String userId) {
        //获取购物车中的数据
        Map<Long, ShoppingCart> shoppingCartMap = shoppingCarts.getShoppingCartMap();

        if (shoppingCartMap.size() > 0) {

            //根据用户id获取对象
            ShoppingCarts shoppingCartsRedis = (ShoppingCarts) redisUtil.get(userId);//reids中 shoppingcards 对象

            if (shoppingCartsRedis == null){

                System.out.println("redis中没有数据....新建购物车对象.....");

                shoppingCartsRedis =  new ShoppingCarts();
            }

            Map<Long, ShoppingCart> redisUser = shoppingCartsRedis.getShoppingCartMap();//reids中 shoppingcards 集合
            Set<Long> itemids = shoppingCartMap.keySet();//内存中的键的集合（商品id）
            for (Long itemid : itemids) {//内存中的

                //根据id获取reids中存在的商品信息
                ShoppingCart shoppingCartRedis = redisUser.get(itemid);
                //获取内存中的对象
                ShoppingCart shoppingCart = shoppingCartMap.get(itemid);
                if(shoppingCartRedis != null){
                    //在redis中存在这个商品

                    //修改数量(商品对象)
                    shoppingCartRedis.setItemNum(shoppingCart.getItemNum());
                    //修改购物车对象
                    redisUser.put(itemid,shoppingCartRedis);
                }else{
                    //redis中不存在这个对象

                    //将内存中的对象放入reids中
                    redisUser.put(itemid, shoppingCart);
                }
            }

            shoppingCartsRedis.setShoppingCartMap(redisUser);
            shoppingCartsRedis.getTotalPrice();
            redisUtil.set(userId, shoppingCartsRedis);
        }

    }

    /**
     * 根据商品id在reids中获得购物车列表
     * @param userId
     * @return
     */
    public  List<ShoppingCart> selectBuyerCartFromRedis(String userId){
        ShoppingCarts ShoppingCarts = (ShoppingCarts) redisUtil.get(userId);
        Map<Long, ShoppingCart> shoppingCartMap = ShoppingCarts.getShoppingCartMap();
        List<ShoppingCart> blist = new ArrayList<ShoppingCart>();
        Set<Long> keySet = shoppingCartMap.keySet();
        for (Long o: keySet) {
            ShoppingCart buyerItem = shoppingCartMap.get(o);
            blist.add(buyerItem);
        }
        return  blist;
    }

    /**
     * 根据商品id在reids中获得购物车Map
     * @param userId
     * @return
     */
    public   Map<Long, ShoppingCart> selectBuyerCartMap(String userId){
        ShoppingCarts shoppingCarts = (ShoppingCarts) redisUtil.get(userId);

        Map<Long, ShoppingCart> shoppingCartMap = shoppingCarts.getShoppingCartMap();
        return shoppingCartMap;
    }


    public   Map<String,Object> deleteBuyerCardMap(String userId,Long itemId){

        Map<String,Object> mapRe = new HashMap<>();
        ShoppingCarts shoppingCarts = (ShoppingCarts) redisUtil.get(userId);
        boolean b = shoppingCarts.removeItem(itemId);
        if (!b){
            mapRe.put("code","400");
            mapRe.put("info","删除购物车失败");
        }else{
            mapRe.put("code","200");
            mapRe.put("info","删除购物车成功");
        }

        Map<Long, ShoppingCart> shoppingCartMap = shoppingCarts.getShoppingCartMap();

        mapRe.put("result",shoppingCartMap);
        return mapRe;
    }

    public   boolean set(String string,Object object){

        return redisUtil.set(string,object);
    }


    public   ShoppingCarts get(String string){



        ShoppingCarts shoppingCarts = (ShoppingCarts) redisUtil.get(string);
        return shoppingCarts;
    }


}