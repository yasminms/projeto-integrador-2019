package br.com.ifsul.notesapi.repository;

import br.com.ifsul.notesapi.entity.Note;
import br.com.ifsul.notesapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

    @Query("FROM Note n WHERE " +
            "n.author = :author")
    List<Note> findAll(@Param("author") User user);

}
