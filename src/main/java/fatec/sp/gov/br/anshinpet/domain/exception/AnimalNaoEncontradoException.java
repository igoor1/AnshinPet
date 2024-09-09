package fatec.sp.gov.br.anshinpet.domain.exception;

public class AnimalNaoEncontradoException extends EntidadeNaoEncontradaException {

    private  static final long serialVersionUID = 1L;

    public AnimalNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public AnimalNaoEncontradoException(Long animalId) {
        this(String.format("Animal com ID %d não foi encontrado.", animalId));
    }
}
