package br.com.ifsul.notesapi.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Responsável pelos serviços referentes ao usuário")
public interface UserContract {

    @ApiOperation("Deleta conta do usuário logado")
    void delete();

}
