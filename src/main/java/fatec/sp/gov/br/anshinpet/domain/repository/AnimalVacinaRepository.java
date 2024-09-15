package fatec.sp.gov.br.anshinpet.domain.repository;

import fatec.sp.gov.br.anshinpet.domain.model.AnimalVacina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalVacinaRepository extends JpaRepository<AnimalVacina, Long> {

    List<AnimalVacina> findByAnimalId(Long animalId);

}
