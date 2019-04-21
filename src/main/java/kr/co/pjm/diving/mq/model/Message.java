package kr.co.pjm.diving.mq.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {
  private static final long serialVersionUID = 7422724864563311601L;
  
  private int val;
  private String msg;
}
