package fatec.sp.gov.br.anshinpet.domain.repository;

import fatec.sp.gov.br.anshinpet.domain.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query("from Animal a where a.nome LIKE concat('%',:name,'%')")
    List<Animal> findByNameContaining(String name);
}
