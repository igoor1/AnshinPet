package fatec.sp.gov.br.anshinpet.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnimalFotoDTO {

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;
}
