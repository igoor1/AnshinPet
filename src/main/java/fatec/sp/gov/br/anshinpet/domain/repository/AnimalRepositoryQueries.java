package fatec.sp.gov.br.anshinpet.domain.repository;

import fatec.sp.gov.br.anshinpet.domain.model.AnimalFoto;

public interface AnimalRepositoryQueries {

    AnimalFoto save(AnimalFoto foto);
    void delete(AnimalFoto foto);
}
