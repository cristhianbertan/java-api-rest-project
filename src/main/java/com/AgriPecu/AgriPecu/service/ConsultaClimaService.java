package com.AgriPecu.AgriPecu.service;

import com.AgriPecu.AgriPecu.model.ConsultaClima;
import com.AgriPecu.AgriPecu.repository.ConsultaClimaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "consultasClima")
public class ConsultaClimaService {

    @Autowired
    private ConsultaClimaRepository consultaClimaRepository;

    @Cacheable(key = "'allConsultas'")
    public List<ConsultaClima> getAllConsultas() {
        return consultaClimaRepository.findAll();
    }

    @Cacheable(key = "#id")
    public Optional<ConsultaClima> getConsultaById(Long id) {
        return consultaClimaRepository.findById(id);
    }

    @CacheEvict(allEntries = true)
    public ConsultaClima createConsulta(ConsultaClima consulta) {
        return consultaClimaRepository.save(consulta);
    }

    @CacheEvict(key = "#id")
    public ConsultaClima updateConsulta(Long id, ConsultaClima consultaDetails) {
        Optional<ConsultaClima> optionalConsulta = consultaClimaRepository.findById(id);
        if (optionalConsulta.isPresent()) {
            ConsultaClima consulta = optionalConsulta.get();
            consulta.setCidade(consultaDetails.getCidade());
            consulta.setTipo(consultaDetails.getTipo());
            consulta.setAtividade(consultaDetails.getAtividade());
            consulta.setRecomendacao(consultaDetails.getRecomendacao());
            consulta.setCondicoesAtuais(consultaDetails.getCondicoesAtuais());
            consulta.setAvaliacao(consultaDetails.getAvaliacao());
            consulta.setUsuario(consultaDetails.getUsuario());
            return consultaClimaRepository.save(consulta);
        } else {
            throw new RuntimeException("Consulta de Clima não encontrada com o ID: " + id);
        }
    }

    @CacheEvict(key = "#id")
    public void deleteConsulta(Long id) {
        consultaClimaRepository.deleteById(id);
    }

    @Cacheable(key = "'byCidade-' + #cidade.toLowerCase()")
    public List<ConsultaClima> getConsultasByCidade(String cidade) {
        return consultaClimaRepository.findByCidadeIgnoreCase(cidade);
    }

    @Cacheable(key = "'byUsuario-' + #usuarioId")
    public List<ConsultaClima> getConsultasByUsuario(Long usuarioId) {
        return consultaClimaRepository.findByUsuarioId(usuarioId);
    }
}
