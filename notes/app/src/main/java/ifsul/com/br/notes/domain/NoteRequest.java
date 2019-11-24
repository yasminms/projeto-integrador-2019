package ifsul.com.br.notes.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NoteRequest {

    private static final long serialVersionUID = -5525063841412111372L;

    private String title;

    private String text;

}
