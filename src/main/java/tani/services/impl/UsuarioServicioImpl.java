package tani.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import tani.config.JWTUtils;
import tani.dto.otros.EmailDTO;
import tani.dto.otros.TokenDTO;
import tani.dto.usuario.InformacionUsuarioDTO;
import tani.dto.usuario.LoginDTO;
import tani.dto.usuario.RegistroUsuarioDTO;
import tani.model.entities.Usuario;
import tani.repositories.UsuarioRepo;
import tani.services.interfaces.UsuarioServicio;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tani.services.interfaces.EmailServicio;

import java.util.Map;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServicioImpl implements UsuarioServicio {

    private final JWTUtils jwtUtils;
    private final UsuarioRepo usuarioRepo;
    private final EmailServicio emailServicio;


    @Override
    public InformacionUsuarioDTO registrarUsuario(RegistroUsuarioDTO usuarioDTO) throws Exception {
        // Verificar si el correo ya está registrado
        if (usuarioRepo.findByCorreo(usuarioDTO.correo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado.");
        }

        // Convertir DTO a entidad Usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.nombre());
        usuario.setFechaNacimiento(usuarioDTO.fechaNacimiento());
        usuario.setTelefono(usuarioDTO.telefono());
        usuario.setCorreo(usuarioDTO.correo());
        usuario.setContrasenia(encriptarPassword(usuarioDTO.contrasenia()));
        usuario.setTipoUsuario(usuarioDTO.tipoUsuario());

        // Guardar el usuario en la base de datos
        Usuario usuarioCreado = usuarioRepo.save(usuario);

        // Enviar correo de bienvenida
        EmailDTO email = new EmailDTO(
                "¡Bienvenido a UniEventos " + usuarioDTO.nombre() + "!",
                "Hola " + usuarioDTO.nombre() + ", ¡Tu información personal fue guardada en nuestra base de datos! " +
                        "Te damos una cálida bienvenida a UniEventos.",
                usuarioDTO.correo()
        );
        emailServicio.enviarCorreo(email);

        // Convertir entidad a DTO y devolver
        return new InformacionUsuarioDTO(
                usuarioCreado.getId_usuario(),
                usuarioCreado.getNombre(),
                usuarioCreado.getFechaNacimiento(),
                usuarioCreado.getTelefono(),
                usuarioCreado.getCorreo(),
                usuarioCreado.getTipoUsuario()
        );
    }


    private String encriptarPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode( password );

    }

    @Override
    public TokenDTO iniciarSesion(LoginDTO usuarioDTO) {
        // Buscar el usuario por correo
        Usuario usuario = usuarioRepo.findByCorreo(usuarioDTO.email())
                .orElseThrow(() -> new RuntimeException("El correo no está registrado"));

        // Validar la contraseña con BCrypt
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(usuarioDTO.password(), usuario.getContrasenia())) {
            throw new RuntimeException("La contraseña es incorrecta");
        }

        // Construir claims para el JWT
        Map<String, Object> claims = Map.of(
                "tipoUsuario", usuario.getTipoUsuario(),
                "nombre", usuario.getNombre(),
                "idUsuario", usuario.getId_usuario()
        );

        // Generar el token con los claims
        String token = jwtUtils.generarToken(usuario.getCorreo(), claims);

        return new TokenDTO(token);
    }



    @Override
    public Optional<InformacionUsuarioDTO> buscarPorCorreo(String correo) {
        return usuarioRepo.findByCorreo(correo)
                .map(usuario -> new InformacionUsuarioDTO(
                        usuario.getId_usuario(),
                        usuario.getNombre(),
                        usuario.getFechaNacimiento(),
                        usuario.getTelefono(),
                        usuario.getCorreo(),
                        usuario.getTipoUsuario()
                ));
    }

    @Override
    public boolean existeCorreo(String correo) {
        return usuarioRepo.findByCorreo(correo).isPresent();
    }


    @Override
    public List<InformacionUsuarioDTO> listarUsuarios() {
        return usuarioRepo.findAll().stream()
                .map(usuario -> new InformacionUsuarioDTO(
                        usuario.getId_usuario(),
                        usuario.getNombre(),
                        usuario.getFechaNacimiento(),
                        usuario.getTelefono(),
                        usuario.getCorreo(),
                        usuario.getTipoUsuario()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarUsuario(Integer id) {
        usuarioRepo.deleteById(id);
    }

    @Override
    public void recuperarPassword(String correo) {

    }

    @Override
    public InformacionUsuarioDTO editarUsuario(RegistroUsuarioDTO usuarioDTO) throws Exception {
        // Buscar el usuario por correo
        Usuario usuario = usuarioRepo.findByCorreo(usuarioDTO.correo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Actualizar los campos del usuario
        usuario.setNombre(usuarioDTO.nombre());
        usuario.setFechaNacimiento(usuarioDTO.fechaNacimiento());
        usuario.setTelefono(usuarioDTO.telefono());
        usuario.setContrasenia(encriptarPassword(usuarioDTO.contrasenia()));
        usuario.setTipoUsuario(usuarioDTO.tipoUsuario());

        // Guardar los cambios en la base de datos
        Usuario usuarioActualizado = usuarioRepo.save(usuario);

        // Convertir entidad a DTO y devolver
        return new InformacionUsuarioDTO(
                usuarioActualizado.getId_usuario(),
                usuarioActualizado.getNombre(),
                usuarioActualizado.getFechaNacimiento(),
                usuarioActualizado.getTelefono(),
                usuarioActualizado.getCorreo(),
                usuarioActualizado.getTipoUsuario()
        );
    }


}

