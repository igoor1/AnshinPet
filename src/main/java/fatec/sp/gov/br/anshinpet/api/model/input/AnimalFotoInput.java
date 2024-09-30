package fatec.sp.gov.br.anshinpet.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AnimalFotoInput {

    @NotNull
    private MultipartFile arquivo;

    @NotBlank
    private String descricao;
}
