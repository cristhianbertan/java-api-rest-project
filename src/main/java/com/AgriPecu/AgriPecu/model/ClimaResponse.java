package com.AgriPecu.AgriPecu.model;

public class ClimaResponse {

    private String cidade;
    private String condicoesAtuais;
    private String recomendacao;

    public ClimaResponse(String cidade, String condicoesAtuais, String recomendacao) {
        this.cidade = cidade;
        this.condicoesAtuais = condicoesAtuais;
        this.recomendacao = recomendacao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCondicoesAtuais() {
        return condicoesAtuais;
    }

    public void setCondicoesAtuais(String condicoesAtuais) {
        this.condicoesAtuais = condicoesAtuais;
    }

    public String getRecomendacao() {
        return recomendacao;
    }

    public void setRecomendacao(String recomendacao) {
        this.recomendacao = recomendacao;
    }

}
