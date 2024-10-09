package fatec.sp.gov.br.anshinpet.infrastructure.exception;

public class StorageException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public StorageException(String mensagem){
        super(mensagem);
    }

    public StorageException(String mensagem, Throwable causa) {
        this(String.format("Animal com ID %d não foi encontrado.", causa));
    }
}
