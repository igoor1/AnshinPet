package fatec.sp.gov.br.anshinpet.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoencaInput {

    @NotBlank
    private String nome;

    @NotBlank
    private String gravidade;
}
