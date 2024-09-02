package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.domain.model.Animal;
import fatec.sp.gov.br.anshinpet.domain.repository.AnimalRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping
    public List<Animal> listar(){
        return animalRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Animal> adicionar(@RequestBody Animal animal){
           Animal newAnimal = animalRepository.save(animal);
           return ResponseEntity.status(HttpStatus.CREATED).body(newAnimal);
    }

    @PutMapping("/{animalId}")
    public ResponseEntity<?> atualizar(@PathVariable Long animalId,@RequestBody Animal animal){
        try{
            Animal animalAtual = animalRepository.findById(animalId)
                    .orElse(null);

            if (animalAtual != null){
                BeanUtils.copyProperties(animal, animalAtual,"id");

                animalAtual = animalRepository.save(animalAtual);
                return ResponseEntity.ok(animalAtual);
            }

            return ResponseEntity.notFound().build();

        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{animalId}")
    public ResponseEntity<Animal> deletar(@PathVariable Long animalId){
            animalRepository.deleteById(animalId);
            return ResponseEntity.noContent().build();
    }

}
