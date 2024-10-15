package fatec.sp.gov.br.anshinpet.domain.exception;

public class AnimalDoencaNaoEncontradoException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public AnimalDoencaNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public AnimalDoencaNaoEncontradoException(Long animalDoencaId){
        this(String.format("Animal Doença %d não cadastrada", animalDoencaId));
    }
}
