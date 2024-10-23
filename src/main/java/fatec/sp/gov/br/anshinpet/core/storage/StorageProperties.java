package fatec.sp.gov.br.anshinpet.core.storage;

import com.amazonaws.regions.Regions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Getter
@Setter
@Component
@ConfigurationProperties("anshinpet.storage")
public class StorageProperties {

    private Local local = new Local();
    private S3 s3 = new S3();
    private TipoStorage tipo = TipoStorage.LOCAL;

    public enum TipoStorage{
        LOCAL,S3
    }

    @Getter
    @Setter
    public class Local{
        private Path diretorioFotos;
    }

    @Getter
    @Setter
    public class S3{

        private String publickey;
        private String privatekey;
        private String bucket;
        private String diretorio;
        private Regions regiao;
    }
}
