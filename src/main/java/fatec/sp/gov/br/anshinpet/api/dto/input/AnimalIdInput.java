package fatec.sp.gov.br.anshinpet.api.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalIdInput {

    @NotNull
    private Long id;
}
