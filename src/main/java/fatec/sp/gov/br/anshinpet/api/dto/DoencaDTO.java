package fatec.sp.gov.br.anshinpet.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoencaDTO {

    private long id;
    private String nome;
    private String gravidade;
}
