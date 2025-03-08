package tani.services.interfaces;

import tani.dto.usuario.RegistroUsuarioDTO;
import tani.model.entities.Usuario;
import tani.dto.otros.TokenDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioServicio {

    Usuario registrarUsuario(RegistroUsuarioDTO usuarioDTO) throws Exception;

    Optional<Usuario> buscarPorCorreo(String correo) throws Exception;

    TokenDTO iniciarSesion(RegistroUsuarioDTO usuarioDTO);

    boolean existeCorreo(String correo);

    List<Usuario> listarUsuarios();

    void eliminarUsuario(Integer id);

    void recuperarPassword(String correo);
}
