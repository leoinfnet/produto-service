package br.com.acme.produtoservice.repository;

import br.com.acme.produtoservice.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}
