package br.com.fiap.api_diploma.dto;

import br.com.fiap.cp5.model.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @NotBlank String login,
        @NotBlank String senha,
        @NotNull UserRole role
) {}
