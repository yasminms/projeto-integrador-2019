package ifsul.com.br.notes.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthRequest implements Serializable {

    private static final long serialVersionUID = -6970495245206030320L;

    private String email;
    private String password;

}
