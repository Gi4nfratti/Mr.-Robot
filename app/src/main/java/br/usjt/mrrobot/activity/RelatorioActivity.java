package br.usjt.mrrobot.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import br.usjt.mrrobot.R;

public class RelatorioActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relatorio);
        TextView qtd_atend = findViewById(R.id.qtd_atend);
        TextView data_hora_atend = findViewById(R.id.data_hora_atend);
        TextView duracao_interacao_atend = findViewById(R.id.duracao_interacao_atend);
        TextView qtd_interacoes = findViewById(R.id.qtd_interacoes);
    }

    public void relatorioSairButton(View view) {
        new AlertDialog.Builder(this)
                .setMessage("Tem certeza que deseja sair?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }
}
