package com.integrador_info.integrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integrador_info.integrador.domain.Article;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    // SELECT * FROM article WHERE descripcion LIKE "%descripcion%"
    List<Article> findByDescription(String description);
}
