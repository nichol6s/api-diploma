package br.com.fiap.api_diploma.dto;

import br.com.fiap.api_diploma.model.TipoCurso;

public record CursoRequestDTO(
        String nome,
        TipoCurso tipo
) {}
