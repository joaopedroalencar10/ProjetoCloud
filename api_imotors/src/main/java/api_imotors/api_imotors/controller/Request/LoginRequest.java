package api_imotors.api_imotors.controller.Request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @Column(nullable = false)
    @NotBlank(message = "O campo username não pode ser vazio")
    public String username;
    
    @Column(nullable = false)
    @NotBlank(message = "O campo senha não pode ser vazio")
    public String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
