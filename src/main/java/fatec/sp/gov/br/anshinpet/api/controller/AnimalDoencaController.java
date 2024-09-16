package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.domain.model.AnimalDoenca;
import fatec.sp.gov.br.anshinpet.domain.service.AnimalDoencaService;
import org.springframework.beans.BeanUtils;
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

    @GetMapping
    public List<AnimalDoenca> listar(){
        return animalDoencaService.listar();
    }

    @GetMapping("/{animalDoencaId}")
    public AnimalDoenca buscar(@PathVariable long animalDoencaId){
        return animalDoencaService.buscarOuFalhar(animalDoencaId);
    }

    @GetMapping("/animal/{animalId}")
    public List<AnimalDoenca> buscarPorAnimal(@PathVariable Long animalId){
        return animalDoencaService.buscarPorAnimal(animalId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalDoenca adicionar(@RequestBody AnimalDoenca animalDoenca){
        return animalDoencaService.adicionar(animalDoenca);
    }

    @PutMapping("/{animalDoencaId}")
    public AnimalDoenca atualizar(@PathVariable Long animalDoencaId, @RequestBody AnimalDoenca animalDoenca){

        AnimalDoenca animalDoencalAtual = buscar(animalDoencaId);

        BeanUtils.copyProperties(animalDoenca, animalDoencalAtual, "id");

        return animalDoencaService.salvar(animalDoencalAtual);
    }

    @DeleteMapping("/{animalDoencaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long animalDoencaId){
        animalDoencaService.excluir(animalDoencaId);
    }
}
