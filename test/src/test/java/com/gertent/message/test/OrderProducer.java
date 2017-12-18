package com.gertent.message.test;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.util.List;

/**
 * @Description
 * @Author wyf
 * @Date 2017/12/18
 */
public class OrderProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("OrderProducer");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();

        String[] tags =new String[]{"createTag","payTag","sendTag"};
        //订单消息
        for (int orderId = 1 ; orderId <= 10 ; orderId++){
            //每种订单分为 创建订单/支付订单/发货订单
            for(int type = 0 ; type < 3 ; type++){
                Message msg = new Message("OrderTopic",tags[type % tags.length],"orderId:" + orderId + "----->type:" + type,("orderId:" + orderId + "----->type:" + type).getBytes());
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer)arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                },orderId);
                System.out.println(sendResult);
            }
        }
        producer.shutdown();

    }

    @Test
   public void test1(){
       String[] tags =new String[]{"createTag","payTag","sendTag"};
        for (int orderId = 1 ; orderId <= 10 ; orderId++){
            //每种订单分为 创建订单/支付订单/发货订单
            for(int type = 0 ; type <3 ; type++){
                System.out.println((type % tags.length)+":"+tags[type % tags.length]);

            }
        }
   }
}
