package mqdelay.send.rocket;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.stereotype.Component;

//消息生产者类
@Component
public class Producer {
    private String producerGroup = "order_producer";
    private DefaultMQProducer producer;
    //构造
    public Producer(){
        //创建生产者
        producer = new DefaultMQProducer(producerGroup);
        //不开启vip通道
        producer.setVipChannelEnabled(false);
        //设定 name server
        producer.setNamesrvAddr(RocketConstants.NAME_SERVER);
        //producer.m
        start();
    }

    //使producer启动
    public void start(){
        try {
            this.producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
    //返回producer
    public DefaultMQProducer getProducer(){
        return this.producer;
    }

    //进行关闭的方法
    public void shutdown(){
        this.producer.shutdown();
    }
}