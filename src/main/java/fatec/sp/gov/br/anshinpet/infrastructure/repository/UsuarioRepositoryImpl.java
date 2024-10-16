package fatec.sp.gov.br.anshinpet.infrastructure.repository;

import fatec.sp.gov.br.anshinpet.domain.model.UsuarioFoto;
import fatec.sp.gov.br.anshinpet.domain.repository.UsuarioRepositoryQueries;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepositoryQueries {

    @Autowired
    private EntityManager manager;

    public UsuarioFoto save(UsuarioFoto foto){
        return manager.merge(foto);
    }

    @Override
    public void delete(UsuarioFoto foto) {
        manager.remove(foto);
    }
}
