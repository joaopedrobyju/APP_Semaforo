package com.example.semaforo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{

    private EditText editTextEndereco;
    private EditText editTextPorta;
    private Button buttonConectar;
    private Button buttonDesconectar;
    private Button buttonInteragir;
    private  MQTTService mqttService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEndereco = findViewById(R.id.editTextEndereco);
        editTextPorta = findViewById(R.id.editTextPorta);
        buttonConectar = findViewById(R.id.buttonConectar);
        buttonDesconectar = findViewById(R.id.buttonDesconectar);
        buttonInteragir = findViewById(R.id.buttonInteragir);

        buttonInteragir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTela();
            }
        });


        buttonConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // Conectar no broker
            String hostname = "broker.hivemq.com";
            String port = "1883";
            mqttService = new MQTTService(getApplicationContext(), hostname, port);
            mqttService.connect(buttonConectar, buttonDesconectar, buttonInteragir);
            }
        });

        buttonDesconectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mqttService.desconnect(buttonConectar, buttonDesconectar, buttonInteragir);

            }
        });




    }


    private void abrirTela() {
        Intent telaStatus = new Intent(this, SemaforoActivity.class);
       startActivity(telaStatus);

        finish();
    }



}