package fatec.sp.gov.br.anshinpet.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AnimalVacinaDTO {

    private Long id;
    private String lote;
    private LocalDate dataAplicacao;
    private AnimalDTO animal;
    private VacinaDTO vacina;
}
