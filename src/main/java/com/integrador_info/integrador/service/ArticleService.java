package com.integrador_info.integrador.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.integrador_info.integrador.domain.Article;
import com.integrador_info.integrador.repository.ArticleRepository;

public class ArticleService implements IArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> getArticleList(String title, String description, LocalDate date, int page, int size,
            String sortDir, String sort) {
        Pageable pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
        Page<Article> articles;
        if(title != null || description !=null){
            articles = articleRepository.findByDescriptionContainingOrByTitleContaining(title,description, pageReq);
        }else {
            if(date!=null){
                articles = articleRepository.findByCreatedAtIsAfter(date, pageReq);
            }else{
                articles = articleRepository.findAll(pageReq);
            } 
        }
        return articles.getContent();
    }

    @Override
    public void updateArticle(Article autor) {
        articleRepository.save(autor);
    }

    @Override
    public Article createArticle(Article autor) {
        // TODO Auto-generated method stub
        return articleRepository.save(autor);
    }

    @Override
    public Article getArticleById(Long id) {
        // TODO Auto-generated method stub
        return articleRepository.getReferenceById(id);
    }
    
}
