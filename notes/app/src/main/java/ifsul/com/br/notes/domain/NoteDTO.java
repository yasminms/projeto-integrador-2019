package ifsul.com.br.notes.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteDTO implements Serializable {

    private static final long serialVersionUID = -4220984636650157972L;

    private String title;
    private String text;
    private String creationTimestamp;
    private String updateTimestamp;

}
