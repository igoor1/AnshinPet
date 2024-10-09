package fatec.sp.gov.br.anshinpet.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AnimalFoto {

    @Id
    @Column(name = "animal_id")
    @EqualsAndHashCode.Include
    private Long id;

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private Animal animal;

    public Long GetAnimalId(){
        if(getAnimal() != null){
            return animal.getId();
        }
        return null;
    }
}
