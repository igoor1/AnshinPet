package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.api.assembler.AnimalModelAssembler;
import fatec.sp.gov.br.anshinpet.api.assembler.AnimalInputDisassembler;
import fatec.sp.gov.br.anshinpet.api.assembler.PageModelAssembler;
import fatec.sp.gov.br.anshinpet.api.dto.AnimalDTO;
import fatec.sp.gov.br.anshinpet.api.dto.PageDTO;
import fatec.sp.gov.br.anshinpet.api.dto.input.AnimalInput;
import fatec.sp.gov.br.anshinpet.domain.model.Animal;
import fatec.sp.gov.br.anshinpet.domain.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private AnimalModelAssembler animalModelAssembler;

    @Autowired
    private AnimalInputDisassembler animalInputDisassembler;

    @Autowired
    private PageModelAssembler pageModelAssembler;

    @GetMapping
    public ResponseEntity<PageDTO> listar(@PageableDefault(size = 12) Pageable pageable){
        Page<Animal> animalPage = animalService.listar(pageable);
        List<AnimalDTO>  animalDTO = animalModelAssembler.toCollectionModel(animalPage.getContent());
        Page<AnimalDTO> animalDTOPage = new PageImpl<>(animalDTO, pageable, animalPage.getTotalElements());
        PageDTO pageDTO = pageModelAssembler.toModel(animalDTOPage);
        return ResponseEntity.ok(pageDTO);
    }

    @GetMapping("/{animalId}")
    public AnimalDTO buscar(@PathVariable Long animalId){
        Animal animal = animalService.buscarOuFalhar(animalId);
        return animalModelAssembler.toModel(animal);
    }

    @GetMapping("/listar/{animalNome}")
    public ResponseEntity<List<AnimalDTO>> buscarPorNome(@PathVariable String animalNome){
        List<Animal> animal = animalService.buscarPorNome(animalNome);
        return ResponseEntity.ok(animalModelAssembler.toCollectionModel(animal));
    }

    @GetMapping("/quantidade")
    public BigDecimal buscarQuantidadeAnimais() {
        return animalService.buscarQntdAnimais();
    }

    @GetMapping("/quantidade/{tipo}")
    public BigDecimal buscarQuantidadePorTipo(@PathVariable String tipo){
        return animalService.buscarQntdPorTipo(tipo);
    }

    @GetMapping("/quantidade/disponiveis")
    public BigDecimal buscarQntdDisponivel(){
        return animalService.qntdAnimaisDisponivel();
    }

    @GetMapping("/quantidade/nao-disponiveis")
    public BigDecimal buscarQntdNaoDisponivel(){
        return animalService.qntdAnimaisNaoDisponivel();
    }

    @PostMapping
    public AnimalDTO adicionar(@RequestBody @Valid AnimalInput animalInput){
        Animal animal = animalInputDisassembler.toDomainObject(animalInput);
           return animalModelAssembler.toModel(animalService.salvar(animal));
    }

    @PutMapping("/{animalId}")
    public AnimalDTO atualizar(@PathVariable Long animalId, @RequestBody @Valid AnimalInput animalInput){
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
