package fatec.sp.gov.br.anshinpet.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalDoencaInput {

    @NotBlank
    private String status;

    @NotBlank
    private String descricao;

    @Valid
    @NotNull
    private AnimalIdInput animal;

    @Valid
    @NotNull
    private DoencaIdInput doenca;
}
