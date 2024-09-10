package fatec.sp.gov.br.anshinpet.domain.repository;

import fatec.sp.gov.br.anshinpet.domain.model.AnimalDoenca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalDoencaRepository extends JpaRepository<AnimalDoenca, Long> {
}
