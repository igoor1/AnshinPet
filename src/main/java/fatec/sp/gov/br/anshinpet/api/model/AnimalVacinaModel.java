package fatec.sp.gov.br.anshinpet.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AnimalVacinaModel {

    private Long id;
    private String lote;
    private LocalDate dataAplicacao;
    private AnimalModel animal;
    private VacinaModel vacina;
}
