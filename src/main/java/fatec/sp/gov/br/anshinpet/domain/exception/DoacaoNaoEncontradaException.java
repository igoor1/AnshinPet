package fatec.sp.gov.br.anshinpet.domain.exception;

public class DoacaoNaoEncontradaException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public DoacaoNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public DoacaoNaoEncontradaException(Long doacaoId){
        this(String.format("Doacao com ID %d não foi encontrado.", doacaoId));
    }
}
