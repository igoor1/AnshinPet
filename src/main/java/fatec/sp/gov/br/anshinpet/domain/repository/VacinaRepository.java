package fatec.sp.gov.br.anshinpet.domain.repository;

import fatec.sp.gov.br.anshinpet.domain.model.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {

    @Query("from Vacina v where v.nome LIKE concat('%',:name,'%')")
    List<Vacina> findByNameContaining(String name);
}
