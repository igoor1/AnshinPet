package fatec.sp.gov.br.anshinpet.domain.repository;

import fatec.sp.gov.br.anshinpet.domain.model.InteresseAdocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InteresseAdocaoRepository extends JpaRepository<InteresseAdocao, Long> {

    @Query("from InteresseAdocao i where i.nome LIKE concat('%',:name,'%')")
    List<InteresseAdocao> findByNameContaining(String name);
}
