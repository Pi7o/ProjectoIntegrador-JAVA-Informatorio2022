package com.integrador_info.integrador.service;

import java.time.LocalDate;
import java.util.List;

import com.integrador_info.integrador.domain.Fuente;

public interface IFuenteService {
    List<Fuente> getFuenteList(String name,LocalDate iso,int page, int size, String sortDir, String sort);

    void updateFuente(Fuente fuente);

    Fuente createFuente(Fuente fuente);

    Fuente getFuenteById(Long id);
}
