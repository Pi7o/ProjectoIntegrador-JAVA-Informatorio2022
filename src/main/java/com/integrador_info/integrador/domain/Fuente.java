package com.integrador_info.integrador.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/*
 De las siguientes entidades se necesita conocer y registrar
Source:
id (autogenerado)
name: Nombre de la Fuente (ejemplo: Infobae)
code: Representa el nombre con un patron (ej: Si el nombre es: La Nacion, el code sera
la-nacion) de solo minusculas y los espacios en blanco se reemplazan con el simbolo -
createdAt: Fecha de creacion
 */
@Entity
public class Fuente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private LocalDate createdAt;

    @OneToMany(mappedBy = "source",cascade = CascadeType.ALL)
    private List<Article> articulos;

    public Fuente() {
    }

    public Fuente(Long id, String name) {
        this.id = id;
        this.name = name;
        this.code = name.trim().replaceAll("\\s+","-");
        this.createdAt = LocalDate.now();
    }

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
        this.code = name.trim().replaceAll("\\s+","-");
        
    }

    public String getCode() {
        return this.code;
    }

    public void setCreatedAt(LocalDate createdAt){
        this.createdAt = createdAt;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public void setArticulos(List<Article> articulos) {
        this.articulos = articulos;
    }
    public List<Article> getArticulos() {
        return this.articulos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Fuente)) {
            return false;
        }
        Fuente fuente = (Fuente) o;
        return Objects.equals(id, fuente.id) && Objects.equals(name, fuente.name) && Objects.equals(code, fuente.code) && Objects.equals(createdAt, fuente.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, createdAt);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            "}";
    }


}
