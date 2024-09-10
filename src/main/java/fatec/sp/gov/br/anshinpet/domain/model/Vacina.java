package fatec.sp.gov.br.anshinpet.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Vacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String fabricante;

    @OneToMany(mappedBy = "vacina")
    @JsonIgnore
    private Set<AnimalVacina> animais;
}
