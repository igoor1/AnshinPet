package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.api.dto.LoginDTO;
import fatec.sp.gov.br.anshinpet.api.dto.input.LoginInput;
import fatec.sp.gov.br.anshinpet.api.dto.input.UsuarioInput;
import fatec.sp.gov.br.anshinpet.domain.model.Usuario;
import fatec.sp.gov.br.anshinpet.domain.service.AuthService;
import fatec.sp.gov.br.anshinpet.domain.service.TokenService;
import jakarta.validation.Valid;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginInput loginInput){
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginInput.getEmail(), loginInput.getSenha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UsuarioInput usuarioInput) throws AuthenticationException {
        authService.cadastrar(usuarioInput);
        return ResponseEntity.ok().build();
    }
}
