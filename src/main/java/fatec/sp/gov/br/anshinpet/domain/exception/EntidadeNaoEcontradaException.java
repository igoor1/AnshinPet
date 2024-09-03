package fatec.sp.gov.br.anshinpet.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public abstract class EntidadeNaoEcontradaException extends NegocioException{
    private  static final long serialVersionUID = 1L;

    public EntidadeNaoEcontradaException(String mensagem){
        super(mensagem);
    }
}


