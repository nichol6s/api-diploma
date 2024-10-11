package br.com.fiap.api_diploma.model;

public enum TipoCurso {
    GRADUACAO("Graduação"),
    POS_GRADUACAO("Pós-Graduação");

    private final String descricao;

    TipoCurso(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}