package fatec.sp.gov.br.anshinpet.domain.service;

import fatec.sp.gov.br.anshinpet.domain.exception.AnimalNaoEncontradoException;
import fatec.sp.gov.br.anshinpet.domain.exception.NegocioException;
import fatec.sp.gov.br.anshinpet.domain.model.Animal;
import fatec.sp.gov.br.anshinpet.domain.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnimalService {

    private static final String MSG_ANIMAL_EM_USO
            = "Animal de código %d não pode ser removido, pois está em uso";

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalFotoService animalFotoService;

    public List<Animal> listar(){
        return animalRepository.findAll();
    }

    @Transactional
    public Animal salvar(Animal animal){
        return animalRepository.save(animal);
    }

    @Transactional
    public void excluir(Long animalId){
        try{
            var foto = animalFotoService.buscarOuFalhar(animalId);
            if (foto != null) {
                animalFotoService.excluir(animalId);
            }
            animalRepository.deleteById(animalId);

        } catch (EmptyResultDataAccessException e){
            throw new AnimalNaoEncontradoException(animalId);

        } catch (DataIntegrityViolationException e){
            throw new NegocioException(
                    String.format(MSG_ANIMAL_EM_USO,animalId));
        }
    }

    public List<Animal> buscarPorNome(String animalNome){
        return animalRepository.findByNameContaining(animalNome);
    }

    public Animal buscarOuFalhar(Long animalId){
        return animalRepository.findById(animalId)
                .orElseThrow(()-> new AnimalNaoEncontradoException(animalId));
    }
}
