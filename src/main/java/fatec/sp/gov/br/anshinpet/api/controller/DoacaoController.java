package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.api.assembler.DoacaoInputDisassembler;
import fatec.sp.gov.br.anshinpet.api.assembler.DoacaoModelAssembler;
import fatec.sp.gov.br.anshinpet.api.model.DoacaoModel;
import fatec.sp.gov.br.anshinpet.domain.model.Doacao;
import fatec.sp.gov.br.anshinpet.domain.service.DoacaoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/listar/{tipoDoacao}")
    public ResponseEntity<List<DoacaoModel>> buscvarPOrTipo(@PathVariable String tipoDoacao){
        List<Doacao> doacao = doacaoService.buscarPortipo(tipoDoacao);
        return ResponseEntity.ok(doacaoModelAssembler.toCollectionModel(doacao));
    }
}
