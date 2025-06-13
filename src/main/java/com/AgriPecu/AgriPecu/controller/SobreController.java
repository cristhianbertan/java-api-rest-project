package com.AgriPecu.AgriPecu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SobreController {
    @GetMapping("/sobre")
    public String sobre() {
        return """
        {
          "integrantes": ["Maicou Hahn Fortuna","Cristhian Cardoso Bertan"],
          "nome_projeto": "AgriPecu"
        }
        """;
    }
}
