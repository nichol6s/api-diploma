package br.com.fiap.api_diploma.dto;

import br.com.fiap.api_diploma.model.Sexo;

import java.time.LocalDate;

public record DiplomaRequestDTO(
        Long diplomadoId,
        Long cursoId,
        LocalDate dataConclusao,
        Sexo sexoReitor,
        String nomeReitor
) {}
