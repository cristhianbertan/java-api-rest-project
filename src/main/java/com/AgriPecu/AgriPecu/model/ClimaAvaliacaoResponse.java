package com.AgriPecu.AgriPecu.model;

public class ClimaAvaliacaoResponse {

    private String cidade;
    private String clima;
    private String avaliacao;

    public ClimaAvaliacaoResponse(String cidade, String clima, String avaliacao) {
        this.cidade = cidade;
        this.clima = clima;
        this.avaliacao = avaliacao;
    }

    public String getCidade() {
        return cidade;
    }

    public String getClima() {
        return clima;
    }

    public String getAvaliacao() {
        return avaliacao;
    }
}
