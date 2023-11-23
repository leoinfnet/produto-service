package br.com.acme.produtoservice.exception;

public class ProdutoNotFoundException extends RuntimeException{
    public ProdutoNotFoundException() {
    }

    public ProdutoNotFoundException(String message) {
        super(message);
    }
}
