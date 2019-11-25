package ifsul.com.br.notes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;

import java.util.List;

import ifsul.com.br.notes.R;
import ifsul.com.br.notes.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ifsul.com.br.notes.utils.RetrofitUtils.retrofit;
import static ifsul.com.br.notes.utils.UserUtils.getEmail;
import static ifsul.com.br.notes.utils.UserUtils.getName;
import static ifsul.com.br.notes.utils.UserUtils.getToken;
import static ifsul.com.br.notes.utils.UserUtils.logout;

public class ProfileActivity extends AppCompatActivity implements Validator.ValidationListener {

    private Button btEdit;
    private Button btLogout;
    private Button btDeleteAccount;
    private ImageButton btBack;

    private TextView tvEmail;

    @Length(min = 3, message = "Nome informado deve ter no mínimo 3 caracteres")
    private EditText etFullName;

    private Validator validator;
    private UserService userService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        componentsInitializer();

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validator.validate();
            }
        });

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logout(ProfileActivity.this);
                startActivity(new Intent(ProfileActivity.this, SignInActivity.class));
                finish();
            }
        });

        btDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userService.delete(getToken(ProfileActivity.this)).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if (response.isSuccessful()) {

                            logout(ProfileActivity.this);
                            startActivity(new Intent(ProfileActivity.this, SignInActivity.class));
                            finish();
                        } else {

                            Toast.makeText(ProfileActivity.this, "Não foi possível deletar sua conta. Tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                        Toast.makeText(ProfileActivity.this, "Falha na comunicação. Tente novamente.", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void componentsInitializer() {

        btEdit = findViewById(R.id.bt_edit_account);
        btLogout = findViewById(R.id.bt_logout);
        btBack = findViewById(R.id.bt_back);
        btDeleteAccount = findViewById(R.id.bt_delete_account);
        etFullName = findViewById(R.id.et_full_name);
        etFullName.setText(getName(this));
        tvEmail = findViewById(R.id.tv_email);
        tvEmail.setText(getEmail(this));
        userService = retrofit.create(UserService.class);
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public void onValidationSucceeded() {

        userService.update(getToken(this), etFullName.getText().toString()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {

                    logout(ProfileActivity.this);
                    startActivity(new Intent(ProfileActivity.this, SignInActivity.class));
                    finish();
                } else {

                    Toast.makeText(ProfileActivity.this, "Não foi possível atualizar suas informações. Tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(ProfileActivity.this, "Falha na comunicação. Tente novamente.", Toast.LENGTH_LONG).show();
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
