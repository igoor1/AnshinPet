package fatec.sp.gov.br.anshinpet.domain.service;

import fatec.sp.gov.br.anshinpet.domain.exception.NegocioException;
import fatec.sp.gov.br.anshinpet.domain.exception.UsuarioNaoEncontradoException;
import fatec.sp.gov.br.anshinpet.domain.model.Usuario;
import fatec.sp.gov.br.anshinpet.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioFotoService usuarioFotoService;

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void excluir(Long usuarioId) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        usuarioRepository.findFotoById(usuario.getId()).ifPresentOrElse( u -> {
            usuarioFotoService.excluir(usuario.getId());
            usuarioRepository.deleteById(usuario.getId());
            usuarioRepository.flush();
            return;
        }, () -> {
            usuarioRepository.deleteById(usuarioId);
        });
    }

    public Usuario buscarOuFalhar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }

    public BigDecimal buscarQntdUsuarios() {
        long count = usuarioRepository.count();
        return BigDecimal.valueOf(count);
    }
}
