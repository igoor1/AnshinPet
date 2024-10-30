package fatec.sp.gov.br.anshinpet.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InteresseAdocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String sexo;
    private String dataNascimento;
    private String celular;
    private String cpf;
    private String descricao;
    private String status;
    private String cep;
    private String endereco;
    private String complemento;
    private String bairro;
    private String cidade;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
}
