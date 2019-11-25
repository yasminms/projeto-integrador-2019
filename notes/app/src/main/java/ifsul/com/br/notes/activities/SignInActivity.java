package ifsul.com.br.notes.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import ifsul.com.br.notes.R;
import ifsul.com.br.notes.domain.AuthRequest;
import ifsul.com.br.notes.domain.AuthResponse;
import ifsul.com.br.notes.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ifsul.com.br.notes.utils.RetrofitUtils.retrofit;

public class SignInActivity extends AppCompatActivity implements com.mobsandgeeks.saripaar.Validator.ValidationListener {

    private Button btSignUp;
    private Button btSignIn;

    @Email(message = "Informe um e-mail válido")
    private EditText etEmail;

    @Password(min = 4, message = "Senha deve conter ao menos 4 caracteres")
    private EditText etPassword;

    private Validator validator;
    private UserService userService;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        componentsInitializer();

        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validator.validate();
            }
        });

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });
    }

    private void componentsInitializer() {
        btSignUp = findViewById(R.id.bt_signup);
        btSignIn = findViewById(R.id.bt_signin);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        validator = new Validator(this);
        validator.setValidationListener(this);
        userService = retrofit.create(UserService.class);
        sharedPreferences = getSharedPreferences("mynotes", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public void onValidationSucceeded() {

        final AuthRequest request = AuthRequest.builder()
                .email(etEmail.getText().toString())
                .password(etPassword.getText().toString())
                .build();

        userService.auth(request).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {

                if (response.isSuccessful()) {

                    editor.putBoolean("authenticated", true);
                    editor.putString("token", response.body().getToken());
                    editor.commit();

                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    finish();
                } else {

                    Toast.makeText(SignInActivity.this, "E-mail ou senha incorreto(s).", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {

                Toast.makeText(SignInActivity.this, "Falha na comunicação. Tente novamente.", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onValidationFailed(final List<ValidationError> errors) {

        for (ValidationError error : errors) {
            final View component = error.getView();
            final String message = error.getCollatedErrorMessage(this);
            ((EditText) component).setError(message);
        }
    }
}
