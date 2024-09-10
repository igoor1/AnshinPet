package fatec.sp.gov.br.anshinpet.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class AnimalDoenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "doenca_id")
    private Doenca doenca;

    private String status;
    private String descricao;

    public AnimalDoenca(Animal animal, Doenca doenca, String status, String descricao){
        this.animal = animal;
        this.doenca = doenca;
        this.status = status;
        this.descricao = descricao;
    }
}
