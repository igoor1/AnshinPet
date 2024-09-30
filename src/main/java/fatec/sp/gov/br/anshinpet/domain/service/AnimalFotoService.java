package fatec.sp.gov.br.anshinpet.domain.service;

import fatec.sp.gov.br.anshinpet.domain.exception.FotoNaoEncontradaException;
import fatec.sp.gov.br.anshinpet.domain.model.AnimalFoto;
import fatec.sp.gov.br.anshinpet.domain.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
public class AnimalFotoService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private FotoStorageService fotoStorage;

    @Transactional
    public AnimalFoto salvar(AnimalFoto foto, InputStream dadosArquivo){
        String nomeNovoArquivo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());
        Long animalId = foto.GetAnimalId();
        String nomeArquivoExistente = null;

        Optional<AnimalFoto> fotoExistente = animalRepository.findFotoById(animalId);

        if(fotoExistente.isPresent()){
            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
            animalRepository.delete(fotoExistente.get());
        }

        foto.setNomeArquivo(nomeNovoArquivo);
        foto = animalRepository.save(foto);
        animalRepository.flush();

        var novaFoto = FotoStorageService.NovaFoto.builder()
                .nomeArquivo(foto.getNomeArquivo())
                .inputStream(dadosArquivo)
                .build();

        fotoStorage.substituir(nomeArquivoExistente, novaFoto);
        return  foto;
    }

    public AnimalFoto buscarOuFalhar(Long animalId) {
        return animalRepository.findFotoById(animalId)
                .orElseThrow(() -> new FotoNaoEncontradaException(animalId));
    }

    @Transactional
    public void excluir(Long animalId){
        AnimalFoto foto = buscarOuFalhar(animalId);
        animalRepository.delete(foto);
        animalRepository.flush();
        fotoStorage.remover(foto.getNomeArquivo());
    }
}
