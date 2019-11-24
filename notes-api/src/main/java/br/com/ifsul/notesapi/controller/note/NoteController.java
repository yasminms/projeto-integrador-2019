package br.com.ifsul.notesapi.controller.note;

import br.com.ifsul.notesapi.domain.NoteDTO;
import br.com.ifsul.notesapi.domain.NoteRequest;
import br.com.ifsul.notesapi.service.DeleteNoteService;
import br.com.ifsul.notesapi.service.FindAllNotesService;
import br.com.ifsul.notesapi.service.SaveNoteService;
import br.com.ifsul.notesapi.service.UpdateNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController implements NoteContract {

    @Autowired
    private SaveNoteService saveNoteService;

    @Autowired
    private FindAllNotesService findAllNotesService;

    @Autowired
    private DeleteNoteService deleteNoteService;

    @Autowired
    private UpdateNoteService updateNoteService;

    @Override
    @PostMapping
    public void save(@Valid @RequestBody final NoteRequest request) {

        saveNoteService.save(request);
    }

    @Override
    @PutMapping("/{id}")
    public void update(@PathVariable("id") final Integer id, @Valid @RequestBody final NoteRequest request) {

        updateNoteService.update(id, request);
    }

    @Override
    @GetMapping
    public List<NoteDTO> findAll() {

        return findAllNotesService.findAll();
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final Integer id) {

        deleteNoteService.delete(id);
    }

}
