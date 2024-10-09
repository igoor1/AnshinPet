package fatec.sp.gov.br.anshinpet.domain.exception;

public class FotoNaoEncontradaException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public FotoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public FotoNaoEncontradaException(Long animalId) {
        this(String.format("Não existe um cadastro de foto com código %d.", animalId));
    }
}
