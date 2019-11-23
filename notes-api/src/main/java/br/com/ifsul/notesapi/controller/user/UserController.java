package br.com.ifsul.notesapi.controller.user;

import br.com.ifsul.notesapi.service.DeleteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController implements UserContract {

    @Autowired
    private DeleteUserService deleteUserService;

    @Override
    @DeleteMapping
    public void delete() {

        deleteUserService.delete();
    }
}
