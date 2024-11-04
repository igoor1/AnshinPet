package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.api.assembler.AnimalModelAssembler;
import fatec.sp.gov.br.anshinpet.api.assembler.PageModelAssembler;
import fatec.sp.gov.br.anshinpet.api.dto.AnimalDTO;
import fatec.sp.gov.br.anshinpet.api.dto.PageDTO;
import fatec.sp.gov.br.anshinpet.domain.model.Animal;
import fatec.sp.gov.br.anshinpet.domain.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/disponiveis")
public class AdocaoController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private AnimalModelAssembler animalModelAssembler;

    @Autowired
    private PageModelAssembler pageModelAssembler;

    @GetMapping("/listar")
    public ResponseEntity<PageDTO> listar(@PageableDefault(size = 12) Pageable pageable){
        Page<Animal> animalPage = animalService.buscarAdocao(pageable);
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
}
