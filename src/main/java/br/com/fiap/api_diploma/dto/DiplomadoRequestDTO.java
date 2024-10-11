package br.com.fiap.api_diploma.dto;

public record DiplomadoRequestDTO(
        String nome,
        String nacionalidade,
        String naturalidade,
        String rg
) {}
