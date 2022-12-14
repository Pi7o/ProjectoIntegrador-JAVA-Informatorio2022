package com.integrador_info.integrador.config;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.integrador_info.integrador.domain.Article;
import com.integrador_info.integrador.domain.Autor;
import com.integrador_info.integrador.domain.Fuente;
import com.integrador_info.integrador.repository.ArticleRepository;
import com.integrador_info.integrador.repository.AutorRepository;
import com.integrador_info.integrador.repository.FuenteRepository;

@Component
public class InitialtestRunner implements CommandLineRunner {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private FuenteRepository fuenteRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    private Random wearandom = new Random();

    /*
     * lo usaba para generar los nombres y apellidos 
     private String generarNombre(){
        String[] nombres = {"Carlos", "Pablo", "Marta", "Maria", "Freddie", "Jiro", "Bodhi", "Dallas", "Hiki", "Jack"};
        int numeromagico = wearandom.nextInt(nombres.length);
        String salida = nombres[numeromagico]; 
        return salida;
    }
    private String generarApellido(){
        String[] apellidos = {"Pedrao", "Kiji", "Mercuru", "Heiden", "Donma","Cassio", "Kishi","Abhdol","Jamil","Sossa"};
        int numeromagico = wearandom.nextInt(apellidos.length);
        String salida = apellidos[numeromagico]; 
        return salida;
    }
     */

    @Override
    public void run (String... args) throws Exception{
        /* lo usaba para testear relaciones creando un par de elementos en la db
        Autor autor = new Autor();
        autor.setFirstname("Juan");
        autor.setLastname("Perez");
        autor.setCreatedAt(LocalDate.now());

        Fuente fuente = new Fuente();
        fuente.setName("I made it up");
        fuente.setCreatedAt(LocalDate.now());

        Article articulo = new Article();
        articulo.setAuthor(autor);
        articulo.setTitle("titulo aqui");
        articulo.setContent("contenido aqui");
        articulo.setDescription("descript aqui");
        articulo.setPublishedAt(LocalDate.of(2020, 5, 6));
        articulo.setSource(fuente);
        articulo.setUrl("url aqui");
        articulo.setUrlToImage("urlToImage aqui");

        Fuente fuente2 = new Fuente();
        fuente2.setName("I also made this up");
        fuente2.setCreatedAt(LocalDate.now());

        Article articulo2= new Article();
        articulo2.setAuthor(autor);
        articulo2.setTitle("titulo segundo aqui");
        articulo2.setContent("contenido segundo aqui");
        articulo2.setDescription("descript segundo aqui");
        articulo2.setPublishedAt(LocalDate.of(2020, 5, 10));
        articulo2.setSource(fuente2);
        articulo2.setUrl("url segunda aqui");
        articulo2.setUrlToImage("urlToImage segunda aqui");

        autor.setArticulos(List.of(articulo,articulo2));
        fuente.setArticulos(List.of(articulo));
        fuente2.setArticulos(List.of(articulo2));

        autor = autorRepository.save(autor);
        */
        
        
        /* para comprobar paginacion cree 100 elementos aleatorios en la tabla autores
        List<Autor> autores = IntStream.rangeClosed(1, 100)
            .mapToObj(i -> new Autor(generarNombre(), generarApellido(), LocalDate.now()) )
            .collect(Collectors.toList());
        autorRepository.saveAll(autores);
        */
        System.out.println("Probando");
    }
        

    
}
