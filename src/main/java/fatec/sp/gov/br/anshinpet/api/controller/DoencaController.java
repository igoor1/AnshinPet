package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.api.assembler.DoencaInputDisassembler;
import fatec.sp.gov.br.anshinpet.api.assembler.DoencaModelAssembler;
import fatec.sp.gov.br.anshinpet.api.dto.DoencaDTO;
import fatec.sp.gov.br.anshinpet.api.dto.input.DoencaInput;
import fatec.sp.gov.br.anshinpet.domain.model.Doenca;
import fatec.sp.gov.br.anshinpet.domain.service.DoencaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doencas")
public class DoencaController {

    @Autowired
    private DoencaService doencaService;

    @Autowired
    private DoencaModelAssembler doencaModelAssembler;

    @Autowired
    private DoencaInputDisassembler doencaInputDisassembler;

    @GetMapping
    public List<DoencaDTO> listar(){
        List<Doenca> doencas = doencaService.listar();
        return doencaModelAssembler.toCollectionModel(doencas);
    }

    @GetMapping("/{doencaId}")
    public DoencaDTO buscar(@PathVariable Long doencaId){
        Doenca doenca = doencaService.buscarOuFalhar(doencaId);
        return doencaModelAssembler.toModel(doenca);
    }

    @PostMapping
    public DoencaDTO adicionar(@RequestBody @Valid DoencaInput doencaInput){
        Doenca doenca = doencaInputDisassembler.toDomainObject(doencaInput);
        doenca = doencaService.salvar(doenca);
        return doencaModelAssembler.toModel(doenca);
    }

    @PutMapping("/{doencaId}")
    public DoencaDTO atualizar(@PathVariable Long doencaId, @RequestBody @Valid DoencaInput doencaInput){
        Doenca doencaAtual = doencaService.buscarOuFalhar(doencaId);
        doencaInputDisassembler.copyToDomainObject(doencaInput, doencaAtual);
        doencaAtual = doencaService.salvar(doencaAtual);
        return doencaModelAssembler.toModel(doencaAtual);
    }

    @DeleteMapping("/{doencaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long doencaId){
        doencaService.excluir(doencaId);
    }
}
