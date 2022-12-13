package br.com.itau.bootcamp.springkafkaservice.event.producer;

import br.com.itau.bootcamp.springkafkaservice.event.model.MyKafkaMessage;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaEventProducer {

  private final KafkaTemplate<String, MyKafkaMessage> kafkaTemplate;

  public KafkaEventProducer(
      KafkaTemplate<String, MyKafkaMessage> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(String uuid, String message) {
    log.info("Publishing:: {}:{}", uuid, message);

    MyKafkaMessage myKafkaMessage = MyKafkaMessage.builder()
        .id(uuid)
        .text(message)
        .creationTime(LocalDateTime.now())
        .build();

    this.kafkaTemplate.send("spring.topic", myKafkaMessage.getId(), myKafkaMessage);
    this.kafkaTemplate.flush();
  }
}
