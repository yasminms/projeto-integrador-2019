package br.com.ifsul.notesapi.service;

import br.com.ifsul.notesapi.domain.NoteRequest;
import br.com.ifsul.notesapi.entity.Note;
import br.com.ifsul.notesapi.entity.User;
import br.com.ifsul.notesapi.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.ifsul.notesapi.config.security.CustomUserDetailsService.getUser;

@Service
@Slf4j
public class SaveNoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private FindUserByEmailService findUserByEmailService;

    public void save(final NoteRequest request) {

        log.info("Inserindo nova anotação para usuário: {}", getUser().getEmail());

        final User user = findUserByEmailService.findByEmail(getUser().getEmail());

        final Note note = new Note();

        note.setAuthor(user);
        note.setTitle(request.getTitle());
        note.setText(request.getText());

        noteRepository.save(note);
    }
}
