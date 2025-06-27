package com.AgriPecu.AgriPecu.controller;

import com.AgriPecu.AgriPecu.model.ConsultaClima;
import com.AgriPecu.AgriPecu.service.ConsultaClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaClimaController {

    @Autowired
    private ConsultaClimaService consultaClimaService;

    @GetMapping
    public List<ConsultaClima> getAllConsultas() {
        return consultaClimaService.getAllConsultas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaClima> getConsultaById(@PathVariable Long id) {
        return consultaClimaService.getConsultaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ConsultaClima> createConsulta(@RequestBody ConsultaClima consulta) {
        ConsultaClima createdConsulta = consultaClimaService.createConsulta(consulta);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdConsulta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaClima> updateConsulta(@PathVariable Long id, @RequestBody ConsultaClima consultaDetails) {
        try {
            ConsultaClima updatedConsulta = consultaClimaService.updateConsulta(id, consultaDetails);
            return ResponseEntity.ok(updatedConsulta);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable Long id) {
        consultaClimaService.deleteConsulta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cidade/{cidade}")
    public List<ConsultaClima> getConsultasByCidade(@PathVariable String cidade) {
        return consultaClimaService.getConsultasByCidade(cidade);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<ConsultaClima> getConsultasByUsuario(@PathVariable Long usuarioId) {
        return consultaClimaService.getConsultasByUsuario(usuarioId);
    }
}