package br.com.ifsul.notesapi.controller.authentication;

import br.com.ifsul.notesapi.domain.AuthRequest;
import br.com.ifsul.notesapi.domain.AuthResponse;
import br.com.ifsul.notesapi.domain.UserDTO;
import br.com.ifsul.notesapi.domain.UserRegisterRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Responsável pelos serviços referentes a autenticação")
public interface AuthenticationContract {

    @ApiOperation("Autentica o usuário, retornando um token de acesso")
    AuthResponse authenticate(final AuthRequest request);

    @ApiOperation(value = "Insere um usuário")
    UserDTO save(final UserRegisterRequest request);

}
