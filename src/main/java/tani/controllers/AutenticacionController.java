package tani.controllers;

import tani.dto.usuario.RegistroUsuarioDTO;
import tani.dto.usuario.LoginDTO;
import tani.dto.otros.TokenDTO;
import tani.services.interfaces.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Map<String, String>> crearCuenta(@Valid @RequestBody RegistroUsuarioDTO registroUsuarioDTO) throws Exception {
        usuarioServicio.registrarUsuario(registroUsuarioDTO);

        // Crear una respuesta en formato JSON
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Cuenta creada exitosamente");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/recuperar-contrasenia")
    public ResponseEntity<Map<String, String>> recuperarContrasenia(@Valid @RequestBody String correo) throws Exception {
        usuarioServicio.recuperarPassword(correo);

        // Crear una respuesta en formato JSON
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Correo enviado exitosamente");

        return ResponseEntity.ok(response);
    }
    @PutMapping("/cambiar-contrasenia/{correo}")
    public ResponseEntity<Map<String, String>> cambiarContrasenia(@PathVariable String correo, @RequestBody String contrasenia) throws Exception {
        usuarioServicio.cambiarContrasenia(correo, contrasenia);

        // Crear una respuesta en formato JSON
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Contraseña cambiada exitosamente");

        return ResponseEntity.ok(response);
    }


}