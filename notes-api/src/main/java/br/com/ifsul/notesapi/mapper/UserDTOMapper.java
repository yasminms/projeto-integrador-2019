package br.com.ifsul.notesapi.mapper;

import br.com.ifsul.notesapi.config.security.UserPrincipal;
import br.com.ifsul.notesapi.domain.UserDTO;
import br.com.ifsul.notesapi.entity.User;

public final class UserDTOMapper {

    public static UserDTO apply(final User user) {

        return UserDTO.builder()
                .email(user.getEmail())
                .fullName(user.getFullName())
                .build();
    }

    public static UserDTO apply(final UserPrincipal userPrincipal) {

        return UserDTO.builder()
                .email(userPrincipal.getEmail())
                .fullName(userPrincipal.getFullName())
                .build();
    }
}
