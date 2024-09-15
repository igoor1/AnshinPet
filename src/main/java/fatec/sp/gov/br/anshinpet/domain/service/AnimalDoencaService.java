package fatec.sp.gov.br.anshinpet.domain.service;

import fatec.sp.gov.br.anshinpet.domain.exception.NegocioException;
import fatec.sp.gov.br.anshinpet.domain.exception.animalDoenca.AnimalDoencaNaoEncontradoException;
import fatec.sp.gov.br.anshinpet.domain.model.AnimalDoenca;
import fatec.sp.gov.br.anshinpet.domain.repository.AnimalDoencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalDoencaService {

    @Autowired
    private AnimalDoencaRepository animalDoencaRepository;

    public AnimalDoenca salvar(AnimalDoenca animalDoenca){
        return animalDoencaRepository.save(animalDoenca);
    }

    public void excluir (Long animalDoencaId){
        try{
            animalDoencaRepository.deleteById(animalDoencaId);
        }catch (EmptyResultDataAccessException e){
            throw new AnimalDoencaNaoEncontradoException(animalDoencaId);
        }catch (DataIntegrityViolationException e){
            throw new NegocioException(String.format("ue", animalDoencaId));
        }
    }

    public AnimalDoenca buscarOuFalhar(Long animalDoencaId){
        return animalDoencaRepository.findById(animalDoencaId)
                .orElseThrow(()-> new AnimalDoencaNaoEncontradoException(animalDoencaId));
    }

    public List<AnimalDoenca> buscarPorAnimal(Long animalId){
        return animalDoencaRepository.findByAnimalId(animalId);
    }
}