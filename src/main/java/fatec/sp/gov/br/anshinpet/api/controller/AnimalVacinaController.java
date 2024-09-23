package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.api.assembler.AnimalVacinaInputDissasembler;
import fatec.sp.gov.br.anshinpet.api.assembler.AnimalVacinaModelAssembler;
import fatec.sp.gov.br.anshinpet.api.model.AnimalVacinaModel;
import fatec.sp.gov.br.anshinpet.api.model.input.AnimalVacinaInput;
import fatec.sp.gov.br.anshinpet.domain.model.AnimalVacina;
import fatec.sp.gov.br.anshinpet.domain.service.AnimalVacinaService;
import jakarta.validation.Valid;
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
    private AnimalVacinaService animalVacinaService;

    @Autowired
    private AnimalVacinaModelAssembler animalVacinaModelAssembler;

    @Autowired
    private AnimalVacinaInputDissasembler animalVacinaInputDissasembler;

    @GetMapping
    public List<AnimalVacinaModel> listar(){
        List<AnimalVacina> animalVacinas = animalVacinaService.listar();
        return animalVacinaModelAssembler.toCollectionModel(animalVacinas);
    }

    @GetMapping("/{animalVacinaId}")
    public AnimalVacinaModel buscar(@PathVariable Long animalVacinaId){
        AnimalVacina animalVacina = animalVacinaService.buscarOuFalhar(animalVacinaId);
        return animalVacinaModelAssembler.toModel(animalVacina);
    }

    @GetMapping("/animal/{animalId}")
    public List<AnimalVacinaModel> buscarPorAnimal(@PathVariable Long animalId){
        List<AnimalVacina> animalVacina = animalVacinaService.buscarPorAnimal(animalId);
        return animalVacinaModelAssembler.toCollectionModel(animalVacina);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalVacinaModel adicionar(@RequestBody @Valid AnimalVacinaInput animalVacinaInput){
        AnimalVacina animalVacina = animalVacinaInputDissasembler.toDomainObject(animalVacinaInput);
        animalVacina = animalVacinaService.adicionar(animalVacina);
        return animalVacinaModelAssembler.toModel(animalVacina);
    }

    @PutMapping("/{animalVacinaId}")
    public AnimalVacinaModel atualizar(@PathVariable Long animalVacinaId,
                                       @RequestBody @Valid AnimalVacinaInput animalVacinaInput){

        AnimalVacina animalVacinaAtual = animalVacinaService.buscarOuFalhar(animalVacinaId);
        animalVacinaInputDissasembler.copyToDomainObject(animalVacinaInput, animalVacinaAtual);
        animalVacinaAtual = animalVacinaService.salvar(animalVacinaAtual);
        return animalVacinaModelAssembler.toModel(animalVacinaAtual);
    }

    @DeleteMapping("/{animalVacinaId}")
    public void remover(@PathVariable Long animalVacinaId){
        animalVacinaService.excluir(animalVacinaId);
    }
}
