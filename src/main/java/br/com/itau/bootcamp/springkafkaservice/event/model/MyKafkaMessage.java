package br.com.itau.bootcamp.springkafkaservice.event.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
public class MyKafkaMessage {
  private String id;
  private String text;
  private LocalDateTime creationTime;
}
