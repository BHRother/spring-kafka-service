package br.com.itau.bootcamp.springkafkaservice.event;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@EnableKafka
@Configuration
public class KafkaConfiguration {

  //If the topic already exists, this is ignored.
  @Bean
  NewTopic topic() {
    return TopicBuilder.name("spring.topic")
        .partitions(1)
        .replicas(1)
        .build();
  }
}
