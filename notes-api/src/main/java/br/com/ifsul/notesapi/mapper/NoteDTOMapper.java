package br.com.ifsul.notesapi.mapper;

import br.com.ifsul.notesapi.domain.NoteDTO;
import br.com.ifsul.notesapi.entity.Note;

import static br.com.ifsul.notesapi.utils.DateTimeUtils.localDateTimeToString;

public final class NoteDTOMapper {

    public static NoteDTO apply(final Note note) {

        return NoteDTO.builder()
                .title(note.getTitle())
                .text(note.getText())
                .creationTimestamp(localDateTimeToString(note.getCreationTimestamp()))
                .updateTimestamp(localDateTimeToString(note.getUpdateTimestamp()))
                .build();
    }

}
