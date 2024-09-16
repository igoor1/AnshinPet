package fatec.sp.gov.br.anshinpet.domain.service;

import fatec.sp.gov.br.anshinpet.domain.exception.AnimalVacinaNaoEncontradaException;
import fatec.sp.gov.br.anshinpet.domain.exception.VacinaNaoEncontradaException;
import fatec.sp.gov.br.anshinpet.domain.model.Animal;
import fatec.sp.gov.br.anshinpet.domain.model.AnimalVacina;
import fatec.sp.gov.br.anshinpet.domain.model.Vacina;
import fatec.sp.gov.br.anshinpet.domain.repository.AnimalRepository;
import fatec.sp.gov.br.anshinpet.domain.repository.AnimalVacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AnimalVacinaService {

    @Autowired
    private AnimalVacinaRepository animalVacinaRepository;

    @Autowired
    private AnimalService animalService;

    @Autowired VacinaService vacinaService;

    public List<AnimalVacina> listar(){
        return animalVacinaRepository.findAll();
    }

    public AnimalVacina salvar(AnimalVacina animalVacina){
        return animalVacinaRepository.save(animalVacina);
    }

    public AnimalVacina adicionar(AnimalVacina animalVacina){

        Long animalId = animalVacina.getAnimal().getId();
        Long vacinaId = animalVacina.getVacina().getId();
        String lote = animalVacina.getLote();
        LocalDate data = animalVacina.getDataAplicacao();

        Animal animal = animalService.buscarOuFalhar(animalId);
        Vacina vacina = vacinaService.buscarOuFalhar(vacinaId);

        AnimalVacina cadAplicacao = new AnimalVacina(animal, vacina, lote, data);

        return animalVacinaRepository.save(cadAplicacao);
    }

    public void excluir(Long animalVacinaId){
      try {
          animalVacinaRepository.deleteById(animalVacinaId);
      } catch (EmptyResultDataAccessException e){
          throw new AnimalVacinaNaoEncontradaException(animalVacinaId);
      }
    }

    public AnimalVacina buscarOuFalhar(Long animalVacinaId){
        return animalVacinaRepository.findById(animalVacinaId)
                .orElseThrow(()-> new VacinaNaoEncontradaException(animalVacinaId));
    }

    public List<AnimalVacina> buscarPorAnimal(Long animalId){
        return animalVacinaRepository.findByAnimalId(animalId);
    }
}
