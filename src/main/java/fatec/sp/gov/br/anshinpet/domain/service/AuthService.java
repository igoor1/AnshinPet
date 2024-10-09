package fatec.sp.gov.br.anshinpet.domain.service;

import fatec.sp.gov.br.anshinpet.api.model.input.UsuarioInput;
import fatec.sp.gov.br.anshinpet.domain.model.Usuario;
import fatec.sp.gov.br.anshinpet.domain.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username);
    }

    @Transactional
    public Usuario cadastrar( UsuarioInput usuarioInput){
        if(emailExistente(usuarioInput.getEmail())){
            throw new IllegalArgumentException("Este email já esta cadastrado!");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioInput.getSenha());

        Usuario newUsuario = new Usuario();
        newUsuario.setNome(usuarioInput.getNome());
        newUsuario.setCelular(usuarioInput.getCelular());
        newUsuario.setCpf(usuarioInput.getCpf());
        newUsuario.setSexo(usuarioInput.getSexo());
        newUsuario.setEmail(usuarioInput.getEmail());
        newUsuario.setPassword(encryptedPassword);
        newUsuario.setRole(usuarioInput.getRole());

        return usuarioRepository.save(newUsuario);
    }

    public boolean emailExistente(String email) {
        return usuarioRepository.findUsuarioByEmail(email).isPresent();
    }

}
