package com.integrador_info.integrador.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.integrador_info.integrador.domain.Fuente;
import com.integrador_info.integrador.repository.FuenteRepository;

@Service
public class FuenteService implements IFuenteService{
    @Autowired
    private FuenteRepository fuenteRepository;

    @Override
    public List<Fuente> getFuenteList(String name,LocalDate date,int page, int size, String sortDir, String sort){
        Pageable pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
        Page<Fuente> fuentes;
        if(name != null){
            fuentes = fuenteRepository.findByName(name, pageReq);
        }else {
            if(date!=null){
                fuentes = fuenteRepository.findByCreatedAtIsAfter(date, pageReq);
            }else{
                fuentes = fuenteRepository.findAll(pageReq);
            } 
        }
        return fuentes.getContent();
    }

    @Override
    public void updateFuente(Fuente fuente){
        fuenteRepository.save(fuente);
    }

    @Override
    public Fuente createFuente(Fuente fuente){
        return fuenteRepository.save(fuente);
    }

    @Override
    public Fuente getFuenteById(Long id){
        return fuenteRepository.getReferenceById(id);
    }
}
