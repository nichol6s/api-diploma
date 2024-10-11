package br.com.fiap.api_diploma.exception.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ValidationErrorDTO(
        LocalDateTime timestamp,
        Integer status,
        String error,
        List<String> messages,
        String path
) {}
