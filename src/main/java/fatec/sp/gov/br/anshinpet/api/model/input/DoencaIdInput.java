package fatec.sp.gov.br.anshinpet.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoencaIdInput {

    @NotNull
    private Long id;
}
