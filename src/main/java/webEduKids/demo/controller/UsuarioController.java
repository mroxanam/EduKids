package webEduKids.demo.controller;
import webEduKids.demo.dto.UsuarioDTO;
import webEduKids.demo.service.UsuarioServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping
    public ResponseEntity<String> registrarUsuario(@Valid @RequestBody UsuarioDTO dto) {
        usuarioServicio.registrarUsuario(dto);
        return new ResponseEntity<>("Usuario registrado con éxito", HttpStatus.CREATED);
    }
}
