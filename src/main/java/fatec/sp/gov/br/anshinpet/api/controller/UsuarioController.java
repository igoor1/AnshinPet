package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.api.assembler.UsuarioInputDisassembler;
import fatec.sp.gov.br.anshinpet.api.assembler.UsuarioModelAssembler;
import fatec.sp.gov.br.anshinpet.api.dto.UsuarioDTO;
import fatec.sp.gov.br.anshinpet.api.dto.input.UsuarioInput;
import fatec.sp.gov.br.anshinpet.domain.model.Usuario;
import fatec.sp.gov.br.anshinpet.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;

    @GetMapping
    public List<UsuarioDTO> listar(){
        List<Usuario> usuarios = usuarioService.listar();
        return usuarioModelAssembler.toCollectionModel(usuarios);
    }

    @GetMapping("/{usuarioId}")
    public UsuarioDTO buscar(@PathVariable Long usuarioId){
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
        return usuarioModelAssembler.toModel(usuario);
    }

    @GetMapping("/quantidade")
    public BigDecimal getQuantidadeAnimais() {
        return usuarioService.buscarQntdUsuarios();
    }

    @PutMapping("/{usuarioId}")
    public UsuarioDTO atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInput usuarioInput){
        Usuario usuarioAtual = usuarioService.buscarOuFalhar(usuarioId);
        usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
        return usuarioModelAssembler.toModel(usuarioService.salvar(usuarioAtual));
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long usuarioId){
        usuarioService.excluir(usuarioId);
    }
}
