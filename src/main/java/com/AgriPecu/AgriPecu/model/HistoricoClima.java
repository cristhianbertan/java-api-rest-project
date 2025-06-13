package com.AgriPecu.AgriPecu.model;

public class HistoricoClima {
    private String cidade;
    private String tipo;
    private String atividade;
    private String resposta;
    private String clima;
    private String avaliacao;

    public HistoricoClima(String cidade, String tipo, String atividade, String resposta) {
        this.cidade = cidade;
        this.tipo = tipo;
        this.atividade = atividade;
        this.resposta = resposta;
    }

    public HistoricoClima(String cidade, String clima, String avaliacao) {
        this.cidade = cidade;
        this.clima = clima;
        this.avaliacao = avaliacao;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
}
