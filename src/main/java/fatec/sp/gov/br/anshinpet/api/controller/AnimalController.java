package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.api.assembler.AnimalModelAssembler;
import fatec.sp.gov.br.anshinpet.api.assembler.AnimalInputDisassembler;
import fatec.sp.gov.br.anshinpet.api.model.AnimalModel;
import fatec.sp.gov.br.anshinpet.api.model.input.AnimalInput;
import fatec.sp.gov.br.anshinpet.domain.model.Animal;
import fatec.sp.gov.br.anshinpet.domain.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
@CrossOrigin(origins = "http://localhost:5173")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private AnimalModelAssembler animalModelAssembler;

    @Autowired
    private AnimalInputDisassembler animalInputDisassembler;

    @GetMapping
    public List<AnimalModel> listar(){
        return animalModelAssembler.toCollectionModel(animalService.listar());
    }

    @GetMapping("/{animalId}")
    public AnimalModel buscar(@PathVariable Long animalId){
        Animal animal = animalService.buscarOuFalhar(animalId);
        return animalModelAssembler.toModel(animal);
    }

    @GetMapping("/listar/{animalNome}")
    public ResponseEntity<List<AnimalModel>> buscarPorNome(@PathVariable String animalNome){
        List<Animal> animal = animalService.buscarPorNome(animalNome);
        return ResponseEntity.ok(animalModelAssembler.toCollectionModel(animal));
    }

    @PostMapping
    public AnimalModel adicionar(@RequestBody @Valid AnimalInput animalInput){
        Animal animal = animalInputDisassembler.toDomainObject(animalInput);
           return animalModelAssembler.toModel(animalService.salvar(animal));
    }

    @PutMapping("/{animalId}")
    public AnimalModel atualizar(@PathVariable Long animalId, @RequestBody @Valid AnimalInput animalInput){
        Animal animalAtual = animalService.buscarOuFalhar(animalId);
        animalInputDisassembler.copyToDomainObject(animalInput, animalAtual);
        return animalModelAssembler.toModel(animalService.salvar(animalAtual));
    }

    @DeleteMapping("/{animalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long animalId){
            animalService.excluir(animalId);
    }
}
