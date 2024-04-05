package com.example.semaforo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

public class SemaforoActivity extends AppCompatActivity implements  View.OnClickListener{

    private Switch switchStatuSemaforo;
    private Switch switchLedVermelho;
    private Switch switchLedVerde;

    private Switch switchLedAmarelo;

    private Button buttonVoltar;
    private Button butttonVermelho;
    private Button butttonAmarelo;
    private Button butttonVerde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        switchStatuSemaforo = findViewById(R.id.switchStatuSemaforo);
        switchLedVermelho = findViewById(R.id.switchLedVermelho);
        switchLedVerde = findViewById(R.id.switchLedVerde);
        switchLedAmarelo = findViewById(R.id.switchLedAmarelo);

        buttonVoltar = findViewById(R.id.buttonVoltar);
        butttonVermelho = findViewById(R.id.buttonVermelho);
        butttonAmarelo= findViewById(R.id.buttonAmarelo);
        butttonVerde = findViewById(R.id.buttonVerde);


        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        eventosSwitch();

        switchVermelho();

        switchVerde();

        switchAmarelo();


    }


    @Override
    public void onClick(View v) {

    }

    private void eventosSwitch(){
        switchStatuSemaforo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Context self = getApplicationContext();

                    //ligar com o mqtt
                    Status status = new Status();
                    String message = status.ligarSemaforo();

                    Constantes.mqttClient.publish(Constantes.TOPIC_LIGAR_DESLIGAR_SEMAFORO, message, new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            butttonVermelho.setBackgroundTintList(getColorStateList(R.color.vermelho));
                            butttonAmarelo.setBackgroundTintList(getColorStateList(R.color.amarelo));
                            butttonVerde.setBackgroundTintList(getColorStateList(R.color.verde));
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Toast.makeText(self, "Falhou ao ligar semáforo :(", Toast.LENGTH_LONG).show();

                        }
                    });
                } else {
                    Context self = getApplicationContext();

                    //desligar com o mqtt
                    Status status = new Status();
                    String message = status.desligarSemaforo();

                    Constantes.mqttClient.publish(Constantes.TOPIC_LIGAR_DESLIGAR_SEMAFORO, message, new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            butttonVermelho.setBackgroundTintList(getColorStateList(R.color.cinza));
                            butttonAmarelo.setBackgroundTintList(getColorStateList(R.color.cinza));
                            butttonVerde.setBackgroundTintList(getColorStateList(R.color.cinza));
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Toast.makeText(self, "Falhou ao desligar semáforo :(", Toast.LENGTH_LONG).show();

                        }
                    });
                }

            }



        });

    }

    private void switchVermelho(){
        switchLedVermelho.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Context self = getApplicationContext();
                    
                    //ligar com o mqtt
                    Status status = new Status();
                    String message = status.ligarVermelho();

                    Constantes.mqttClient.publish(Constantes.TOPIC_LIGAR_DESLIGAR_SEMAFORO, message, new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            butttonVermelho.setBackgroundTintList(getColorStateList(R.color.vermelho));
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Toast.makeText(self, "Falhou ao ligar semáforo :(", Toast.LENGTH_LONG).show();

                        }
                    });

                } else {
                    butttonVermelho.setBackgroundTintList(getColorStateList(R.color.cinza));
                }
            }
        });
    }

    private void switchVerde(){
        switchLedVerde.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    butttonVerde.setBackgroundTintList(getColorStateList(R.color.verde));

                } else {

                    butttonVerde.setBackgroundTintList(getColorStateList(R.color.cinza));
                }
            }
        });
    }

    private void switchAmarelo(){
        switchLedAmarelo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    butttonAmarelo.setBackgroundTintList(getColorStateList(R.color.amarelo));

                } else {
                    butttonAmarelo.setBackgroundTintList(getColorStateList(R.color.cinza));
                }
            }
        });
    }

}