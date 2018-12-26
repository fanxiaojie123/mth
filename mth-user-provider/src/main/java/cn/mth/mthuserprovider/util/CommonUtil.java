package cn.mth.mthuserprovider.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.UUID;

@Slf4j
public class CommonUtil {

    /**
     * 生成唯一订单号
     * @param machineId
     * @return
     */
    public static String getOrderIdByUUId(String machineId) {

        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        String orderId=machineId + String.format("%015d", hashCodeV);
        //log.info("生成唯一订单ID：" + orderId);
        return orderId;
    }

    //返回true 表示 都为空
    //判断该对象是否: 返回ture表示所有属性为null  返回false表示不是所有属性都是null
    public static boolean isAllFieldNull(Object obj) throws Exception{
      Class stuCla = (Class) obj.getClass();// 得到类对象
        Field[] fs = stuCla.getDeclaredFields();//得到属性集合
        boolean flag = true;
        for (Field f : fs) {//遍历属性
            f.setAccessible(true); // 设置属性是可以访问的(私有的也可以)
            Object val = f.get(obj);// 得到此属性的值
            if(val!=null) {//只要有1个属性不为空,那么就不是所有的属性值都为空
                flag = false;
                break;
            }
        }
        return flag;
    }

    //返回true  表示 都不为空
    //判断该对象是否: 返回ture表示所有属性都不为null  返回false表示所有不是属性都是null
    public static boolean isAllFieldNotNull(Object obj) throws Exception{
        Class stuCla = (Class) obj.getClass();// 得到类对象
        Field[] fs = stuCla.getDeclaredFields();//得到属性集合
        boolean flag = true;
        for (Field f : fs) {//遍历属性
            f.setAccessible(true); // 设置属性是可以访问的(私有的也可以)
            Object val = f.get(obj);// 得到此属性的值
            if(val==null) {//只要有1个属性不为空,那么就不是所有的属性值都为空
                flag = false;
                break;
            }
        }
        return flag;
    }


    public static void main(String[] args) {
        getOrderIdByUUId("1");
    }
}
