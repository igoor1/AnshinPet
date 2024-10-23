package fatec.sp.gov.br.anshinpet.infrastructure.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import fatec.sp.gov.br.anshinpet.core.storage.StorageProperties;
import fatec.sp.gov.br.anshinpet.domain.service.FotoStorageService;
import fatec.sp.gov.br.anshinpet.infrastructure.exception.StorageException;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

public class S3StorageService implements FotoStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public FotoRecuperada recuperar(String nomeArquivo) {
        String path = getPath(nomeArquivo);
        URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), path);
        return FotoRecuperada.builder().url(url.toString()).build();
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {
        try {
            String path = getPath(novaFoto.getNomeArquivo());
            var objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(novaFoto.getContentType());
            objectMetadata.setContentLength(novaFoto.getInputStream().available());

            var putObjectRequest = new PutObjectRequest(
                    storageProperties.getS3().getBucket(),
                    path,
                    novaFoto.getInputStream(),
                    objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(putObjectRequest);
        } catch (Exception e){
            throw new StorageException("Não cadastrado", e);
        }
    }

    @Override
    public void remover(String nomeArquivo) {
        String path = getPath(nomeArquivo);
        var deleteObjectRequest = new DeleteObjectRequest(storageProperties.getS3().getBucket(), path);
        amazonS3.deleteObject(deleteObjectRequest);
    }

    private String getPath(String nomeArquivo){
        return String.format("%s/%s", storageProperties.getS3().getDiretorio(), nomeArquivo);
    }
}
