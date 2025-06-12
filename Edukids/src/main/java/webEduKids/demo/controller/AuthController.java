package webEduKids.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import webEduKids.demo.service.AuthService;
import webEduKids.demo.dto.UserRegistrationDto;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        model.addAttribute("showSuccessModal", false);
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(
            @ModelAttribute("user") UserRegistrationDto userDto,
            Model model) {

        try {
            // Validar datos
            if (userDto.getEmail() == null || userDto.getEmail().isEmpty() ||
                    userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
                model.addAttribute("error", "Por favor completa todos los campos");
                return "register";
            }

            // Registrar usuario
            boolean registroExitoso = authService.registrarUsuario(userDto.getEmail(), userDto.getPassword());

            if (registroExitoso) {
                model.addAttribute("showSuccessModal", true);
                model.addAttribute("user", new UserRegistrationDto()); // Limpiar el formulario
            } else {
                model.addAttribute("error", "El correo electrónico ya está registrado");
            }
        } catch (DataAccessException e) {
            model.addAttribute("error", "Error de conexión con la base de datos. Inténtalo de nuevo más tarde.");
            e.printStackTrace();
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error inesperado");
            e.printStackTrace();
        }

        return "register";
    }
}
