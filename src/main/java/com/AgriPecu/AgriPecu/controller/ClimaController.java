package com.AgriPecu.AgriPecu.controller;

import com.AgriPecu.AgriPecu.model.ClimaAvaliacaoRequest;
import com.AgriPecu.AgriPecu.model.ClimaAvaliacaoResponse;
import com.AgriPecu.AgriPecu.model.ClimaRequest;
import com.AgriPecu.AgriPecu.model.ClimaResponse;
import com.AgriPecu.AgriPecu.service.ClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClimaController {

    @Autowired
    private ClimaService climaService;

    @PostMapping("/clima")
    public ClimaResponse obterRecomendacao(@RequestBody ClimaRequest request) {
        return climaService.gerarRecomendacao(request);
    }


    @PostMapping("/avaliar")
    public ClimaAvaliacaoResponse avaliarClima(@RequestBody ClimaAvaliacaoRequest request) {
        return climaService.avaliarClima(request.getCidade());
    }

}
