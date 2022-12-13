package br.com.itau.bootcamp.springkafkaservice.data.repository;

import br.com.itau.bootcamp.springkafkaservice.data.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, String> {

}
