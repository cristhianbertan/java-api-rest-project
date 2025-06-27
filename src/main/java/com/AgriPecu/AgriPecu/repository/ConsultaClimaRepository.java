package com.AgriPecu.AgriPecu.repository;

import com.AgriPecu.AgriPecu.model.ConsultaClima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConsultaClimaRepository extends JpaRepository<ConsultaClima, Long> {
    List<ConsultaClima> findByCidadeIgnoreCase(String cidade);
    List<ConsultaClima> findByUsuarioId(Long usuarioId);
}
