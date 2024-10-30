package fatec.sp.gov.br.anshinpet.domain.service;

import fatec.sp.gov.br.anshinpet.domain.exception.AnimalNaoEncontradoException;
import fatec.sp.gov.br.anshinpet.domain.exception.NegocioException;
import fatec.sp.gov.br.anshinpet.domain.model.Animal;
import fatec.sp.gov.br.anshinpet.domain.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalFotoService animalFotoService;

    public Page<Animal> listar(Pageable pageable){
        return animalRepository.findAll(pageable);
    }

    @Transactional
    public Animal salvar(Animal animal){
        return animalRepository.save(animal);
    }

    @Transactional
    public void excluir(Long animalId){
        Animal animal = buscarOuFalhar(animalId);
        animalRepository.findFotoById(animal.getId()).ifPresentOrElse((a) -> {
            animalFotoService.excluir(animal.getId());
            animalRepository.deleteById(animal.getId());
            animalRepository.flush();
        }, () -> {
            animalRepository.deleteById(animalId);
        });
    }

    public List<Animal> buscarPorNome(String animalNome){
        return animalRepository.findByNameContaining(animalNome);
    }

    public Page<Animal> buscarAdocao(Pageable pageable){
        return animalRepository.findDisponivelParaAdocao(pageable);
    }

    public Animal buscarOuFalhar(Long animalId){
        return animalRepository.findById(animalId)
                .orElseThrow(()-> new AnimalNaoEncontradoException(animalId));
    }

    public BigDecimal buscarQntdAnimais(){
        long count = animalRepository.count();
        return BigDecimal.valueOf(count);
    }

     public BigDecimal buscarQntdPorTipo(String tipo){
        long count = animalRepository.countByTipo(tipo);
        return BigDecimal.valueOf(count);
     }

     public BigDecimal qntdAnimaisDisponivel(){
        long count = animalRepository.countByAdocao("S");
        return BigDecimal.valueOf(count);
     }

    public BigDecimal qntdAnimaisNaoDisponivel(){
        long count = animalRepository.countByAdocao("N");
        return BigDecimal.valueOf(count);
    }
}
