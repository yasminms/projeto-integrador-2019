package ifsul.com.br.notes.activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ifsul.com.br.notes.R;
import ifsul.com.br.notes.domain.NoteDTO;

public class CustomAdapter extends ArrayAdapter<NoteDTO> {

    private static final int MAX_TITLE_LENGTH = 22;
    private static final int MAX_TEXT_LENGTH = 50;

    private List<NoteDTO> notes;
    private Context context;
    private int resource;

    public CustomAdapter(Context context, int resource, List<NoteDTO> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.notes = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final LayoutInflater layoutInflater = LayoutInflater.from(context);

        final View view = layoutInflater.inflate(resource, null, false);

        final TextView tvTitle = view.findViewById(R.id.tv_title);
        final TextView tvText = view.findViewById(R.id.tv_text);
        final TextView tvUpdate = view.findViewById(R.id.tv_update);

        final NoteDTO note = notes.get(position);

        if (note.getTitle().length() >= MAX_TITLE_LENGTH) {
            note.setTitle(note.getTitle().substring(0, MAX_TITLE_LENGTH) + "...");
        }

        if (note.getText().length() >= MAX_TEXT_LENGTH) {
            note.setText(note.getText().substring(0, MAX_TEXT_LENGTH) + "...");
        }

        tvTitle.setText(note.getTitle());
        tvText.setText(note.getText());
        tvUpdate.setText(note.getUpdateTimestamp());

        return view;
    }
}
