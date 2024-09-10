package fatec.sp.gov.br.anshinpet.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class AnimalVacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lote;
    private LocalDate dataAplicacao;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "vacina_id")
    private Vacina vacina;

    public AnimalVacina(Animal animal, Vacina vacina, String lote, LocalDate dataAplicacao){
        this.animal = animal;
        this.vacina = vacina;
        this.lote = lote;
        this.dataAplicacao = dataAplicacao;
    }

    public AnimalVacina() {}
}
