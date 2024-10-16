package fatec.sp.gov.br.anshinpet.domain.repository;

import fatec.sp.gov.br.anshinpet.domain.model.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoacaoRepository extends JpaRepository<Doacao, Long> {

    List<Doacao> findByTipo(String tipo);

    @Query("select count(a) from Doacao a where a.tipo = :tipo")
    Long countByDoacao(@Param("tipo") String tipo);
}
