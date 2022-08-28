package com.integrador_info.integrador.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.integrador_info.integrador.domain.Article;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, PagingAndSortingRepository<Article,Long> {
    // SELECT * FROM article WHERE descripcion LIKE "%descripcion%"
    Page<Article> findByDescriptionContainingOrTitleContaining(String description,String title, Pageable pageReq);

    Page<Article> findBypublishedAtIsAfter(LocalDate date, Pageable pageReq);
}
