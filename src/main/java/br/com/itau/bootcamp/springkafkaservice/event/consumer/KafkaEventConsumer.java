package br.com.itau.bootcamp.springkafkaservice.event.consumer;

import br.com.itau.bootcamp.springkafkaservice.MessageService;
import br.com.itau.bootcamp.springkafkaservice.event.model.MyKafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@KafkaListener(topics = "spring.topic", autoStartup = "true")
public class KafkaEventConsumer {

  private final MessageService messageService;

  public KafkaEventConsumer(MessageService messageService) {
    this.messageService = messageService;
  }

  @KafkaHandler
  void listener(MyKafkaMessage message) {
    log.info("Consuming message:: {}", message);
    messageService.saveMessage(message.getId(), message.getText(), message.getCreationTime());
  }

  @KafkaHandler(isDefault = true)
  void listener(Object message) {
    log.info("Discarding message (UNKNOWN format):: {}", message);
  }
}
