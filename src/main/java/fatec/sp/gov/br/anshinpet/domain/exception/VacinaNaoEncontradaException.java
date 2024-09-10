package fatec.sp.gov.br.anshinpet.domain.exception;

public class VacinaNaoEncontradaException extends EntidadeNaoEncontradaException{

    private  static final long serialVersionUID = 1L;

    public VacinaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public VacinaNaoEncontradaException(Long vacinaId) {
        this(String.format("Vacina com ID %d não foi encontrado.", vacinaId));
    }
}
