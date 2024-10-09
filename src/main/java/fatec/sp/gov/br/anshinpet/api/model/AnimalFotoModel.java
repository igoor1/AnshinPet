package fatec.sp.gov.br.anshinpet.api.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnimalFotoModel {

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;
}
