package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.api.assembler.VacinaInputDisassembler;
import fatec.sp.gov.br.anshinpet.api.assembler.VacinaModelAssembler;
import fatec.sp.gov.br.anshinpet.api.dto.VacinaDTO;
import fatec.sp.gov.br.anshinpet.api.dto.input.VacinaInput;
import fatec.sp.gov.br.anshinpet.domain.model.Vacina;
import fatec.sp.gov.br.anshinpet.domain.service.VacinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vacinas")
public class VacinaController {

    @Autowired
    private VacinaService vacinaService;

    @Autowired
    private VacinaModelAssembler vacinaModelAssembler;

    @Autowired
    private VacinaInputDisassembler vacinaInputDisassembler;

    @GetMapping
    public List<VacinaDTO> listar(){
        List<Vacina> vacinas = vacinaService.listar();
        return vacinaModelAssembler.toCollectionModel(vacinas);
    }

    @GetMapping("/{vacinaId}")
    public VacinaDTO buscar(@PathVariable Long vacinaId){
        Vacina vacina = vacinaService.buscarOuFalhar(vacinaId);
        return vacinaModelAssembler.toModel(vacina);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VacinaDTO adicionar(@RequestBody @Valid VacinaInput vacinaInput){
        Vacina vacina = vacinaInputDisassembler.toDomainObject(vacinaInput);
        vacina = vacinaService.salvar(vacina);
        return vacinaModelAssembler.toModel(vacina);
    }

    @PutMapping("/{vacinaId}")
    public VacinaDTO atualizar(@PathVariable Long vacinaId, @RequestBody @Valid VacinaInput vacinaInput){

        Vacina vacinaAtual = vacinaService.buscarOuFalhar(vacinaId);
        vacinaInputDisassembler.copyToDomainObject(vacinaInput, vacinaAtual);
        vacinaAtual = vacinaService.salvar(vacinaAtual);
        return vacinaModelAssembler.toModel(vacinaAtual);
    }

    @DeleteMapping("/{vacinaId}")
    public void remover(@PathVariable Long vacinaId){
        vacinaService.excluir(vacinaId);
    }
}
