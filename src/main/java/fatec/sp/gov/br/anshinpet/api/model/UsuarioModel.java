package fatec.sp.gov.br.anshinpet.api.model;

import fatec.sp.gov.br.anshinpet.domain.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {

    private String nome;
    private String celular;
    private String cpf;
    private String sexo;
    private String email;
    private Role role;
}
