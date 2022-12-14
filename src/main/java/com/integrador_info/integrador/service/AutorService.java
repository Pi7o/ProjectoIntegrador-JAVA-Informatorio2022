package com.integrador_info.integrador.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.integrador_info.integrador.domain.Autor;
import com.integrador_info.integrador.repository.AutorRepository;

@Service
public class AutorService implements IAutorService{
    @Autowired
    private AutorRepository autorRepository;

    @Override
    public List<Autor> getAutorList(String fullname,LocalDate date,int page, int size, String sortDir, String sort){
        Pageable pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
        Page<Autor> autors;
        if(fullname != null){
            autors = autorRepository.findByFullnameContains(fullname, pageReq);
        }else {
            if(date!=null){
                autors = autorRepository.findByCreatedAtIsAfter(date, pageReq);
            }else{
                autors = autorRepository.findAll(pageReq);
            } 
        }
        return autors.getContent();
    }

    @Override
    public void updateAutor(Autor autor){
        autorRepository.save(autor);
    }

    @Override
    public Autor createAutor(Autor autor){
        autor.setCreatedAt(LocalDate.now());
        return autorRepository.save(autor);
    }

    @Override
    public Autor getAutorById(Long id){
        return autorRepository.getReferenceById(id);
    }
    
}
