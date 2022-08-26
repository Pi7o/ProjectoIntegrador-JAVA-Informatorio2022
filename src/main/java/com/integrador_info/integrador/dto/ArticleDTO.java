package com.integrador_info.integrador.dto;

import java.time.LocalDate;

public class ArticleDTO {

    private Long id;
    
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private LocalDate publishedAt;
    private String content;
    private AutorDTO author;
    private FuenteDTO source;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return this.urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public LocalDate getPublishedAt() {
        return this.publishedAt;
    }

    public void setPublishedAt(LocalDate publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AutorDTO getAuthor() {
        return this.author;
    }

    public void setAuthor(AutorDTO author) {
        this.author = author;
    }

    public FuenteDTO getSource() {
        return this.source;
    }

    public void setSource(FuenteDTO source) {
        this.source = source;
    }

    
}
