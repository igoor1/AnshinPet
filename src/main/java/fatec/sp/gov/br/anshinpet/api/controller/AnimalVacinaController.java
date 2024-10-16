package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.api.assembler.AnimalVacinaInputDissasembler;
import fatec.sp.gov.br.anshinpet.api.assembler.AnimalVacinaModelAssembler;
import fatec.sp.gov.br.anshinpet.api.dto.AnimalVacinaDTO;
import fatec.sp.gov.br.anshinpet.api.dto.input.AnimalVacinaInput;
import fatec.sp.gov.br.anshinpet.domain.model.AnimalVacina;
import fatec.sp.gov.br.anshinpet.domain.service.AnimalVacinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animal-vacinas")
public class AnimalVacinaController {

    @Autowired
    private AnimalVacinaService animalVacinaService;

    @Autowired
    private AnimalVacinaModelAssembler animalVacinaModelAssembler;

    @Autowired
    private AnimalVacinaInputDissasembler animalVacinaInputDissasembler;

    @GetMapping
    public List<AnimalVacinaDTO> listar(){
        List<AnimalVacina> animalVacinas = animalVacinaService.listar();
        return animalVacinaModelAssembler.toCollectionModel(animalVacinas);
    }

    @GetMapping("/{animalVacinaId}")
    public AnimalVacinaDTO buscar(@PathVariable Long animalVacinaId){
        AnimalVacina animalVacina = animalVacinaService.buscarOuFalhar(animalVacinaId);
        return animalVacinaModelAssembler.toModel(animalVacina);
    }

    @GetMapping("/animal/{animalId}")
    public List<AnimalVacinaDTO> buscarPorAnimal(@PathVariable Long animalId){
        List<AnimalVacina> animalVacina = animalVacinaService.buscarPorAnimal(animalId);
        return animalVacinaModelAssembler.toCollectionModel(animalVacina);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalVacinaDTO adicionar(@RequestBody @Valid AnimalVacinaInput animalVacinaInput){
        AnimalVacina animalVacina = animalVacinaInputDissasembler.toDomainObject(animalVacinaInput);
        animalVacina = animalVacinaService.adicionar(animalVacina);
        return animalVacinaModelAssembler.toModel(animalVacina);
    }

    @PutMapping("/{animalVacinaId}")
    public AnimalVacinaDTO atualizar(@PathVariable Long animalVacinaId,
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
