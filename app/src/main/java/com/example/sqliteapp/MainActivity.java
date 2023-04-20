package com.example.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nome, frequencia, dosagem, horario;
    Button insert, update, delete, view;

    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.nome);
        frequencia = findViewById(R.id.frequencia);
        dosagem = findViewById(R.id.dosagem);
        horario = findViewById(R.id.horario);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);

        DB= new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeTXT = nome.getText().toString();
                String frequenciaTXT = frequencia.getText().toString();
                String dosagemTXT = dosagem.getText().toString();
                String horarioTXT = horario.getText().toString();

                Boolean checkInsertData = DB.insertData(nomeTXT, frequenciaTXT, dosagemTXT, horarioTXT);
                if(checkInsertData) {
                    Toast.makeText(MainActivity.this, "Medicamento cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Medicamento não foi cadastrado.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeTXT = nome.getText().toString();
                String frequenciaTXT = frequencia.getText().toString();
                String dosagemTXT = dosagem.getText().toString();
                String horarioTXT = horario.getText().toString();

                Boolean checkUpdateData = DB.updateData(nomeTXT, frequenciaTXT, dosagemTXT, horarioTXT);
                if(checkUpdateData) {
                    Toast.makeText(MainActivity.this, "Medicamento atualizado com sucesso.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Medicamento não foi atualizado.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeTXT = nome.getText().toString();

                Boolean checkDeleteData = DB.deleteData(nomeTXT);
                if(checkDeleteData) {
                    Toast.makeText(MainActivity.this, "Medicamento excluído com sucesso.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Medicamento não foi excluído.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getData();
                if(res.getCount() ==0) {
                    Toast.makeText(MainActivity.this, "Não existe esse medicamento", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Nome: " + res.getString(0) + "\n");
                    buffer.append("Frequência:" + res.getString(1) + "\n");
                    buffer.append("Dosagem:" + res.getString(2) + "\n");
                    buffer.append("Horário:" + res.getString(3) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Medicamentos");
                builder.setMessage((buffer.toString()));
                builder.show();
            }
        });
    }
}