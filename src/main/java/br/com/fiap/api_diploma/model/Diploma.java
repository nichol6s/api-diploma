package br.com.fiap.api_diploma.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diploma {
    @Id
    private UUID id;

    @ManyToOne
    @NotNull(message = "Diplomado é obrigatório")
    private Diplomado diplomado;

    @ManyToOne
    @NotNull(message = "Curso é obrigatório")
    private Curso curso;

    @NotNull(message = "Data de conclusão é obrigatória")
    private LocalDate dataConclusao;

    @NotNull(message = "Sexo do reitor é obrigatório")
    @Enumerated(EnumType.STRING)
    private Sexo sexoReitor;

    @NotBlank(message = "Nome do reitor é obrigatório")
    private String nomeReitor;

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }
}