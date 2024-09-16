package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.domain.model.Animal;
import fatec.sp.gov.br.anshinpet.domain.service.AnimalService;
import org.springframework.beans.BeanUtils;
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

    @GetMapping
    public List<Animal> listar(){
        return animalService.listar();
    }

    @GetMapping("/{animalId}")
    public Animal buscar(@PathVariable Long animalId){
        return animalService.buscarOuFalhar(animalId);
    }

    @GetMapping("/listar/{animalNome}")
    public ResponseEntity<List<Animal>> buscarPorNome(@PathVariable String animalNome){
        return ResponseEntity.ok(animalService.buscarPorNome(animalNome));
    }

    @PostMapping
    public Animal adicionar(@RequestBody Animal animal){
           return animalService.salvar(animal);
    }

    @PutMapping("/{animalId}")
    public Animal atualizar(@PathVariable Long animalId,@RequestBody Animal animal){

        Animal animalAtual = animalService.buscarOuFalhar(animalId);

        BeanUtils.copyProperties(animal, animalAtual,"id");

        return animalService.salvar(animalAtual);
    }

    @DeleteMapping("/{animalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long animalId){
            animalService.excluir(animalId);
    }
}
