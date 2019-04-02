package kr.co.pjm.diving.mq.producer;

import java.util.stream.IntStream;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import kr.co.pjm.diving.mq.configuration.rabitmq.RabbitMQConfiguration;
import kr.co.pjm.diving.mq.model.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Producer {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Scheduled(cron = "0/5 * * * * *")
  public void onSend() {
    log.info("Sending message... Start");

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    IntStream.range(1, 15000).parallel().forEach(val -> {
      rabbitTemplate.convertAndSend(RabbitMQConfiguration.QUEUE_NAME, new Message(val, "Hello, RabbitMQ! 1"));
    });
    stopWatch.stop();
    log.info(stopWatch.toString());
    log.info("Sending message... End");
  }

}
