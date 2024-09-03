package fatec.sp.gov.br.anshinpet.domain.service;

import fatec.sp.gov.br.anshinpet.domain.exception.AnimalNaoEncontradoException;
import fatec.sp.gov.br.anshinpet.domain.exception.EntidadeNaoEcontradaException;
import fatec.sp.gov.br.anshinpet.domain.exception.NegocioException;
import fatec.sp.gov.br.anshinpet.domain.model.Animal;
import fatec.sp.gov.br.anshinpet.domain.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

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
                    String.format("ue",animalId));
        }
    }

    public Animal buscarOuFalhar(Long animalId){
        return animalRepository.findById(animalId)
                .orElseThrow(()-> new AnimalNaoEncontradoException(animalId));
    }
}
