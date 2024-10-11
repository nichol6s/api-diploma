package br.com.fiap.api_diploma.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO(
        @NotBlank String login,
        @NotBlank String senha
) {}