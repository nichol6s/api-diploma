package br.com.fiap.api_diploma.controller;

import br.com.fiap.api_diploma.dto.AuthDTO;
import br.com.fiap.api_diploma.dto.LoginResponseDTO;
import br.com.fiap.api_diploma.dto.RegisterDTO;
import br.com.fiap.api_diploma.model.Usuario;
import br.com.fiap.api_diploma.repository.UsuarioRepository;
import br.com.fiap.api_diploma.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO authDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                authDTO.login(),
                authDTO.senha()
        );
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO) {
        if (usuarioRepository.findByLogin(registerDTO.login()) != null) {
            return ResponseEntity.badRequest().body("Usuário já existe");
        }

        String encryptedPassword = new BCryptPasswordEncoder()
                .encode(registerDTO.senha());

        Usuario usuario = new Usuario(
                registerDTO.login(),
                encryptedPassword,
                registerDTO.role()
        );

        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }
}