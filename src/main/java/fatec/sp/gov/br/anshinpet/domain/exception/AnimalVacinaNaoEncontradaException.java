package fatec.sp.gov.br.anshinpet.domain.exception;

public class AnimalVacinaNaoEncontradaException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public AnimalVacinaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public AnimalVacinaNaoEncontradaException(Long animalVacinaId) {
        this(String.format("Animal com ID %d não foi encontrado.", animalVacinaId));
    }
}
