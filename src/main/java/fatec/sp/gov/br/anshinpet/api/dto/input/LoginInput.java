package fatec.sp.gov.br.anshinpet.api.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginInput {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String senha;
}
