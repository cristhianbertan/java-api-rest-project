package com.AgriPecu.AgriPecu.service;

import com.AgriPecu.AgriPecu.model.ClimaAvaliacaoResponse;
import com.AgriPecu.AgriPecu.model.ClimaRequest;
import com.AgriPecu.AgriPecu.model.ClimaResponse;
import com.AgriPecu.AgriPecu.model.HistoricoClima;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClimaService {

    @Value("${openweather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();
    private final List<HistoricoClima> historico = new ArrayList<>();


    public ClimaResponse gerarRecomendacao(ClimaRequest request) {
        String cidade = request.getCidade();
        String tipo = request.getTipo();
        String atividade = request.getAtividade();

        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric&lang=pt_br",cidade, apiKey);

        try {
            String json = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(json);

            double temp = root.path("main").path("temp").asDouble();
            String condicao = root.path("weather").get(0).path("description").asText();

            String condicoesAtuais = condicao + ", " + temp + "°C";

            String recomendacao = gerarTextoRecomendacao(temp, condicao, tipo, atividade);

            this.historico.add(new HistoricoClima(cidade,tipo,atividade,recomendacao));
            return new ClimaResponse(cidade, condicoesAtuais, recomendacao);

        } catch (Exception e) {

            return new ClimaResponse(cidade, "Indisponível", "Erro ao consultar dados climáticos: " + e.getMessage());
        }
    }

    public List<HistoricoClima> getHistorico() {
        return this.historico;
    }
    private String gerarTextoRecomendacao(double temp, String condicao, String tipo, String atividade) {
        switch (tipo.toLowerCase()) {
            case "agricultura":
                return gerarRecomendacaoAgricultura(temp, condicao, atividade);

            case "pecuaria":
                return gerarRecomendacaoPecuaria(temp, atividade);
            default:
                return "Sem recomendação específica para essa atividade.";
        }
    }

    private String gerarRecomendacaoAgricultura(double temp, String condicao, String atividade) {
        switch (atividade.toLowerCase()) {
            case "colheita":
                if (condicao.toLowerCase().contains("chuva")) {
                    return "Adiar colheita. Previsão de chuva pode prejudicar a qualidade.";
                } else if (temp >= 20 && temp <= 30) {
                    return "Clima ideal para colheita. Aproveite o tempo seco.";
                } else {
                    return "Temperatura fora do ideal para colheita. Avaliar riscos.";
                }

            case "plantio":
                if (temp < 10) {
                    return "Muito frio para plantio. Risco alto de prejudicar germinação.";
                } else if (temp >= 18 && temp <= 30) {
                    return "Condições ideais para plantio. Culturas recomendadas: Milho, Feijão, Soja.";
                } else if (temp >= 15 && temp < 18) {
                    return "Temperatura aceitável para plantio. Recomendado: Trigo.";
                } else if (temp > 35) {
                    return "Temperatura muito alta. Risco de queimar mudas.";
                } else {
                    return "Temperatura fora do ideal. Avaliar cultura específica.";
                }

            case "aragem":
            case "preparo do solo":
                if (temp >= 20 && temp <= 30) {
                    return "Temperatura adequada para preparo do solo. Verifique umidade do solo.";
                } else {
                    return "Temperatura fora do ideal para aragem. Aguardar melhores condições.";
                }

            case "pulverizacao":
                if (temp < 30) {
                    return "Boa temperatura para pulverização. Evite dias com vento forte.";
                } else {
                    return "Evitar pulverização. Temperatura alta pode causar evaporação e deriva.";
                }

            default:
                return "Atividade agrícola não reconhecida.";
        }
    }

    private String gerarRecomendacaoPecuaria(double temp, String atividade) {
        switch (atividade.toLowerCase()) {
            case "pasto":
            case "pastoreio":
                if (temp >= 18 && temp <= 28) {
                    return "Temperatura ideal para pastoreio.";
                } else if (temp > 32) {
                    return "Temperatura alta. Estresse térmico possível. Reforçar hidratação.";
                } else {
                    return "Temperatura fora do ideal para pastoreio.";
                }
            case "confinamento":
            case "manejo":
                if (temp <= 28) {
                    return "Temperatura adequada para manejo de animais.";
                } else {
                    return "Alta temperatura. Prover sombra e água para os animais.";
                }

            case "reproducao":
            case "parto":
                if (temp >= 20 && temp <= 28) {
                    return "Temperatura ideal para reprodução e parto.";
                } else {
                    return "Temperatura inadequada para reprodução. Pode afetar fertilidade.";
                }

            case "leite":
            case "producao de leite":
                if (temp >= 18 && temp <= 25) {
                    return "Condições ideais para produção de leite.";
                } else if (temp > 30) {
                    return "Temperatura alta. Redução na produção de leite esperada.";
                } else {
                    return "Temperatura fora do ideal para alta produtividade leiteira.";
                }

            default:
                return "Atividade pecuária não reconhecida.";
        }
    }

    public ClimaAvaliacaoResponse avaliarClima(String cidade) {
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric&lang=pt_br", cidade, apiKey);
        try {
            String json = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(json);

            double temp = root.path("main").path("temp").asDouble();
            String condicao = root.path("weather").get(0).path("description").asText();
            String clima = condicao + ", " + temp + "°C";

            String avaliacao;

            if (condicao.contains("chuva") || condicao.contains("tempestade") || temp > 35 || temp < 10) {
                avaliacao = "Clima desfavorável. Risco de interrupções no campo.";
            } else {
                avaliacao = "Clima favorável para atividades no campo.";
            }
            this.historico.add(new HistoricoClima(cidade,clima,avaliacao));
            return new ClimaAvaliacaoResponse(cidade, clima, avaliacao);
        } catch (Exception e) {
            this.historico.add(new HistoricoClima(cidade,"Indisponível","Erro ao consultar clima: " + e.getMessage()));
            return new ClimaAvaliacaoResponse(cidade, "Indisponível", "Erro ao consultar clima: " + e.getMessage());
        }
    }


}
