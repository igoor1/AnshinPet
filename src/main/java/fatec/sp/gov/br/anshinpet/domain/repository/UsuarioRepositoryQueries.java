package fatec.sp.gov.br.anshinpet.domain.repository;

import fatec.sp.gov.br.anshinpet.domain.model.UsuarioFoto;

public interface UsuarioRepositoryQueries {

    UsuarioFoto save(UsuarioFoto foto);
    void delete(UsuarioFoto foto);
}
