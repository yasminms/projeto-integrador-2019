package br.com.ifsul.notesapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 1768919052366338314L;

    @NotBlank(message = "E-mail deve ser preenchido")
    @Email(message = "O endereço fornecido deve ser um e-mail válido")
    private String email;

    @NotBlank(message = "Senha deve ser preenchida")
    private String password;

    @NotBlank(message = "Nome completo deve ser preenchido")
    private String fullName;

}
