package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.api.assembler.AnimalDoencaInputDisassembler;
import fatec.sp.gov.br.anshinpet.api.assembler.AnimalDoencaModelAssembler;
import fatec.sp.gov.br.anshinpet.api.model.AnimalDoencaModel;
import fatec.sp.gov.br.anshinpet.api.model.input.AnimalDoencaInput;
import fatec.sp.gov.br.anshinpet.domain.model.AnimalDoenca;
import fatec.sp.gov.br.anshinpet.domain.service.AnimalDoencaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animal-doencas")
@CrossOrigin(origins = "http://localhost:5173")
public class AnimalDoencaController {

    @Autowired
    private AnimalDoencaService animalDoencaService;

    @Autowired
    private AnimalDoencaModelAssembler animalDoencaModelAssembler;

    @Autowired
    private AnimalDoencaInputDisassembler animalDoencaInputDisassembler;

    @GetMapping
    public List<AnimalDoencaModel> listar(){
        List<AnimalDoenca> animalDoencas = animalDoencaService.listar();
        return animalDoencaModelAssembler.toCollectionModel(animalDoencas);
    }

    @GetMapping("/{animalDoencaId}")
    public AnimalDoencaModel buscar(@PathVariable long animalDoencaId){
        AnimalDoenca animalDoenca = animalDoencaService.buscarOuFalhar(animalDoencaId);
        return animalDoencaModelAssembler.toModel(animalDoenca);
    }

    @GetMapping("/animal/{animalId}")
    public List<AnimalDoencaModel> buscarPorAnimal(@PathVariable Long animalId){
        List<AnimalDoenca> animalDoenca = animalDoencaService.buscarPorAnimal(animalId);
        return animalDoencaModelAssembler.toCollectionModel(animalDoenca);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalDoencaModel adicionar(@RequestBody @Valid AnimalDoencaInput animalDoencaInput){
        AnimalDoenca animalDoenca = animalDoencaInputDisassembler.toDomainObject(animalDoencaInput);
        animalDoenca = animalDoencaService.adicionar(animalDoenca);
        return animalDoencaModelAssembler.toModel(animalDoenca);
    }

    @PutMapping("/{animalDoencaId}")
    public AnimalDoencaModel atualizar(@PathVariable Long animalDoencaId,
                                       @RequestBody @Valid AnimalDoencaInput animalDoencaInput){

        AnimalDoenca animalDoencaAtualizada = animalDoencaInputDisassembler.toDomainObject(animalDoencaInput);
        animalDoencaAtualizada = animalDoencaService.atualizar(animalDoencaId, animalDoencaAtualizada);
        return animalDoencaModelAssembler.toModel(animalDoencaAtualizada);
    }

    @DeleteMapping("/{animalDoencaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long animalDoencaId){
        animalDoencaService.excluir(animalDoencaId);
    }
}
