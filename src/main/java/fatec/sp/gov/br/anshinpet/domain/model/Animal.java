package fatec.sp.gov.br.anshinpet.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sexo;
    private String tipo;
    private LocalDate data;
    private String cor;
    private String porte;
    private String castrado;
    private String adocao;
    private String raca;

    @OneToMany(mappedBy = "animal")
    @JsonIgnore
    private Set<AnimalDoenca> doencas;

    @OneToMany(mappedBy = "animal")
    @JsonIgnore
    private Set<AnimalVacina> vacina;
}
