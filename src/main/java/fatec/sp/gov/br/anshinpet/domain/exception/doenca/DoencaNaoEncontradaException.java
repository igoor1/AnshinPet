package fatec.sp.gov.br.anshinpet.domain.exception.doenca;

import fatec.sp.gov.br.anshinpet.domain.exception.EntidadeNaoEncontradaException;

public class DoencaNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public DoencaNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public DoencaNaoEncontradaException(Long doencaId){
        this(String.format("doenca %d nao cadastrada", doencaId));
    }

}
