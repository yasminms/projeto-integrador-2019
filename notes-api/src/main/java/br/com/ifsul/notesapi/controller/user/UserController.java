package br.com.ifsul.notesapi.controller.user;

import br.com.ifsul.notesapi.service.DeleteUserService;
import br.com.ifsul.notesapi.service.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController implements UserContract {

    @Autowired
    private DeleteUserService deleteUserService;

    @Autowired
    private UpdateUserService updateUserService;

    @Override
    @DeleteMapping
    public void delete() {

        deleteUserService.delete();
    }

    @Override
    @PutMapping
    public void update(@RequestParam("name") final String name) {

        updateUserService.update(name);
    }
}
