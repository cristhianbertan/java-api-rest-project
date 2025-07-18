package com.AgriPecu.AgriPecu.controller;

import com.AgriPecu.AgriPecu.model.ConsultaClima;
import com.AgriPecu.AgriPecu.service.ClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoricoController {

    @Autowired
    private ClimaService climaService;

    @GetMapping("/historico")
    public List<ConsultaClima> listarHistorico() {
        return climaService.getHistorico();
    }

}
