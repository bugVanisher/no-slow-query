package com.noslowq.newsql.metaq;

import com.google.gson.Gson;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RocketMQMessageListener(topic = "${spring.rocketmq.topic}", consumerGroup = "${spring.rocketmq.consumer.group}")
public class SqlMsgConsumer implements RocketMQListener<SqlDto> {

    private static final Gson GSON = new Gson();

    @Autowired
    private ConsumerService consumerService;


    @Override
    public void onMessage(SqlDto sqlDto) {
        if (null != sqlDto) {
            System.out.printf("get msg:%s\n", GSON.toJson(sqlDto));
            try {
                consumerService.handle(sqlDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
