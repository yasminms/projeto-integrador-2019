package br.com.ifsul.notesapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteRequest implements Serializable {

    private static final long serialVersionUID = -5525063841412111372L;

    @NotBlank(message = "A anotação deve possuir um título")
    private String title;

    private String text;

}
