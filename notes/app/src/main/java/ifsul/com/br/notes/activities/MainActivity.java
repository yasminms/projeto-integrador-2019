package ifsul.com.br.notes.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import ifsul.com.br.notes.R;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    private FragmentTransaction fragmentTransaction;

    private ImageButton btCreate;

    private ImageButton btSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        componentsInitializer();

        fragmentTransaction.replace(R.id.linear_content, new NotesFragment());

        fragmentTransaction.commit();

        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NoteActivity.class));
                finish();
            }
        });

        btSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                finish();
            }
        });
    }

    private void componentsInitializer() {
        btCreate = findViewById(R.id.bt_create);
        btSettings = findViewById(R.id.bt_settings);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }
}
