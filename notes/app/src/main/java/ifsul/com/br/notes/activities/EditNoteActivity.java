package ifsul.com.br.notes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import ifsul.com.br.notes.R;
import ifsul.com.br.notes.domain.NoteDTO;
import ifsul.com.br.notes.domain.NoteRequest;
import ifsul.com.br.notes.services.NoteService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ifsul.com.br.notes.utils.RetrofitUtils.retrofit;
import static ifsul.com.br.notes.utils.UserUtils.getToken;

public class EditNoteActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty(message = "Título deve ser informado")
    private EditText etTitle;

    private EditText etDescripition;

    private Button btEdit;

    private Button btDelete;

    private ImageButton btBack;

    private Validator validator;

    private NoteService noteService;

    private NoteDTO note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        componentsInitializer();
        note = (NoteDTO) getIntent().getSerializableExtra("note");
        updateComponents(note);

        // Changing default screen adjustment
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validator.validate();
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                noteService.delete(getToken(EditNoteActivity.this), note.getId()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if (response.isSuccessful()) {

                            startActivity(new Intent(EditNoteActivity.this, MainActivity.class));
                            finish();
                        } else {

                            Toast.makeText(EditNoteActivity.this, "Não foi possível deletar sua anotação. Tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                        Toast.makeText(EditNoteActivity.this, "Falha na comunicação. Tente novamente.", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(EditNoteActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void componentsInitializer() {

        etTitle = findViewById(R.id.et_title);
        etDescripition = findViewById(R.id.et_description);
        btEdit = findViewById(R.id.bt_add);
        btDelete = findViewById(R.id.bt_delete);
        btBack = findViewById(R.id.bt_back);
        validator = new Validator(this);
        validator.setValidationListener(this);
        noteService = retrofit.create(NoteService.class);
    }

    private void updateComponents(final NoteDTO note) {

        etTitle.setText(note.getTitle());
        etDescripition.setText(note.getText());
        btEdit.setText("Editar");
    }

    @Override
    public void onValidationSucceeded() {

        final NoteRequest request = NoteRequest.builder()
                .text(etDescripition.getText().toString())
                .title(etTitle.getText().toString())
                .build();

        noteService.update(getToken(this), note.getId(), request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {

                    startActivity(new Intent(EditNoteActivity.this, MainActivity.class));
                    finish();
                } else {

                    Toast.makeText(EditNoteActivity.this, "Não foi possível cadastrar sua anotação. Tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(EditNoteActivity.this, "Falha na comunicação. Tente novamente.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            final View component = error.getView();
            final String message = error.getCollatedErrorMessage(this);
            ((EditText) component).setError(message);
        }
    }
}
