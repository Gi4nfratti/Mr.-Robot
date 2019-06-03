package br.usjt.mrrobot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.usjt.mrrobot.R;
import br.usjt.mrrobot.model.User;


public class NomeEmailActivity extends AppCompatActivity {

    TextInputEditText nome_login;
    TextInputEditText email_login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_email);
        Intent i = new Intent(this,ChatActivity.class);

        nome_login =  findViewById(R.id.nome_login);
        email_login = findViewById(R.id.email_login);
        Toast.makeText(NomeEmailActivity.this, "Dica: Digíte seu nome!", Toast.LENGTH_LONG).show();
    }

    public void botaoEntrar(View view){
        Intent i = new Intent(NomeEmailActivity.this,ChatActivity.class);
        User user = new User();
        TextInputEditText editTextNome = findViewById(R.id.nome_login);
        user.setNome(editTextNome.getText().toString());
        EditText editTextEmail = (TextInputEditText) findViewById(R.id.email_login);
        user.setEmail(editTextEmail.getText().toString());
        i.putExtra("usuario", user);
        startActivity(i);
    }

}
