package com.AgriPecu.AgriPecu.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultasclima")
public class ConsultaClima {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cidade;
    private String tipo;
    private String atividade;
    @Column(length = 1000)
    private String recomendacao;
    private String condicoesAtuais; // Adicionado para persistir a condição atual também
    private String avaliacao; // Para as avaliações de clima
    private LocalDateTime dataHoraConsulta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public ConsultaClima() {
        this.dataHoraConsulta = LocalDateTime.now();
    }

    public ConsultaClima(String cidade, String tipo, String atividade, String recomendacao, String condicoesAtuais) {
        this(); // Chama o construtor padrão para setar dataHoraConsulta
        this.cidade = cidade;
        this.tipo = tipo;
        this.atividade = atividade;
        this.recomendacao = recomendacao;
        this.condicoesAtuais = condicoesAtuais;
    }

    public ConsultaClima(String cidade, String condicoesAtuais, String avaliacao) {
        this(); // Chama o construtor padrão para setar dataHoraConsulta
        this.cidade = cidade;
        this.condicoesAtuais = condicoesAtuais;
        this.avaliacao = avaliacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRecomendacao() {
        return recomendacao;
    }

    public void setRecomendacao(String recomendacao) {
        this.recomendacao = recomendacao;
    }

    public String getCondicoesAtuais() {
        return condicoesAtuais;
    }

    public void setCondicoesAtuais(String condicoesAtuais) {
        this.condicoesAtuais = condicoesAtuais;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDateTime getDataHoraConsulta() {
        return dataHoraConsulta;
    }

    public void setDataHoraConsulta(LocalDateTime dataHoraConsulta) {
        this.dataHoraConsulta = dataHoraConsulta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
