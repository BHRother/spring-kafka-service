package br.com.itau.bootcamp.springkafkaservice.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Message {

  @Id
  @Column(name = "uuid", nullable = false)
  private String uuid;

  private String message;

  private LocalDateTime createdAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Message message = (Message) o;
    return getUuid().equals(message.getUuid());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUuid());
  }
}
