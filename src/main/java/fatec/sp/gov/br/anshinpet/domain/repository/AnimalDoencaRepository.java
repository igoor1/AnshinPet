package fatec.sp.gov.br.anshinpet.domain.repository;

import fatec.sp.gov.br.anshinpet.domain.model.AnimalDoenca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalDoencaRepository extends JpaRepository<AnimalDoenca, Long> {

    List<AnimalDoenca> findByAnimalId(Long animalId);
}
