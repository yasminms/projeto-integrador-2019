package ifsul.com.br.notes.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.mobsandgeeks.saripaar.ValidationError;

import java.util.List;

import ifsul.com.br.notes.R;

public class SignUpActivity extends AppCompatActivity implements com.mobsandgeeks.saripaar.Validator.ValidationListener {

    private Button btRegister;
    private EditText etFullName;
    private EditText etEmail;
    private EditText etConfirmEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        componentsInitializer();
    }

    private void componentsInitializer() {
        btRegister = findViewById(R.id.bt_register);
        etFullName = findViewById(R.id.et_full_name);
        etEmail = findViewById(R.id.et_email);
        etConfirmEmail = findViewById(R.id.et_confirm_email);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirma_password);
    }

    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

    }
}
