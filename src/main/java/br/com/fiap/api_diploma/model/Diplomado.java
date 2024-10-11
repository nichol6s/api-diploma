package br.com.fiap.api_diploma.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Diplomado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Nacionalidade é obrigatória")
    private String nacionalidade;

    @NotBlank(message = "Naturalidade é obrigatória")
    private String naturalidade;

    @NotBlank(message = "RG é obrigatório")
    private String rg;
}
