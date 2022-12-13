package br.com.itau.bootcamp.springkafkaservice.controller.dto;

import java.time.LocalDateTime;

public record MessageDTO(String id, String message,
                         LocalDateTime createdAt) {
}
