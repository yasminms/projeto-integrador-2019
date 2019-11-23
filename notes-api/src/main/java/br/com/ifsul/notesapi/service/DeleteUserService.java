package br.com.ifsul.notesapi.service;

import br.com.ifsul.notesapi.entity.User;
import br.com.ifsul.notesapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.ifsul.notesapi.config.security.CustomUserDetailsService.getUser;

@Service
@Slf4j
public class DeleteUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FindUserByEmailService findUserByEmailService;

    public void delete() {

        log.info("Deletando conta de usuário logado: {}", getUser().getEmail());

        final User user = findUserByEmailService.findByEmail(getUser().getEmail());

        userRepository.delete(user);
    }
}
