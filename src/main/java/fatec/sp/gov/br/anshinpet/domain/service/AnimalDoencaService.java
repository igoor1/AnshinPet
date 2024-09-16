package fatec.sp.gov.br.anshinpet.domain.service;

import fatec.sp.gov.br.anshinpet.domain.exception.NegocioException;
import fatec.sp.gov.br.anshinpet.domain.exception.animalDoenca.AnimalDoencaNaoEncontradoException;
import fatec.sp.gov.br.anshinpet.domain.model.Animal;
import fatec.sp.gov.br.anshinpet.domain.model.AnimalDoenca;
import fatec.sp.gov.br.anshinpet.domain.model.Doenca;
import fatec.sp.gov.br.anshinpet.domain.repository.AnimalDoencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnimalDoencaService {

    @Autowired
    private AnimalDoencaRepository animalDoencaRepository;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private DoencaService doencaService;

    public List<AnimalDoenca> listar(){
        return animalDoencaRepository.findAll();
    }

    @Transactional
    public AnimalDoenca salvar(AnimalDoenca animalDoenca){
        return animalDoencaRepository.save(animalDoenca);
    }

    @Transactional
    public AnimalDoenca adicionar(AnimalDoenca animalDoenca){

        Long animalId = animalDoenca.getAnimal().getId();
        Long doencaId = animalDoenca.getDoenca().getId();
        String status = animalDoenca.getStatus();
        String descricao = animalDoenca.getDescricao();

        Animal animal = animalService.buscarOuFalhar(animalId);
        Doenca doenca = doencaService.buscarOuFalhar(doencaId);

        AnimalDoenca addAnimalDoenca = new AnimalDoenca(animal, doenca, status, descricao);
        return animalDoencaRepository.save(addAnimalDoenca);
    }

    @Transactional
    public void excluir (Long animalDoencaId){
        try{
            animalDoencaRepository.deleteById(animalDoencaId);
        }catch (EmptyResultDataAccessException e){
            throw new AnimalDoencaNaoEncontradoException(animalDoencaId);
        }catch (DataIntegrityViolationException e ){
            throw new NegocioException("AnimalDoença não pode ser removido, pois está em uso.");
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