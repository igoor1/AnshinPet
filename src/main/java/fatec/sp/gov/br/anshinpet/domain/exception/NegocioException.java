package fatec.sp.gov.br.anshinpet.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NegocioException extends RuntimeException{

    private  static final long serialVersionUID = 1L;

    public NegocioException(String messagem, Throwable causa) {
        super(messagem, causa);
    }

    public NegocioException(String messagem) {
        super(messagem);
    }
}
