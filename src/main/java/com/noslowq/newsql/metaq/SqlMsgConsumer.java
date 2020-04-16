package com.noslowq.newsql.metaq;

import com.google.gson.Gson;
import com.noslowq.newsql.utils.Logger;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
//@RocketMQMessageListener(topic = "${spring.rocketmq.topic}", consumerGroup = "${spring.rocketmq.consumer.group}")
public class SqlMsgConsumer implements RocketMQListener<SqlDto> {

    private static final Logger logger = Logger.getLogger(SqlMsgConsumer.class);

    private static final Gson GSON = new Gson();

    @Autowired
    private ConsumerService consumerService;


    @Override
    public void onMessage(SqlDto sqlDto) {
        if (null != sqlDto) {
            logger.debug("get msg:{}", GSON.toJson(sqlDto));
            try {
                consumerService.handle(sqlDto);
            } catch (Exception e) {
                logger.error(e, "consume error.");
            }
        }
    }

}
