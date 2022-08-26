package com.integrador_info.integrador.dto;

import java.time.LocalDate;
import java.util.List;

public class FuenteDTO {
    private Long id;
    private String name;
    private String code;
    private LocalDate createdAt;
    private List<ArticleDTO> articulos;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public List<ArticleDTO> getArticulos() {
        return this.articulos;
    }

    public void setArticulos(List<ArticleDTO> articulos) {
        this.articulos = articulos;
    }

}
