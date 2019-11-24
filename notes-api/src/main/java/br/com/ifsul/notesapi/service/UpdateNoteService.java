package br.com.ifsul.notesapi.service;

import br.com.ifsul.notesapi.domain.NoteRequest;
import br.com.ifsul.notesapi.entity.Note;
import br.com.ifsul.notesapi.entity.User;
import br.com.ifsul.notesapi.exception.BadRequestException;
import br.com.ifsul.notesapi.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.ifsul.notesapi.config.security.CustomUserDetailsService.getUser;

@Service
@Slf4j
public class UpdateNoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private FindUserByEmailService findUserByEmailService;

    @Autowired
    private FindNoteByIdService findNoteByIdService;

    public void update(final Integer id, final NoteRequest request) {

        final User user = findUserByEmailService.findByEmail(getUser().getEmail());
        final Note note = findNoteByIdService.findById(id);

        if (!note.getAuthor().equals(user)) {

            log.error("Não é possível atualizar uma anotação de outro usuário: {}", user.getEmail());
            throw new BadRequestException("Não é possível atualizar essa anotação. Tente outra diferente.");
        }

        note.setTitle(request.getTitle());
        note.setText(request.getText());

        noteRepository.save(note);
    }
}
