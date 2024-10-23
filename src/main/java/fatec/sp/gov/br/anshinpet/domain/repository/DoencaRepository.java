package fatec.sp.gov.br.anshinpet.domain.repository;

import fatec.sp.gov.br.anshinpet.domain.model.Doenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoencaRepository extends JpaRepository<Doenca, Long> {

    @Query("from Doenca d where d.nome LIKE concat('%',:name,'%')")
    List<Doenca> findByNameContaining(String name);
}
