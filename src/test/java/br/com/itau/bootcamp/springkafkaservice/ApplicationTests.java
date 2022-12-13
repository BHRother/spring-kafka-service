package br.com.itau.bootcamp.springkafkaservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.itau.bootcamp.springkafkaservice.controller.dto.MessageDTO;
import br.com.itau.bootcamp.springkafkaservice.data.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ApplicationTests extends AbstractIntegrationTest {

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  public void testPublishAndConsumerMessage_storeDB() throws InterruptedException {
    //when
    ResponseEntity<String> createMessageResponse = restTemplate.postForEntity("/messages",
        "Hello there, it is Bruno here", String.class);
    //then
    assertEquals(HttpStatus.OK, createMessageResponse.getStatusCode());

    //when
    MessageDTO messageDTO = waitForMessage(10).get(0);
    assertNotNull(messageDTO.id());
    assertNotNull(messageDTO.createdAt());
    assertEquals("Hello there, it is Bruno here", messageDTO.message());
  }

  @Test
  public void testPublishAndConsumerMessage_storeDB2() throws InterruptedException {
    //when
    ResponseEntity<String> createMessageResponse = restTemplate.postForEntity("/messages",
        "Hello there, it is Bruno here 2", String.class);
    //then
    assertEquals(HttpStatus.OK, createMessageResponse.getStatusCode());

    //when
    MessageDTO messageDTO = waitForMessage(10).get(0);
    assertNotNull(messageDTO.id());
    assertNotNull(messageDTO.createdAt());
    assertEquals("Hello there, it is Bruno here 2", messageDTO.message());
  }

  public MessageDTOList waitForMessage(int attempts) throws InterruptedException {
    while (attempts-- > 0) {

      ResponseEntity<MessageDTOList> response = restTemplate.getForEntity("/messages",
          MessageDTOList.class);
      assertEquals(HttpStatus.OK, response.getStatusCode());

      if (response.getBody().size() > 0) {
        return response.getBody();
      }
      Thread.sleep(200);//wait for the message to be consumed
    }
    return new MessageDTOList();
  }
}
