package fatec.sp.gov.br.anshinpet.core.storage;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import fatec.sp.gov.br.anshinpet.domain.service.FotoStorageService;
import fatec.sp.gov.br.anshinpet.infrastructure.service.LocalFotoStorageService;
import fatec.sp.gov.br.anshinpet.infrastructure.service.S3StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Autowired
    private StorageProperties storageProperties;

    @Bean
    public AmazonS3 amazonS3(){
        var credentials = new BasicAWSCredentials(
                storageProperties.getS3().getPublickey(),
                storageProperties.getS3().getPrivatekey()
        );
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(storageProperties.getS3().getRegiao())
                .build();
    }

    @Bean
    public FotoStorageService fotoStorageService(){
        if (StorageProperties.TipoStorage.S3.equals(storageProperties.getTipo())){
            return new S3StorageService();
        } else {
            return new LocalFotoStorageService();
        }
    }
}
