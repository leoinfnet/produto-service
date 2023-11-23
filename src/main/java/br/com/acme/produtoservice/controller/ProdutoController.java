package br.com.acme.produtoservice.controller;

import br.com.acme.produtoservice.exception.ProdutoNotFoundException;
import br.com.acme.produtoservice.model.Produto;
import br.com.acme.produtoservice.payload.ResponsePayload;
import br.com.acme.produtoservice.service.ProdutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ProdutoController {
    @Autowired
    ProdutoService produtoService;
    Logger LOGGER = LoggerFactory.getLogger(ProdutoController.class);
    @GetMapping
    public List<Produto> getAll(){
        List<Produto> all = produtoService.getAll();
        LOGGER.info("GET ALL:" + all);
        return all;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id){
        try{
            Produto produto = produtoService.getById(id);
            LOGGER.info("GET BY ID: "+ produto);
            return ResponseEntity.ok(produto);
        }catch (ProdutoNotFoundException ex){
            ResponsePayload notFound = ResponsePayload.builder().message("Not Found").dateTime(LocalDateTime.now()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
        }

    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        LOGGER.info("DELETE: " + id);
        produtoService.deleteById(id);
    }
    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody Produto produto){
        Produto created = produtoService.create(produto);
        LOGGER.info("CREATED: " + created);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody Produto produto){
        Produto updated = produtoService.update(id, produto);
        LOGGER.info("UPDATE: " + updated);
    }


}
