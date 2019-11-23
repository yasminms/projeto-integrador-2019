package br.com.ifsul.notesapi.service;

import br.com.ifsul.notesapi.entity.User;
import br.com.ifsul.notesapi.exception.EntityNotFoundException;
import br.com.ifsul.notesapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
public class FindUserByEmailService {

    @Autowired
    private UserRepository userRepository;

    public User findByEmail(final String email) {

        return ofNullable(userRepository.findByEmail(email)).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado. Tente novamente."));
    }

}
