package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.api.assembler.DoacaoInputDisassembler;
import fatec.sp.gov.br.anshinpet.api.assembler.DoacaoModelAssembler;
import fatec.sp.gov.br.anshinpet.api.dto.DoacaoDTO;
import fatec.sp.gov.br.anshinpet.api.dto.input.DoacaoInput;
import fatec.sp.gov.br.anshinpet.domain.model.Doacao;
import fatec.sp.gov.br.anshinpet.domain.service.DoacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doacoes")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    @Autowired
    private DoacaoInputDisassembler doacaoInputDisassembler;

    @Autowired
    private DoacaoModelAssembler doacaoModelAssembler;

    @GetMapping
    public List<DoacaoDTO> listar(){
        List<Doacao> doacoes = doacaoService.listar();
        return doacaoModelAssembler.toCollectionModel(doacoes);
    }

    @GetMapping("/{doacaoId}")
    public DoacaoDTO buscar(@PathVariable Long doacaoId){
        Doacao doacao = doacaoService.buscarOuFalhar(doacaoId);
        return doacaoModelAssembler.toModel(doacao);
    }

    @GetMapping("/listar/{tipoDoacao}")
    public ResponseEntity<List<DoacaoDTO>> buscvarPOrTipo(@PathVariable String tipoDoacao){
        List<Doacao> doacao = doacaoService.buscarPortipo(tipoDoacao);
        return ResponseEntity.ok(doacaoModelAssembler.toCollectionModel(doacao));
    }

    @PostMapping
    public DoacaoDTO adicionar(@RequestBody @Valid DoacaoInput doacaoInput){
        Doacao doacao = doacaoInputDisassembler.toDomainObject(doacaoInput);
        return doacaoModelAssembler.toModel(doacaoService.salvar(doacao));
    }

    @PutMapping("/{doacaoId}")
    public DoacaoDTO atualizar(@PathVariable Long doacaoId, @RequestBody @Valid DoacaoInput doacaoInput){
        Doacao doacaoAtual = doacaoService.buscarOuFalhar(doacaoId);
        doacaoInputDisassembler.copyToDomainObject(doacaoInput, doacaoAtual);
        return doacaoModelAssembler.toModel(doacaoService.salvar(doacaoAtual));
    }

    @DeleteMapping("/{doacaoId}")
    @ResponseStatus(HttpStatus.OK)
    public void remover(@PathVariable Long doacaoId){
        doacaoService.excluir(doacaoId);
    }
}
