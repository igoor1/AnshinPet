package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.domain.model.Animal;
import fatec.sp.gov.br.anshinpet.domain.model.Vacina;
import fatec.sp.gov.br.anshinpet.domain.repository.VacinaRepository;
import fatec.sp.gov.br.anshinpet.domain.service.VacinaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacinas")
@CrossOrigin(origins = "http://localhost:5173")
public class VacinaController {

    @Autowired
    private VacinaService vacinaService;

    @Autowired
    private VacinaRepository vacinaRepository;

    @GetMapping
    public List<Vacina> listar(){
        return vacinaRepository.findAll();
    }

    @GetMapping("/{vacinaId}")
    public Vacina buscar(@PathVariable Long vacinaId){
        return vacinaService.buscarOuFalhar(vacinaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vacina adicionar(@RequestBody @Validated Vacina vacina){
        return vacinaService.salvar(vacina);
    }

    @PutMapping("/{vacinaId}")
    public Vacina atualizar(@PathVariable Long vacinaId, @RequestBody Vacina vacina){
        Vacina vacinaAtual = vacinaService.buscarOuFalhar(vacinaId);

        BeanUtils.copyProperties(vacina, vacinaAtual, "id");

        return vacinaService.salvar(vacinaAtual);
    }

    @DeleteMapping("/{vacinaId}")
    public void remover(@PathVariable Long vacinaId){
        vacinaService.excluir(vacinaId);
    }
}
