package ifsul.com.br.notes.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 4674534597237600432L;

    private String email;
    private String password;
    private String fullName;

}
