package fatec.sp.gov.br.anshinpet.domain.exception;

public class InteresseNaoEncontradoException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public InteresseNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public InteresseNaoEncontradoException(Long id){
        this(String.format("Interrese em adoção com id: %d nao cadastrado", id));
    }
}
