package br.com.itau.bootcamp.springkafkaservice;

import br.com.itau.bootcamp.springkafkaservice.data.model.Message;
import br.com.itau.bootcamp.springkafkaservice.data.repository.MessageRepository;
import br.com.itau.bootcamp.springkafkaservice.event.model.MyKafkaMessage;
import br.com.itau.bootcamp.springkafkaservice.event.producer.KafkaEventProducer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  private final KafkaEventProducer producer;
  private final MessageRepository repository;

  public MessageService(
      KafkaEventProducer producer,
      MessageRepository repository) {
    this.producer = producer;
    this.repository = repository;
  }

  public void sendMessage(String message) {
    this.producer.sendMessage(UUID.randomUUID().toString(), message);
  }

  public void saveMessage(String id, String text, LocalDateTime creationTime) {
    this.repository.save(new Message(id, text, creationTime));
  }

  public List<Message> getMessages() {
    return this.repository.findAll();
  }
}
