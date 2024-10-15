package fatec.sp.gov.br.anshinpet.domain.repository;

import fatec.sp.gov.br.anshinpet.domain.model.Usuario;
import fatec.sp.gov.br.anshinpet.domain.model.UsuarioFoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQueries {

    UserDetails findByEmail(String email);
    Optional<Usuario> findUsuarioByEmail(String email);

    @Query("From UsuarioFoto u where u.usuario.id = :usuarioId")
    Optional<UsuarioFoto> findFotoById(Long usuarioId);
}
