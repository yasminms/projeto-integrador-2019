package br.com.ifsul.notesapi.controller.note;

import br.com.ifsul.notesapi.domain.NoteDTO;
import br.com.ifsul.notesapi.domain.NoteRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(description = "Responsável pelos serviços referentes as anotações")
public interface NoteContract {

    @ApiOperation("Insere uma nova anotação")
    void save(NoteRequest request);

    @ApiOperation("Lista todas anotações do usuário logado")
    List<NoteDTO> findAll();

    @ApiOperation("Deleta uma anotação")
    void delete(Integer id);

    @ApiOperation("Atualiza uma anotação existente")
    void update(Integer id, NoteRequest request);

}
