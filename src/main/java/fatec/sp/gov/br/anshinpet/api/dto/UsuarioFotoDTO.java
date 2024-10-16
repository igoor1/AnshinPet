package fatec.sp.gov.br.anshinpet.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioFotoDTO {

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;
}
