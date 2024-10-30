package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.api.assembler.InteresseAdocaoModelAssembler;
import fatec.sp.gov.br.anshinpet.api.assembler.InteresseInputDisassembler;
import fatec.sp.gov.br.anshinpet.api.dto.InteresseAdocaoDTO;
import fatec.sp.gov.br.anshinpet.api.dto.input.InteresseAdocaoInput;
import fatec.sp.gov.br.anshinpet.domain.model.InteresseAdocao;
import fatec.sp.gov.br.anshinpet.domain.service.InteresseAdocaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interesses")
public class InteresseAdocaoController {

    @Autowired
    private InteresseAdocaoService interesseAdocaoService;

    @Autowired
    private InteresseAdocaoModelAssembler interesseAdocaoModelAssembler;

    @Autowired
    private InteresseInputDisassembler interesseInputDisassembler;

    @GetMapping
    public List<InteresseAdocaoDTO> listar() {
        List<InteresseAdocao> interesses = interesseAdocaoService.listar();
        return interesseAdocaoModelAssembler.toCollectionModel(interesses);
    }

    @GetMapping("/{id}")
    public InteresseAdocaoDTO buscar(@PathVariable Long id) {
        InteresseAdocao interesse = interesseAdocaoService.buscarOuFalhar(id);
        return interesseAdocaoModelAssembler.toModel(interesse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InteresseAdocaoDTO adicionar(@RequestBody InteresseAdocaoInput interesseAdocaoInput) {
        InteresseAdocao interesse = interesseInputDisassembler.toDomainObject(interesseAdocaoInput);
        interesse = interesseAdocaoService.salvar(interesse);
        return interesseAdocaoModelAssembler.toModel(interesse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        interesseAdocaoService.excluir(id);
    }
}
