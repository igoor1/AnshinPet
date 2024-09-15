package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.domain.model.AnimalDoenca;
import fatec.sp.gov.br.anshinpet.domain.model.Doenca;
import fatec.sp.gov.br.anshinpet.domain.model.Animal;
import fatec.sp.gov.br.anshinpet.domain.repository.AnimalDoencaRepository;
import fatec.sp.gov.br.anshinpet.domain.service.AnimalDoencaService;
import fatec.sp.gov.br.anshinpet.domain.service.AnimalService;
import fatec.sp.gov.br.anshinpet.domain.service.DoencaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animalDoenca")
@CrossOrigin(origins = "http://localhost:5173")
public class AnimalDoencaController {
    @Autowired
    private AnimalDoencaRepository animalDoencaRepository;

    @Autowired
    private AnimalDoencaService animalDoencaService;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private DoencaService doencaService;

    @GetMapping
    public List<AnimalDoenca> listar(){
        return animalDoencaRepository.findAll();
    }

    @GetMapping("/{animalDoencaId}")
    public AnimalDoenca buscar(@PathVariable long animalDoencaId){
        return animalDoencaService.buscarOuFalhar(animalDoencaId);
    }

    @GetMapping("/animal/{animalId}")
    public List<AnimalDoenca> buscarPorAnimal(@PathVariable Long animalId){
        return animalDoencaService.buscarPorAnimal(animalId);
    }

    @PostMapping()
    public AnimalDoenca adicionar(@RequestParam Long animalId, @RequestParam Long doencaId, @RequestParam String status, @RequestParam String descricao){
        Doenca doencaAtual = doencaService.buscarOuFalhar(doencaId);
        Animal animalAtual = animalService.buscarOuFalhar(animalId);

        AnimalDoenca animalDoenca = new AnimalDoenca(animalAtual, doencaAtual, status, descricao);

        return animalDoencaService.salvar(animalDoenca);
    }

    @PutMapping("/{animalDoencaId}")
    public AnimalDoenca atualizar(@PathVariable Long animalDoencaId, @RequestParam Long animalId, @RequestParam Long doencaId, @RequestParam String status, @RequestParam String descricao){
        AnimalDoenca animalDoencalAtual = animalDoencaService.buscarOuFalhar(animalDoencaId);

        Animal novoAnimal = animalService.buscarOuFalhar(animalId);
        Doenca novaDoenca = doencaService.buscarOuFalhar(doencaId);

        animalDoencalAtual.setAnimal(novoAnimal);
        animalDoencalAtual.setDoenca(novaDoenca);

        animalDoencalAtual.setStatus(status);
        animalDoencalAtual.setDescricao(descricao);

        return animalDoencaService.salvar(animalDoencalAtual);
    }

    @DeleteMapping("/{animalDoencaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long animalDoencaId){
        animalDoencaService.excluir(animalDoencaId);
    }
}
