package br.com.itau.bootcamp.springkafkaservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.itau.bootcamp.springkafkaservice.controller.dto.MessageDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.test.utils.KafkaTestUtils;

class ApplicationTests extends AbstractIntegrationTest {

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  public void testPublishAndConsumerMessage_storeDB() throws InterruptedException {
    //when
    Thread.sleep(1000);

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

  public MessageDTOList waitForMessage(int attempts) throws InterruptedException {
    while (attempts-- > 0) {
      Thread.sleep(1000);//wait for the message to be consumed

      ResponseEntity<MessageDTOList> response = restTemplate.getForEntity("/messages",
          MessageDTOList.class);
      assertEquals(HttpStatus.OK, response.getStatusCode());

      if (response.getBody().size() > 0) {
        return response.getBody();
      }
    }
    return new MessageDTOList();
  }
}
