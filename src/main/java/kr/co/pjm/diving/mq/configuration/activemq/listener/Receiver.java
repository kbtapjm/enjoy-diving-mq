package kr.co.pjm.diving.mq.configuration.activemq.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import kr.co.pjm.diving.mq.model.Email;

@Component
public class Receiver {

  @JmsListener(destination = "mailbox", containerFactory = "myFactory")
  public void receiveMessage(Email email) {
    System.out.println("Received <" + email + ">");
  }
}
