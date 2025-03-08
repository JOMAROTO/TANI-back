package tani.services.interfaces;

import tani.dto.usuario.InformacionUsuarioDTO;
import tani.dto.usuario.LoginDTO;
import tani.dto.usuario.RegistroUsuarioDTO;
import tani.model.entities.Usuario;
import tani.dto.otros.TokenDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioServicio {

    InformacionUsuarioDTO registrarUsuario(RegistroUsuarioDTO usuarioDTO) throws Exception;

    Optional<InformacionUsuarioDTO> buscarPorCorreo(String correo) throws Exception;

    TokenDTO iniciarSesion(LoginDTO usuarioDTO);

    boolean existeCorreo(String correo);

    InformacionUsuarioDTO editarUsuario(RegistroUsuarioDTO usuarioDTO) throws Exception;


    List<InformacionUsuarioDTO> listarUsuarios();

    void eliminarUsuario(Integer id);

    void recuperarPassword(String correo);
}
