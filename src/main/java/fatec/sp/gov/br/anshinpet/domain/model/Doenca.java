package fatec.sp.gov.br.anshinpet.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
public class Doenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String gravidade;

    @OneToMany(mappedBy = "doenca")
    @JsonIgnore
    private Set<AnimalDoenca> animais;
}
