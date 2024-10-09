package fatec.sp.gov.br.anshinpet.domain.repository;

import fatec.sp.gov.br.anshinpet.domain.model.Animal;
import fatec.sp.gov.br.anshinpet.domain.model.AnimalFoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long>, AnimalRepositoryQueries {

    @Query("from Animal a where a.nome LIKE concat('%',:name,'%')")
    List<Animal> findByNameContaining(String name);

    @Query("From AnimalFoto f where f.animal.id = :animalId")
    Optional<AnimalFoto> findFotoById(Long animalId);
}
