package mqdelay.send.controller;

import com.alibaba.fastjson.JSON;
import mqdelay.send.pojo.OrderMsg;
import mqdelay.send.rocket.Producer;
import mqdelay.send.rocket.RocketConstants;
import mqdelay.send.util.TimeUtil;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private Producer producer;

    //初始化并发送消息
    @RequestMapping("/send")
    public String send() throws Exception {

        int userId = 1;

        //得到订单编号:
        DateTimeFormatter df_year = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        LocalDateTime date = LocalDateTime.now();
        String datestr = date.format(df_year);

        //消息,指定用户id和订单编号
        OrderMsg msg = new OrderMsg();
        msg.setUserId(userId);
        msg.setOrderSn(userId+"_"+datestr);

        String msgJson = JSON.toJSONString(msg);
        //生成一个信息，标签在这里手动指定
        Message message = new Message(RocketConstants.TOPIC, "carttag", msgJson.getBytes());
        //delaytime的值:
        //messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
        message.setDelayTimeLevel(5);
        //发送信息
        SendResult sendResult = producer.getProducer().send(message);
        System.out.println("时间:"+ TimeUtil.getTimeNow()+";生产者发送一条信息，内容:{"+msgJson+"},结果:{"+sendResult+"}");

        return "success";
    }
}
