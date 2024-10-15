package fatec.sp.gov.br.anshinpet.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsuarioFoto {

    @Id
    @Column(name = "users_id")
    @EqualsAndHashCode.Include
    private Long id;

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    public Long getUsuarioId() {
        if (getUsuario() != null){
            return usuario.getId();
        }
        return null;
    }
}
