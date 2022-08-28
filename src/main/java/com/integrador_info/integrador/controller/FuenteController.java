package com.integrador_info.integrador.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Objects;
import java.text.ParseException;

import com.integrador_info.integrador.domain.Fuente;
import com.integrador_info.integrador.dto.FuenteDTO;
import com.integrador_info.integrador.repository.FuenteRepository;
import com.integrador_info.integrador.service.IFuenteService;

@RestController
@RequestMapping("/fuente")
public class FuenteController {
    private final FuenteRepository fuenteRepository;

    @Autowired
    private IFuenteService fuenteService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public FuenteController(FuenteRepository fuenteRepository){
        this.fuenteRepository = fuenteRepository;
    }

    private FuenteDTO convertToDTO(Fuente fuente){
        FuenteDTO fuenteDTO = modelMapper.map(fuente, FuenteDTO.class);
        return fuenteDTO ;
    }
    private Fuente convertToEntity(FuenteDTO fuenteDTO) throws ParseException{
        Fuente fuente = modelMapper.map(fuenteDTO, Fuente.class);
        if (fuenteDTO.getId()!= null){
            Fuente olfFuente = fuenteService.getFuenteById(fuenteDTO.getId());
            fuente.setId(olfFuente.getId());
            fuente.setCreatedAt(olfFuente.getCreatedAt());
            fuente.setArticulos(olfFuente.getArticulos());
        }
        return fuente;
    }

    //obtener fuentes paginado
    @GetMapping // **/fuente  con iso "AAAA-MM-DD"
    public List<FuenteDTO> buscarFuentesPaginado(
        @RequestParam (value="name", required = false) String name,
        @RequestParam (value="iso", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
        @RequestParam (value="page", required = false, defaultValue = "0") int page,
        @RequestParam (value="size", required = false, defaultValue = "10") int size,
        @RequestParam (value="sortDir", required = false, defaultValue = "asc") String sortDir,
        @RequestParam (value="sort", required = false, defaultValue = "id") String sort) {

        List<Fuente> fuentes = fuenteService.getFuenteList(name,date,page,size,sortDir,sort);
        System.out.println("entro controller para buscar autores");
        System.out.println(fuentes.get(0).getCode());
        return fuentes.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public FuenteDTO createFuente(@RequestBody FuenteDTO fuenteDTO)throws ParseException {
        Fuente fuentetemp = convertToEntity(fuenteDTO);
        fuentetemp.setCreatedAt(LocalDate.now());
        Fuente fuenteCreado = fuenteService.createFuente(fuentetemp);
        return convertToDTO(fuenteCreado);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public FuenteDTO getFuentePorID(@PathVariable("id") Long id){
        return convertToDTO(fuenteService.getFuenteById(id));
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void modificarFuente(@PathVariable("id") Long id , @RequestBody FuenteDTO fuenteDTO) throws ParseException{
        if(!Objects.equals(id, fuenteDTO.getId())){
            throw new IllegalArgumentException("IDS no coinciden!! "+id.toString());
        }
        Fuente fuente = convertToEntity(fuenteDTO);
        fuenteService.updateFuente(fuente);
    }

    @DeleteMapping("/{id}")
    public void borrarPorId(@PathVariable("id") Long id) { fuenteRepository.deleteById(id);}
}
