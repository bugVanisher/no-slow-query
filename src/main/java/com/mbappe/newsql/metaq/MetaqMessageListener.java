package com.mbappe.newsql.metaq;

import com.google.gson.Gson;
import com.mbappe.newsql.utils.Logger;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.List;

@Service(value = "metaqMessageListener")
public class MetaqMessageListener implements MessageListenerConcurrently {

    private static final Logger logger = Logger.getLogger(MetaqMessageListener.class);

    private static final Gson GSON = new Gson();

    @Autowired
    private ConsumerService consumerService;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        final Charset UTF_8 = Charset.forName("UTF-8");
        long startTime = System.currentTimeMillis();
        for (MessageExt msg : msgs) {
            String receivedMsg = new String(msg.getBody(), UTF_8);
            SqlDto sqlDto = GSON.fromJson(receivedMsg, SqlDto.class);
            try {
                if (null != sqlDto) {
                    consumerService.handle(sqlDto);
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e, "listener handle error.");
            }
        }
        logger.info("Consumed {} messages...Cost {}ms", msgs.size(), System.currentTimeMillis() - startTime);
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
