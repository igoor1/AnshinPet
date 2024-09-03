package fatec.sp.gov.br.anshinpet.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AnimalNaoEncontradoException extends EntidadeNaoEcontradaException{

    private  static final long serialVersionUID = 1L;

    public AnimalNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public AnimalNaoEncontradoException(Long animalId) {
        this(String.format("animal %d nao cadastrado",animalId));
    }
}
