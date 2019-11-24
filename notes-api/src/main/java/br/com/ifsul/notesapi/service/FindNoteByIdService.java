package br.com.ifsul.notesapi.service;

import br.com.ifsul.notesapi.entity.Note;
import br.com.ifsul.notesapi.exception.EntityNotFoundException;
import br.com.ifsul.notesapi.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindNoteByIdService {

    @Autowired
    private NoteRepository noteRepository;

    public Note findById(final Integer id) {

        return noteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Anotação não encontrada. Tente novamente."));
    }

}
