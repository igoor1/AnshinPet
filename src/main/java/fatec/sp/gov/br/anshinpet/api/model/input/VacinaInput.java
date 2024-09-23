package fatec.sp.gov.br.anshinpet.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VacinaInput {

    @NotBlank
    private String nome;

    @NotBlank
    private String fabricante;
}
