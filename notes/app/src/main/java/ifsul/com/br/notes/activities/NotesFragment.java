package ifsul.com.br.notes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import ifsul.com.br.notes.R;
import ifsul.com.br.notes.domain.NoteDTO;
import ifsul.com.br.notes.services.NoteService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ifsul.com.br.notes.utils.RetrofitUtils.retrofit;
import static ifsul.com.br.notes.utils.UserUtils.getToken;

public class NotesFragment extends Fragment {

    private NoteService noteService;
    private CustomAdapter arrayAdapter;
    private List<NoteDTO> notes;
    private ListView lvNotes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, null);

        lvNotes = v.findViewById(R.id.lv_notes);

        noteService = retrofit.create(NoteService.class);

        noteService.findAll(getToken(getContext())).enqueue(new Callback<List<NoteDTO>>() {
            @Override
            public void onResponse(Call<List<NoteDTO>> call, Response<List<NoteDTO>> response) {

                if (response.isSuccessful()) {

                    notes = response.body();
                    arrayAdapter = new CustomAdapter(getContext(), R.layout.row_item, notes);
                    lvNotes.setAdapter(arrayAdapter);

                } else {

                    Toast.makeText(getContext(), "Não foi possível buscar as anotações. Tente novamente em alguns instantes.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<NoteDTO>> call, Throwable t) {

                Toast.makeText(getContext(), "Falha na comunicação. Tente novamente.", Toast.LENGTH_LONG).show();
            }
        });

        lvNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Intent intent = new Intent(getContext(), EditNoteActivity.class);
                intent.putExtra("note", notes.get(position));

                startActivity(intent);
            }
        });

        return v;
    }
}
