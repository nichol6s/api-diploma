package br.com.fiap.api_diploma.exception.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message,
        String path
) {}
