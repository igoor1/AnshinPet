package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.api.assembler.AnimalModelAssembler;
import fatec.sp.gov.br.anshinpet.api.dto.AnimalDTO;
import fatec.sp.gov.br.anshinpet.domain.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/listar")
    public List<AnimalDTO> listar(){
         return animalModelAssembler.toCollectionModel(animalService.buscarAdocao());
    }
}
