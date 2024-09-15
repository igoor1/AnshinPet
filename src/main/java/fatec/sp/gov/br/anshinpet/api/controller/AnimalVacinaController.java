package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.domain.model.AnimalVacina;
import fatec.sp.gov.br.anshinpet.domain.repository.AnimalVacinaRepository;
import fatec.sp.gov.br.anshinpet.domain.service.AnimalVacinaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/animal-vacinas")
@CrossOrigin(origins = "http://localhost:5173")
public class AnimalVacinaController {


    @Autowired
    private AnimalVacinaRepository animalVacinaRepository;

    @Autowired
    private AnimalVacinaService animalVacinaService;

    @GetMapping
    public List<AnimalVacina> listar(){
        return animalVacinaRepository.findAll();
    }

    @GetMapping("/{animalVacinaId}")
    public AnimalVacina buscar(@PathVariable Long animalVacinaId){
        return animalVacinaService.buscarOuFalhar(animalVacinaId);
    }

    @GetMapping("/animal/{animalId}")
    public List<AnimalVacina> buscarPorAnimal(@PathVariable Long animalId){
        return animalVacinaService.buscarPorAnimal(animalId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalVacina adicionar(@RequestBody AnimalVacina animalVacina){
        return animalVacinaService.adicionar(animalVacina);
    }

    @PutMapping("/{animalVacinaId}")
    public AnimalVacina atualizar(@PathVariable Long animalVacinaId, @RequestBody AnimalVacina animalVacina){

        AnimalVacina animalVacinaAtual = buscar(animalVacinaId);

        BeanUtils.copyProperties(animalVacina, animalVacinaAtual, "id");

        return animalVacinaService.salvar(animalVacinaAtual);
    }

    @DeleteMapping("/{animalVacinaId}")
    public void remover(@PathVariable Long animalVacinaId){
        animalVacinaService.excluir(animalVacinaId);
    }
}
