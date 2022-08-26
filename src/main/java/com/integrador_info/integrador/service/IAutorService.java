package com.integrador_info.integrador.service;

import java.util.List;

import com.integrador_info.integrador.domain.Autor;

public interface IAutorService {
    List<Autor> getAutorList(String firstname,int page, int size, String sortDir, String sort);

    void updateAutor(Autor autor);

    Autor createAutor(Autor autor);

    Autor getAutorById(Long id);
}
