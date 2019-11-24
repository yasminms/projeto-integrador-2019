package br.com.ifsul.notesapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteDTO implements Serializable {

    private static final long serialVersionUID = 1016637480054392344L;

    private Integer id;
    private String title;
    private String text;
    private String creationTimestamp;
    private String updateTimestamp;

}
