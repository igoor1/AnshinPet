package fatec.sp.gov.br.anshinpet.api.dto.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AnimalVacinaInput {

    @NotBlank
    private String lote;

    @NotNull
    private LocalDate dataAplicacao;

    @Valid
    @NotNull
    private AnimalIdInput animal;

    @Valid
    @NotNull
    private VacinaIdInput vacina;
}
