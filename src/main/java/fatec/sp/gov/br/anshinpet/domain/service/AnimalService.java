package fatec.sp.gov.br.anshinpet.domain.service;

import fatec.sp.gov.br.anshinpet.domain.exception.AnimalNaoEncontradoException;
import fatec.sp.gov.br.anshinpet.domain.exception.NegocioException;
import fatec.sp.gov.br.anshinpet.domain.model.Animal;
import fatec.sp.gov.br.anshinpet.domain.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    private static final String MSG_ANIMAL_EM_USO
            = "Animal de código %d não pode ser removido, pois está em uso";

    @Autowired
    private AnimalRepository animalRepository;

    public Animal salvar(Animal animal){
        return animalRepository.save(animal);
    }

    public void excluir(Long animalId){
        try{
            animalRepository.deleteById(animalId);

        } catch (EmptyResultDataAccessException e){
            throw new AnimalNaoEncontradoException(animalId);

        } catch (DataIntegrityViolationException e){
            throw new NegocioException(
                    String.format(MSG_ANIMAL_EM_USO,animalId));
        }
    }

    public Animal buscarOuFalhar(Long animalId){
        return animalRepository.findById(animalId)
                .orElseThrow(()-> new AnimalNaoEncontradoException(animalId));
    }
}
