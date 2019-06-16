package br.usjt.mrrobot.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.usjt.mrrobot.R;
import br.usjt.mrrobot.model.User;


public class NomeEmailActivity extends AppCompatActivity {

    TextInputEditText nome_login;
    TextInputEditText email_login;
    Button btnEntrar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_email);
        Intent i = new Intent(this,ChatActivity.class);

        btnEntrar = findViewById(R.id.botaoEntrar);
        nome_login =  findViewById(R.id.nome_login);
        email_login = findViewById(R.id.email_login);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFields()){
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
        });
    }

    public void botaoEntrar(View view){

    }

    private boolean validateFields() {
        int tamanho = 1;
        if (nome_login.getText().length() < tamanho) {
            Toast.makeText(NomeEmailActivity.this, "Dica: Digíte seu nome!", Toast.LENGTH_LONG).show();
            nome_login.setError("Nome requerido!");
            return false;
        } else {
            return true;
        }
    }
}
