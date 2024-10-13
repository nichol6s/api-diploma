package br.com.fiap.api_diploma.dto;

import br.com.fiap.api_diploma.model.Diploma;
import br.com.fiap.api_diploma.model.Sexo;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DiplomaTextDTO {
    private String tituloReitor;
    private String cargoReitor;
    private String nomeDiplomado;
    private String nacionalidade;
    private String naturalidade;
    private String numeroRg;
    private String tipoCurso;
    private String nomeCurso;
    private String dataConclusao;

    public DiplomaTextDTO(Diploma diploma) {
        this.tituloReitor = getTituloReitor(diploma);
        this.cargoReitor = getCargoReitor(diploma);
        this.nomeDiplomado = diploma.getDiplomado().getNome();
        this.nacionalidade = diploma.getDiplomado().getNacionalidade();
        this.naturalidade = diploma.getDiplomado().getNaturalidade();
        this.numeroRg = diploma.getDiplomado().getRg();
        this.tipoCurso = diploma.getCurso().getTipo().getDescricao();
        this.nomeCurso = diploma.getCurso().getNome();
        this.dataConclusao = diploma.getDataConclusao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private String getTituloReitor(Diploma diploma) {
        return switch (diploma.getSexoReitor()) {
            case M -> "Prof. Dr. " + diploma.getNomeReitor();
            case F -> "Profa. Dra. " + diploma.getNomeReitor();
        };
    }

    private String getCargoReitor(Diploma diploma) {
        return diploma.getSexoReitor() == Sexo.M ? "reitor" : "reitora";
    }

    public String gerarTexto() {
        return String.format("""
            O %s, %s da Universidade Fake, no uso de suas atribuições, confere a %s, \
            de nacionalidade %s, natural de %s, portador do rg %s, o presente diploma \
            de %s, do curso de %s, por ter concluído seus estudos nesta instituição \
            de ensino no dia %s.""",
                tituloReitor, cargoReitor, nomeDiplomado, nacionalidade, naturalidade,
                numeroRg, tipoCurso, nomeCurso, dataConclusao);
    }
}