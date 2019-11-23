package br.com.ifsul.notesapi.mapper;

import br.com.ifsul.notesapi.config.security.UserPrincipal;
import br.com.ifsul.notesapi.domain.AuthResponse;

public final class AuthResponseMapper {

    public static AuthResponse apply(final String token, final UserPrincipal userPrincipal) {

        return AuthResponse.builder()
                .token(token)
                .user(UserDTOMapper.apply(userPrincipal))
                .build();
    }
}
