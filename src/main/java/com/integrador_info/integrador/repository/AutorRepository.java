package com.integrador_info.integrador.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.integrador_info.integrador.domain.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>, PagingAndSortingRepository<Autor,Long> {

    Page<Autor> findByFirstname(String firstname, Pageable pageReq);

    Page<Autor> findByCreatedAtIsAfter(LocalDate date, Pageable pageReq);
}
