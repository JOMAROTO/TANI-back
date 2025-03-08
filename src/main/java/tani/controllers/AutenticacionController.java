package tani.controllers;

import tani.dto.usuario.RegistroUsuarioDTO;
import tani.dto.usuario.LoginDTO;
import tani.dto.otros.TokenDTO;
import tani.services.interfaces.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AutenticacionController {
    private final UsuarioServicio usuarioServicio;

    @PostMapping("/iniciar-sesion")
    public ResponseEntity<TokenDTO> iniciarSesion(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
        TokenDTO tokenDTO = usuarioServicio.iniciarSesion(loginDTO);
        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/crear-cuenta")
    public ResponseEntity<String> crearCuenta(@Valid @RequestBody RegistroUsuarioDTO registroUsuarioDTO) throws Exception {
        usuarioServicio.registrarUsuario(registroUsuarioDTO);
        return ResponseEntity.ok("Cuenta creada exitosamente");
    }
}