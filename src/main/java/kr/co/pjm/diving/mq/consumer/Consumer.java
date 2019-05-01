package kr.co.pjm.diving.mq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import kr.co.pjm.diving.mq.configuration.rabitmq.RabbitMQConfiguration;
import kr.co.pjm.diving.mq.model.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Consumer {

  @RabbitListener(containerFactory = "SampleContainerFactory", queues = RabbitMQConfiguration.QUEUE_NAME)
  public void onMessage(Message message) {
    log.info("Received < " + message.toString() + " >");
  }

}
