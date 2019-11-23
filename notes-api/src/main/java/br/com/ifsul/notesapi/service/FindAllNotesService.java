package br.com.ifsul.notesapi.service;

import br.com.ifsul.notesapi.domain.NoteDTO;
import br.com.ifsul.notesapi.entity.User;
import br.com.ifsul.notesapi.mapper.NoteDTOMapper;
import br.com.ifsul.notesapi.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.ifsul.notesapi.config.security.CustomUserDetailsService.getUser;

@Service
public class FindAllNotesService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private FindUserByEmailService findUserByEmailService;

    public List<NoteDTO> findAll() {

        final User user = findUserByEmailService.findByEmail(getUser().getEmail());

        return noteRepository.findAll(user).stream().map(NoteDTOMapper::apply).collect(Collectors.toList());
    }

}
