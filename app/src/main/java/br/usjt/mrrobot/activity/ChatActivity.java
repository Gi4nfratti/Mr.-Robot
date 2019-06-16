package br.usjt.mrrobot.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.usjt.mrrobot.R;
import br.usjt.mrrobot.adapter.MensagensAdapter;
import br.usjt.mrrobot.model.Mensagem;
import br.usjt.mrrobot.model.User;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerMensagens;
    private MensagensAdapter adapter;
    private List<Mensagem> mensagens = new ArrayList<>();
    private FloatingActionButton btnEnviar;
    private EditText txtEditText;
    private Mensagem mensagem;
    Context context;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;

        recyclerMensagens = findViewById(R.id.recyclerMensagens);
        txtEditText = findViewById(R.id.textoEditText);
        btnEnviar = findViewById(R.id.enviarButton);

        adapter = new MensagensAdapter(mensagens, getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerMensagens.setLayoutManager(layoutManager);
        recyclerMensagens.setAdapter(adapter);

        user = (User) getIntent().getSerializableExtra("usuario");
        if(user.getNome().length() > 0) {
            mensagem = new Mensagem("Mr. Robot", "Olá " + user.getNome() + ", eu sou o Mr. Robot. Como posso te ajudar?");
        } else{
            mensagem = new Mensagem("Mr. Robot", "Olá usuário, eu sou o Mr. Robot. Como posso te ajudar?");
        }
        mensagens.add(mensagem);

        final User finalUser = user;
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtEditText.length() > 0) {
                    String input = txtEditText.getText().toString();
                    mensagem = new Mensagem(finalUser.getNome(), input);
                    mensagens.add(mensagem);
                    txtEditText.setText("");
                    recyclerMensagens.smoothScrollToPosition(recyclerMensagens.getAdapter().getItemCount()+1);
                    adapter.notifyDataSetChanged();
                    getResponse(input);
                }else{
                    Toast.makeText(ChatActivity.this, "Escreva uma mensagem!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void relatorioButton(View view){
        Intent i = new Intent(ChatActivity.this, RelatorioActivity.class );
        startActivity(i);
    }

    public void exitButton(View view) {
        new AlertDialog.Builder(this)
                .setMessage("Deseja voltar a página inicial?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(ChatActivity.this, NomeEmailActivity.class );
                        startActivity(i);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    private void getResponse(String input) {

        //Passando o workspaceID
        String workSpaceId = "9eee37e7-3d11-4c6e-989b-7b71c78b4f5c";
        String urlAssistant = "https://gateway.watsonplatform.net/assistant/api/v1/workspaces/" + workSpaceId + "/message?version=2019-02-28";

        //Criando estrutura em JSON de input do usuário
        JSONObject inputJsonObject = new JSONObject();
        try {
            inputJsonObject.put("text",input);
        } catch (JSONException e){
            e.printStackTrace();
        }

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("input", inputJsonObject);
        } catch (JSONException e){
            e.printStackTrace();
        }

        //Fazendo autenticação
        String authentication = "YXBpa2V5OnRDbDB6aDJzLWRrUWhQUEIyb3c4SDBfY1dqZkxtbFpiTFU0T0cwRmZFU0lY";

        //Chamada https em JAVA
        AndroidNetworking.post(urlAssistant)
                .addHeaders("Content-Type","application/json")
                .addHeaders("Authorization","Basic " + authentication)
                .addJSONObjectBody(jsonBody)
                .setPriority(Priority.HIGH)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String outPutAssistant = "";

                        //Resposta do JSON
                        try {
                            String outputJsonObject = response.getJSONObject("output").getJSONArray("text").getString(0);
                            mensagem = new Mensagem("Mr. Robot", outputJsonObject);
                            mensagens.add(mensagem);
                            adapter.notifyDataSetChanged();
                            txtEditText.setText("");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(context, "Erro de conexão", Toast.LENGTH_LONG).show();
                    }
                });
    }

}
