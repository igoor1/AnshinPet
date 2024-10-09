package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.api.assembler.DoacaoInputDisassembler;
import fatec.sp.gov.br.anshinpet.api.assembler.DoacaoModelAssembler;
import fatec.sp.gov.br.anshinpet.api.model.DoacaoModel;
import fatec.sp.gov.br.anshinpet.api.model.input.DoacaoInput;
import fatec.sp.gov.br.anshinpet.domain.model.Doacao;
import fatec.sp.gov.br.anshinpet.domain.service.DoacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doacoes")
@CrossOrigin(origins = "http://localhost:5173")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    @Autowired
    private DoacaoInputDisassembler doacaoInputDisassembler;

    @Autowired
    private DoacaoModelAssembler doacaoModelAssembler;

    @GetMapping
    public List<DoacaoModel> listar(){
        List<Doacao> doacoes = doacaoService.listar();
        return doacaoModelAssembler.toCollectionModel(doacoes);
    }

    @GetMapping("/{doacaoId}")
    public DoacaoModel buscar(@PathVariable Long doacaoId){
        Doacao doacao = doacaoService.buscarOuFalhar(doacaoId);
        return doacaoModelAssembler.toModel(doacao);
    }

    @GetMapping("/listar/{tipoDoacao}")
    public ResponseEntity<List<DoacaoModel>> buscvarPOrTipo(@PathVariable String tipoDoacao){
        List<Doacao> doacao = doacaoService.buscarPortipo(tipoDoacao);
        return ResponseEntity.ok(doacaoModelAssembler.toCollectionModel(doacao));
    }

    @PostMapping
    public DoacaoModel adicionar(@RequestBody @Valid DoacaoInput doacaoInput){
        Doacao doacao = doacaoInputDisassembler.toDomainObject(doacaoInput);
        return doacaoModelAssembler.toModel(doacaoService.salvar(doacao));
    }

    @PutMapping("/{doacaoId}")
    public DoacaoModel atualizar(@PathVariable Long doacaoId, @RequestBody @Valid DoacaoInput doacaoInput){
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
