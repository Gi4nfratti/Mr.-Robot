package br.usjt.mrrobot.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import br.usjt.mrrobot.R;

public class RelatorioActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relatorio);
        TextView relatorioTitulo = findViewById(R.id.relatorioTitulo);
        TextView primeiraInteracao = findViewById(R.id.primeiraInteracao);
        TextView segundaInteracao = findViewById(R.id.segundaInteracao);
        TextView terceiraInteracao = findViewById(R.id.terceiraInteracao);
        TextView naoRespondidas = findViewById(R.id.naoRespondidas);
        TextView periodo1 = findViewById(R.id.periodo1);
        TextView periodo2 = findViewById(R.id.periodo2);
        TextView periodo3 = findViewById(R.id.periodo3);
        FloatingActionButton datePicker1 = findViewById(R.id.datePicker1);
        FloatingActionButton datePicker2 = findViewById(R.id.datePicker2);

        datePicker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RelatorioActivity.this, "Sem função no momento", Toast.LENGTH_SHORT).show();
            }
        });

        datePicker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RelatorioActivity.this, "Sem função no momento", Toast.LENGTH_SHORT).show();
            }
        });

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

    private void datePicker(View view) {
        Calendar currentDay = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = null;
        new DatePickerDialog(getApplicationContext(), dateSetListener, currentDay.get(Calendar.YEAR), currentDay.get(Calendar.MONTH), currentDay.get(Calendar.DAY_OF_MONTH)).show();
    }



}
