package cn.mth.mthuserprovider.config;


import cn.mth.mthuserprovider.util.CommonInfo;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {

    public static final String ORDER_DELY_QUEUE = "order-dely-queue";
    public static final String ORDER_EXCHANGE = "order-exchange";
    public static final String ORDER_QUEUE = "order-queue";
    public final static String DEAD_KEY="order";



    @Bean
    public Queue orderDelyQueue(){

        Map<String,Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange",ORDER_EXCHANGE);
        map.put("x-dead-letter-routing-key",DEAD_KEY);
        map.put("x-message-ttl",CommonInfo.ORDER_REMAIN_SECOND * 1000);

        return new Queue(ORDER_DELY_QUEUE,true,false,false,map);
    }

    @Bean
    public Queue orderQueue(){
        return new Queue(ORDER_QUEUE,true,false,false);
    }


    @Bean
    public DirectExchange orderExchange(){
        return new DirectExchange(ORDER_EXCHANGE,true,false);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with(DEAD_KEY);
    }


}
