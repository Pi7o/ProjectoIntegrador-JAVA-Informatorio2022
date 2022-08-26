package com.integrador_info.integrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integrador_info.integrador.domain.Fuente;

@Repository
public interface FuenteRepository extends JpaRepository<Fuente, Long> {
    
}
