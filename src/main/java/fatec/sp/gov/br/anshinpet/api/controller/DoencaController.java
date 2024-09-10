package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.domain.model.Doenca;
import fatec.sp.gov.br.anshinpet.domain.repository.DoencaRepository;
import fatec.sp.gov.br.anshinpet.domain.service.DoencaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doencas")
@CrossOrigin(origins = "http://localhost:5173")
public class DoencaController {

    @Autowired
    private DoencaRepository doencaRepository;

    @Autowired
    private DoencaService doencaService;

    @GetMapping
    public List<Doenca> listar(){
        return doencaRepository.findAll();
    }

    @GetMapping("/{doencaId}")
    public Doenca buscar(@PathVariable long doencaId){
        return doencaService.buscarOuFalhar(doencaId);
    }

    @PostMapping
    public Doenca adicionar(@RequestBody Doenca doenca){
        return doencaService.salvar(doenca);
    }

    @PutMapping("/{doencaId}")
    public Doenca atualizar(@PathVariable Long doencaId, @RequestBody Doenca doenca){

        Doenca doencaAtual = doencaService.buscarOuFalhar(doencaId);

        BeanUtils.copyProperties(doenca, doencaAtual, "id");

        return doencaService.salvar(doencaAtual);
    }

    @DeleteMapping("/{doencaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long doencaId){
        doencaService.excluir(doencaId);
    }
}
