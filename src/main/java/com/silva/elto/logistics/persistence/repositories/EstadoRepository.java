package com.silva.elto.logistics.persistence.repositories;

import com.silva.elto.logistics.persistence.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    Optional<Estado> findBySigla(String sigla);
}
