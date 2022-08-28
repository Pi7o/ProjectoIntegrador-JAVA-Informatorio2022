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

import com.integrador_info.integrador.domain.Article;
import com.integrador_info.integrador.dto.ArticleDTO;
import com.integrador_info.integrador.repository.ArticleRepository;
import com.integrador_info.integrador.service.IArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleRepository articleRepository;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ArticleController(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    private ArticleDTO convertToDTO(Article article){
        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);
        return articleDTO ;
    }
    private Article convertToEntity(ArticleDTO articleDTO) throws ParseException{
        Article article = modelMapper.map(articleDTO, Article.class);
        if (articleDTO.getId()!= null){
            Article oldArticle = articleService.getArticleById(articleDTO.getId());
            article.setId(oldArticle.getId());
            article.setPublishedAt(oldArticle.getPublishedAt());
            article.setAuthor(oldArticle.getAuthor());
            article.setSource(oldArticle.getSource());
        }
        return article;
    }

    //obtener Articles paginado
    @GetMapping // **/Article  con iso "AAAA-MM-DD"
    public List<ArticleDTO> buscarArticlesPaginado(
        @RequestParam (value="busqueda", required = false) String busqueda,
        @RequestParam (value="iso", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
        @RequestParam (value="page", required = false, defaultValue = "0") int page,
        @RequestParam (value="size", required = false, defaultValue = "10") int size,
        @RequestParam (value="sortDir", required = false, defaultValue = "asc") String sortDir,
        @RequestParam (value="sort", required = false, defaultValue = "id") String sort) {

        List<Article> articles = articleService.getArticleList(busqueda,busqueda,date,page,size,sortDir,sort);
        System.out.println("entro controller para buscar articulos");
        System.out.println(articles.get(0).getTitle());
        return articles.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ArticleDTO createArticle(@RequestBody Article article) {
        Article articleCreado = articleService.createArticle(article);
        return convertToDTO(articleCreado);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ArticleDTO getArticlePorID(@PathVariable("id") Long id){
        return convertToDTO(articleService.getArticleById(id));
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void modificarArticle(@PathVariable("id") Long id , @RequestBody ArticleDTO articleDTO) throws ParseException{
        if(!Objects.equals(id, articleDTO.getId())){
            throw new IllegalArgumentException("IDS no coinciden!! "+id.toString());
        }
        Article Article = convertToEntity(articleDTO);
        articleService.updateArticle(Article);
    }

    @DeleteMapping("/{id}")
    public void borrarPorId(@PathVariable("id") Long id) { articleRepository.deleteById(id);}

}