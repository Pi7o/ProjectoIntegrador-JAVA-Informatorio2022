package com.integrador_info.integrador.service;

import java.time.LocalDate;
import java.util.List;

import com.integrador_info.integrador.domain.Article;

public interface IArticleService {
    List<Article> getArticleList(String title,String description,LocalDate iso,int page, int size, String sortDir, String sort);

    void updateArticle(Article autor);

    Article createArticle(Article autor);

    Article getArticleById(Long id);
}
