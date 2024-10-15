package fatec.sp.gov.br.anshinpet.domain.service;

import fatec.sp.gov.br.anshinpet.domain.exception.FotoNaoEncontradaException;
import fatec.sp.gov.br.anshinpet.domain.model.UsuarioFoto;
import fatec.sp.gov.br.anshinpet.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
public class UsuarioFotoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FotoStorageService fotoStorage;

    @Transactional
    public UsuarioFoto salvar(UsuarioFoto foto, InputStream dadosArquivo){
        String nomeNovoArquivo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());
        Long usuarioId = foto.getUsuarioId();
        String nomeArquivoExistente = null;

        Optional<UsuarioFoto> fotoExistente = usuarioRepository.findFotoById(usuarioId);

        if (fotoExistente.isPresent()){
            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
            usuarioRepository.delete(fotoExistente.get());
        }

        foto.setNomeArquivo(nomeNovoArquivo);
        foto = usuarioRepository.save(foto);
        usuarioRepository.flush();

        var novaFoto = FotoStorageService.NovaFoto.builder()
                .nomeArquivo(foto.getNomeArquivo())
                .inputStream(dadosArquivo)
                .build();

        fotoStorage.substituir(nomeArquivoExistente, novaFoto);
        return foto;
    }

    public UsuarioFoto buscarOuFalhar(Long usuarioId){
        return usuarioRepository.findFotoById(usuarioId)
                .orElseThrow(() -> new FotoNaoEncontradaException(usuarioId));
    }

    public InputStream pegar(String nome){
       return fotoStorage.recuperar(nome);
    }

    public void excluir(Long usuarioId){
        UsuarioFoto foto = buscarOuFalhar(usuarioId);
        usuarioRepository.delete(foto);
        usuarioRepository.flush();
        fotoStorage.remover(foto.getNomeArquivo());
    }
}
