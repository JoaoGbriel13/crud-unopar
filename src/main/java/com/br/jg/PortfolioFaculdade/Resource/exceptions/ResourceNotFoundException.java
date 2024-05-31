package com.br.jg.PortfolioFaculdade.Resource.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super("Não foi encontrado nenhum usuario com essa informação");
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
