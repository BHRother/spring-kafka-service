package br.com.itau.bootcamp.springkafkaservice.controller;

import br.com.itau.bootcamp.springkafkaservice.MessageService;
import br.com.itau.bootcamp.springkafkaservice.controller.dto.MessageDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/messages")
public class MessageController {

  private final MessageService service;

  public MessageController(MessageService service) {
    this.service = service;
  }

  @PostMapping
  void postMessage(@RequestBody String message) {
    log.info("Received {}", message);

    this.service.sendMessage(message);
  }

  @GetMapping
  List<MessageDTO> retrieveMessages() {
    return this.service.getMessages().stream()
        .map(message ->
            new MessageDTO(message.getUuid(), message.getMessage(), message.getCreatedAt()))
        .collect(Collectors.toList());
  }
}
