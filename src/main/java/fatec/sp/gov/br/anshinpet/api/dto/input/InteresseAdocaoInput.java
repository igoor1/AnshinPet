package fatec.sp.gov.br.anshinpet.api.dto.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InteresseAdocaoInput {

    @NotBlank
    private String nome;

    @Email
    private String email;

    @NotBlank
    private String sexo;

    @PastOrPresent
    private String dataNascimento;

    @NotBlank
    private String celular;

    @NotBlank
    private String cpf;

    @NotBlank
    private String descricao;

    private String status;
    private String cep;
    private String endereco;
    private String complemento;
    private String bairro;
    private String cidade;

    @Valid
    @NotNull
    private AnimalIdInput animal;
}
