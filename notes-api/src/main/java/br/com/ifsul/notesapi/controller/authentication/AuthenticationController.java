package br.com.ifsul.notesapi.controller.authentication;

import br.com.ifsul.notesapi.config.security.AuthenticationService;
import br.com.ifsul.notesapi.config.security.CustomUserDetailsService;
import br.com.ifsul.notesapi.domain.AuthRequest;
import br.com.ifsul.notesapi.domain.AuthResponse;
import br.com.ifsul.notesapi.domain.UserDTO;
import br.com.ifsul.notesapi.domain.UserRegisterRequest;
import br.com.ifsul.notesapi.service.SaveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.ifsul.notesapi.mapper.AuthResponseMapper.apply;

@RestController
@RequestMapping("/public")
public class AuthenticationController implements AuthenticationContract {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private SaveUserService saveUserService;

    @Override
    @PostMapping("/auth")
    public AuthResponse authenticate(@Valid @RequestBody final AuthRequest request) {
        final String token = authenticationService.authenticate(request.getEmail(), request.getPassword());
        return apply(token, CustomUserDetailsService.getUser());
    }

    @Override
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO save(@Valid @RequestBody final UserRegisterRequest request) {
        return saveUserService.save(request);
    }
}
