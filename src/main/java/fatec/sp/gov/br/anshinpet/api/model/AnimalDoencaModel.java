package fatec.sp.gov.br.anshinpet.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalDoencaModel {

    private Long id;
    private String status;
    private String descricao;
    private AnimalModel animal;
    private DoencaModel doenca;
}
