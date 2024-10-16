package fatec.sp.gov.br.anshinpet.api.dto.input;

import fatec.sp.gov.br.anshinpet.domain.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

    @NotBlank
    private String nome;

    @NotBlank
    private String celular;

    @NotBlank
    private String cpf;

    @NotBlank
    private String sexo;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    @NotNull
    private Role role;

}
