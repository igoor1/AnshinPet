package fatec.sp.gov.br.anshinpet.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalDoencaDTO {

    private Long id;
    private String status;
    private String descricao;
    private AnimalDTO animal;
    private DoencaDTO doenca;
}
