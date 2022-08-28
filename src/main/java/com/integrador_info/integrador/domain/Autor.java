package com.integrador_info.integrador.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

/*
 De las siguientes entidades se necesita conocer y registrar
Author:
id (autogenerado)
firstname: Primer Nombre
lastname: Apellido
fullname: Nombre completo (firstname + apellido)
createdAt: Fecha de creacion 

 */
@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String firstname ;
    @NotEmpty
    private String lastname ;
    private String fullname ;
    private LocalDate createdAt;

    @OneToMany(mappedBy = "author",cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Article> articulos;

    public Autor() {
    }

    public Autor(Long id) {
        this.id = id;
    }


    public Autor(Long id, String firstname, String lastname, LocalDate createdAt) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.fullname = firstname +" " + lastname;
        this.createdAt = createdAt;
    }
    
    public Autor(String firstname, String lastname, LocalDate createdAt) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.fullname = firstname +" " + lastname;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
        this.fullname = firstname +" "+ this.lastname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
        this.fullname = this.firstname +" "+ lastname;
    }

    public String getFullname() {
        return this.fullname;
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
        if (!(o instanceof Autor)) {
            return false;
        }
        Autor autor = (Autor) o;
        return Objects.equals(id, autor.id) && Objects.equals(firstname, autor.firstname) && Objects.equals(lastname, autor.lastname) && Objects.equals(fullname, autor.fullname) && Objects.equals(createdAt, autor.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, fullname, createdAt);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", fullname='" + getFullname() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            "}";
    }
    
}
