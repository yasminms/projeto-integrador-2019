package ifsul.com.br.notes.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.List;

import ifsul.com.br.notes.R;
import ifsul.com.br.notes.domain.NoteDTO;
import ifsul.com.br.notes.services.NoteService;
import ifsul.com.br.notes.utils.UserUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ifsul.com.br.notes.utils.RetrofitUtils.retrofit;
import static ifsul.com.br.notes.utils.UserUtils.getToken;

public class NotesActivity extends ListActivity {

    private NoteService noteService;
    private CustomAdapter arrayAdapter;
    private List<NoteDTO> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        noteService = retrofit.create(NoteService.class);

        noteService.findAll(getToken(this)).enqueue(new Callback<List<NoteDTO>>() {
            @Override
            public void onResponse(Call<List<NoteDTO>> call, Response<List<NoteDTO>> response) {

                if (response.isSuccessful()) {

                    notes = response.body();
                    arrayAdapter = new CustomAdapter(NotesActivity.this, R.layout.row_item, notes);
                    setListAdapter(arrayAdapter);
                } else {

                    Toast.makeText(NotesActivity.this, "Não foi possível buscar as anotações. Tente novamente em alguns instantes.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<NoteDTO>> call, Throwable t) {

                Toast.makeText(NotesActivity.this, "Falha na comunicação. Tente novamente.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
