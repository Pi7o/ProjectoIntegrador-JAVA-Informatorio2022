package com.integrador_info.integrador.domain;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
/*
De las siguientes entidades se necesita conocer y registrar
ARTICLE:
id (autogenerado)
title: Representa el titulo
description: Breve descripcion de la noticia
url: Link hacia la pagina de la noticia (ej:
https://www.infobae.com/america/ciencia-america/2022/07/12/en-vivo-la-nasa)
urlToImage: Link de la imagen de portada (ej:
https://www.infobae.com/new-resizer/4q_cPUh59XY.jpg)
publishedAt: Fecha de publicacion
content: Texto completo del contenido de la noticia
Author: Relacion con Author
Source: Relacion con Fuente de la noticia
 */
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private LocalDate publishedAt;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Autor author;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Fuente source;


    public Article() {
    }

    public Article(Long id, String title, String description, String url, String urlToImage, LocalDate publishedAt, String content, Autor author, Fuente source) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.author = author;
        this.source = source;
    }

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

    public Autor getAuthor() {
        return this.author;
    }

    public void setAuthor(Autor author) {
        this.author = author;
    }

    public Fuente getSource() {
        return this.source;
    }

    public void setSource(Fuente source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Article)) {
            return false;
        }
        Article article = (Article) o;
        return Objects.equals(id, article.id) && Objects.equals(title, article.title) && Objects.equals(description, article.description) && Objects.equals(url, article.url) && Objects.equals(urlToImage, article.urlToImage) && Objects.equals(publishedAt, article.publishedAt) && Objects.equals(content, article.content) && Objects.equals(author, article.author) && Objects.equals(source, article.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, url, urlToImage, publishedAt, content, author, source);
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", url='" + getUrl() + "'" +
            ", urlToImage='" + getUrlToImage() + "'" +
            ", publishedAt='" + getPublishedAt() + "'" +
            ", content='" + getContent() + "'" +
            ", author='" + getAuthor() + "'" +
            ", source='" + getSource() + "'" +
            "}";
    }
    
    
}
