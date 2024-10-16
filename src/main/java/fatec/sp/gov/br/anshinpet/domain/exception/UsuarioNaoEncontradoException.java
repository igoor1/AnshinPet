package fatec.sp.gov.br.anshinpet.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException{

    private  static final long serialVersionUID = 1L;

    public UsuarioNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public UsuarioNaoEncontradoException(Long usuarioId) {
        super(String.format("Usuário com ID %d não foi encontrado", usuarioId));
    }
}
