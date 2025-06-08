package webEduKids.demo.service;
import webEduKids.demo.dto.UsuarioDTO;
import webEduKids.demo.entity.Usuario;
public interface UsuarioServicio {
    Usuario registrarUsuario(UsuarioDTO dto);
}
