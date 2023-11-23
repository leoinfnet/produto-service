package br.com.acme.produtoservice;

import br.com.acme.produtoservice.exception.ProdutoNotFoundException;
import br.com.acme.produtoservice.model.Produto;
import br.com.acme.produtoservice.repository.ProdutoRepository;
import br.com.acme.produtoservice.service.ProdutoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(value = "test")
public class ProdutoServiceTest {
    Logger LOGGER = LoggerFactory.getLogger(ProdutoServiceTest.class);
    @Autowired
    ProdutoRepository repository;
    @Autowired
    ProdutoService produtoService;
    @BeforeEach
    public void setUp(){
        Produto geladeira = Produto.builder().id(1).nome("Geladeira").valor(new BigDecimal("4500.00")).build();
        Produto playstation = Produto.builder().id(2).nome("Playstation").valor(new BigDecimal("9500.99")).build();
        List<Produto> produtos = List.of(geladeira, playstation);
        repository.saveAll(produtos);
    }
    @Test
    public void testaGetAll(){
        List<Produto> all = produtoService.getAll();
        Produto produto = all.get(0);
        assertEquals(2,all.size());
        assertEquals("Geladeira",produto.getNome());
    }
    @Test
    public void testaGetById(){
        Produto geladeira = produtoService.getById(1);
        assertEquals("Geladeira", geladeira.getNome());
        assertEquals(new BigDecimal("4500.00"),geladeira.getValor());
        assertThrows(ProdutoNotFoundException.class, () ->{
            Produto surprise = produtoService.getById(39);
        });


    }
    @Test
    public void testaDelete(){
        produtoService.deleteById(1);
        int size = produtoService.getAll().size();
        assertEquals(1,size);
    }
    @Test
    public void testaUpdate(){
        Produto geladeira = Produto.builder().id(1).nome("Geladeira Duplex").valor(new BigDecimal("5500.00")).build();
        produtoService.update(1, geladeira);
        int size = produtoService.getAll().size();
        assertEquals(2,size);
        Produto geladeiraFound = produtoService.getById(1);
        assertEquals("Geladeira Duplex", geladeiraFound.getNome());
        assertEquals(new BigDecimal("5500.00"), geladeiraFound.getValor());
    }
    @Test
    public void testaCreate(){
        Produto macBook = Produto.builder().nome("Mac Book Air").valor(new BigDecimal("20000.99")).build();
        Produto created = produtoService.create(macBook);
        int size = produtoService.getAll().size();
        assertEquals(3,size);
        assertNotEquals(0, created.getId());
    }
    @AfterEach
    public void tearDown(){
       repository.deleteAll();
    }

}
