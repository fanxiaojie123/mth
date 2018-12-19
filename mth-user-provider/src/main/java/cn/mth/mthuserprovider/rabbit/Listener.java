package cn.mth.mthuserprovider.rabbit;


import cn.mth.mthuserprovider.config.RabbitConfig;
import cn.mth.mthuserprovider.redisutil.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class Listener {

    @Autowired
    private RedisUtil redisUtil;


    @RabbitListener(queues = RabbitConfig.ORDER_QUEUE)
    public void Listeners(String orderid) {
        if (orderid == null || orderid.equals("")) {
            log.info("订单id错误...");
        }
        System.out.println("order:" + orderid);
    }

}
