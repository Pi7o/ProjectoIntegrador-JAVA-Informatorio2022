package com.integrador_info.integrador.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.integrador_info.integrador.domain.Autor;
import com.integrador_info.integrador.dto.AutorDTO;
import com.integrador_info.integrador.repository.AutorRepository;
import com.integrador_info.integrador.service.AutorService;
import com.integrador_info.integrador.service.IAutorService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Objects;
import java.text.ParseException;

@RestController
@RequestMapping("/autor")
public class AutorController {
    private final AutorRepository autorRepository;

    @Autowired
    private IAutorService autorService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public AutorController(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    private AutorDTO convertToDTO(Autor autor){
        AutorDTO autorDTO = modelMapper.map(autor, AutorDTO.class);
        return autorDTO ;
    }
    private Autor convertToEntity(AutorDTO autorDTO) throws ParseException{
        Autor autor = modelMapper.map(autorDTO, Autor.class);
        if (autorDTO.getId()!= null){
            Autor oldAutor = autorService.getAutorById(autorDTO.getId());
            autor.setId(oldAutor.getId());
            autor.setCreatedAt(oldAutor.getCreatedAt());
            autor.setArticulos(oldAutor.getArticulos());
        }
        return autor;
    }

    //obtener autores paginado
    @GetMapping // **/autor  con iso "AAAA-MM-DD"
    public List<AutorDTO> buscarAutoresPaginado(
        @RequestParam (value="firstname", required = false) String firstname,
        @RequestParam (value="iso", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
        @RequestParam (value="page", required = false, defaultValue = "0") int page,
        @RequestParam (value="size", required = false, defaultValue = "10") int size,
        @RequestParam (value="sortDir", required = false, defaultValue = "asc") String sortDir,
        @RequestParam (value="sort", required = false, defaultValue = "id") String sort) {

        List<Autor> autores = autorService.getAutorList(firstname,date,page,size,sortDir,sort);
        System.out.println("entro controller para buscar autores");
        System.out.println(autores.get(0).getFullname());
        return autores.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public AutorDTO createAutor(@RequestBody AutorDTO autorDTO)throws ParseException {
        Autor autortemp = convertToEntity(autorDTO);
        autortemp.setCreatedAt(LocalDate.now());
        Autor autorCreado = autorService.createAutor(autortemp);
        return convertToDTO(autorCreado);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public AutorDTO getAutorPorId(@PathVariable("id") Long id){
        return convertToDTO(autorService.getAutorById(id));
    }

    //@GetMapping
    //public ResponseEntity<?> getAll(){
    //    return new ResponseEntity(autorRepository.findAll(), HttpStatus.OK);
    //}
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void modificarAutor(@PathVariable("id") Long id , @RequestBody AutorDTO autorDTO) throws ParseException{
        if(!Objects.equals(id, autorDTO.getId())){
            throw new IllegalArgumentException("IDS no coinciden!! "+id.toString());
        }
        Autor autor = convertToEntity(autorDTO);
        autorService.updateAutor(autor);
    }

    @DeleteMapping("/{id}")
    public void borrarPorId(@PathVariable("id") Long id) { autorRepository.deleteById(id);}

    
}
