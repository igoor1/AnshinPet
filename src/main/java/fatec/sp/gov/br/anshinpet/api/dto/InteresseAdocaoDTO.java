package fatec.sp.gov.br.anshinpet.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InteresseAdocaoDTO {
    private Long id;
    private String nome;
    private String email;
    private String sexo;
    private String dataNascimento;
    private String celular;
    private String status;
    private String cpf;
    private String descricao;
    private String cep;
    private String endereco;
    private String complemento;
    private String bairro;
    private String cidade;
    private AnimalDTO animal;
}