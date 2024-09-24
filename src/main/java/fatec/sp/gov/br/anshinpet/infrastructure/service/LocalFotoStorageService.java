package fatec.sp.gov.br.anshinpet.infrastructure.service;

import fatec.sp.gov.br.anshinpet.domain.service.FotoStorageService;
import fatec.sp.gov.br.anshinpet.infrastructure.exception.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalFotoStorageService implements FotoStorageService {

    @Value("${anshinPet.storage.local.diretorio-fotos}")
    private Path diretorioFotos;

    @Override
    public InputStream recuperar(String nomeArquivo) {
        try {
            return Files.newInputStream(getArquivoPath(nomeArquivo));
        } catch (Exception e) {
            throw new StorageException("Não foi possivel recuperar o arquivo.", e);
        }
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {
        Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());
        try {
            FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(arquivoPath));
        } catch (Exception e) {
            throw new StorageException("Não foi possível armazenar o arquivo.",e);
        }
    }

    @Override
    public void remover(String nomeArquivo) {
        Path arquivoPath = getArquivoPath(nomeArquivo);
        try {
            Files.deleteIfExists(arquivoPath);
        } catch (Exception e) {
            throw new StorageException("Não foi possível excluir o arquivo.", e);
        }
    }

    private Path getArquivoPath(String nomeArquivo){
        return diretorioFotos.resolve(nomeArquivo);
    }
}
