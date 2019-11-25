package ifsul.com.br.notes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmEmail;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import ifsul.com.br.notes.R;
import ifsul.com.br.notes.domain.UserDTO;
import ifsul.com.br.notes.domain.UserRegisterRequest;
import ifsul.com.br.notes.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ifsul.com.br.notes.utils.RetrofitUtils.retrofit;

public class SignUpActivity extends AppCompatActivity implements com.mobsandgeeks.saripaar.Validator.ValidationListener {

    private Button btRegister;
    private ImageButton btBack;

    @Length(min = 3, message = "Nome informado deve ter no mínimo 3 caracteres")
    private EditText etFullName;

    @Email(message = "Informe um e-mail válido")
    private EditText etEmail;

    @ConfirmEmail(message = "E-mail diferente do informado anteriormente")
    private EditText etConfirmEmail;

    @Password(min = 4, message = "Senha deve ter no mínimo 4 caracteres")
    private EditText etPassword;

    @ConfirmPassword(message = "Senha diferente da informada anteriormente")
    private EditText etConfirmPassword;

    private Validator validator;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        componentsInitializer();

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validator.validate();
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

    }

    private void componentsInitializer() {
        btRegister = findViewById(R.id.bt_register);
        btBack = findViewById(R.id.bt_back);
        etFullName = findViewById(R.id.et_full_name);
        etEmail = findViewById(R.id.et_email);
        etConfirmEmail = findViewById(R.id.et_confirm_email);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        validator = new Validator(this);
        validator.setValidationListener(this);
        userService = retrofit.create(UserService.class);
    }

    @Override
    public void onValidationSucceeded() {

        final UserRegisterRequest request = UserRegisterRequest.builder()
                .fullName(etFullName.getText().toString())
                .email(etEmail.getText().toString())
                .password(etPassword.getText().toString())
                .build();

        userService.register(request).enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {

                if (response.isSuccessful()) {

                    Toast.makeText(SignUpActivity.this, "Cadastro efetuado com sucesso.", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                    finish();
                } else {

                    Toast.makeText(SignUpActivity.this, "E-mail em uso. Tente outro diferente.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {

                Toast.makeText(SignUpActivity.this, "Falha na comunicação. Tente novamente.", Toast.LENGTH_LONG).show();
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
