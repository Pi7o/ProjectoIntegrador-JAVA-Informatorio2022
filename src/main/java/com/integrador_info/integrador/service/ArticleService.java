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
import com.integrador_info.integrador.repository.AutorRepository;
import com.integrador_info.integrador.repository.FuenteRepository;

@Service
public class ArticleService implements IArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private FuenteRepository fuenteRepository;

    @Override
    public List<Article> getArticleList(String title, String description, LocalDate date, int page, int size,
            String sortDir, String sort) {
        Pageable pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
        Page<Article> articles;
        if(title != null || description !=null){
            articles = articleRepository.findByDescriptionContainingOrTitleContaining(title,description, pageReq);
        }else {
            if(date!=null){
                articles = articleRepository.findBypublishedAtIsAfter(date, pageReq);
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
    public Article createArticle(Article article) {
        article.setAuthor(autorRepository.getReferenceById(article.getAuthor().getId()));
        article.setSource(fuenteRepository.getReferenceById(article.getSource().getId()));
        return articleRepository.save(article);
    }

    @Override
    public Article getArticleById(Long id) {
        // TODO Auto-generated method stub
        return articleRepository.getReferenceById(id);
    }
    
}
