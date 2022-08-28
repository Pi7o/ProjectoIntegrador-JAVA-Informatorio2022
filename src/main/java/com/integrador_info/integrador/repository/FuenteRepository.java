package com.integrador_info.integrador.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.integrador_info.integrador.domain.Fuente;

@Repository
public interface FuenteRepository extends JpaRepository<Fuente, Long>, PagingAndSortingRepository<Fuente,Long> {
    Page<Fuente> findByName(String name, Pageable pageReq);

    Page<Fuente> findByCreatedAtIsAfter(LocalDate date, Pageable pageReq);
}
