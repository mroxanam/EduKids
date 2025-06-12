package webEduKids.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UserRegistrationDto {

    @NotEmpty(message = "El email es obligatorio")
    @Email(message = "Debe ser un email válido")
    private String email;

    @NotEmpty(message = "La contraseña es obligatoria")
    private String password;

    // Constructor vacío (necesario para Spring)
    public UserRegistrationDto() {
    }

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Método toString (opcional pero útil para debugging)
    @Override
    public String toString() {
        return "UserRegistrationDto{" +
                "email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                '}';
    }
}