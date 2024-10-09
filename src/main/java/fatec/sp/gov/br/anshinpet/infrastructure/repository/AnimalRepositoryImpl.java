package fatec.sp.gov.br.anshinpet.infrastructure.repository;

import fatec.sp.gov.br.anshinpet.domain.model.AnimalFoto;
import fatec.sp.gov.br.anshinpet.domain.repository.AnimalRepositoryQueries;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AnimalRepositoryImpl implements AnimalRepositoryQueries {

    @Autowired
    private EntityManager manager;

    @Override
    @Transactional
    public AnimalFoto save(AnimalFoto foto) {
        return manager.merge(foto);
    }

    @Override
    @Transactional
    public void delete(AnimalFoto foto) {
        manager.remove(foto);
    }
}
