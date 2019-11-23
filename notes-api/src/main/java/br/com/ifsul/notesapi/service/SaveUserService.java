package br.com.ifsul.notesapi.service;

import br.com.ifsul.notesapi.domain.UserDTO;
import br.com.ifsul.notesapi.domain.UserRegisterRequest;
import br.com.ifsul.notesapi.entity.User;
import br.com.ifsul.notesapi.exception.EmailAlreadyInUseException;
import br.com.ifsul.notesapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.ifsul.notesapi.mapper.UserDTOMapper.apply;
import static java.util.Objects.nonNull;

@Service
@Slf4j
public class SaveUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO save(final UserRegisterRequest request) {

        log.info("Inserindo novo usuário: {}", request.getEmail());

        User user = userRepository.findByEmail(request.getEmail());

        if (nonNull(user)) {

            log.error("E-mail já existente na base de dados.");
            throw new EmailAlreadyInUseException("Este e-mail já está sendo utilizado. Tente outro diferente.");
        }

        user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());

        return apply(userRepository.save(user));
    }
}
