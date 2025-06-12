package webEduKids.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webEduKids.demo.entity.Usuario;
import webEduKids.demo.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean registrarUsuario(String email, String password) {
        if (usuarioRepository.findByEmail(email).isPresent()) {
            return false;
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPassword(passwordEncoder.encode(password)); // Encriptación aquí

        usuarioRepository.save(nuevoUsuario);
        return true;
    }
}
