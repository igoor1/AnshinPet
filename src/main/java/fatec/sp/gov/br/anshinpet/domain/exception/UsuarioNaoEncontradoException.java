package fatec.sp.gov.br.anshinpet.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException{

    private  static final long serialVersionUID = 1L;

    public UsuarioNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public UsuarioNaoEncontradoException(String email) {
        super(String.format("O email ja esta cadastrado.", email));
    }
}
