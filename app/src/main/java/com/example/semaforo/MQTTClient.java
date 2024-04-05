package com.example.semaforo;


import android.content.Context;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQTTClient {

    private MqttAndroidClient mqttAndroidClient;

    public MQTTClient(Context context, String serverURI) {
        this.mqttAndroidClient = new MqttAndroidClient(context, serverURI, MqttClient.generateClientId());
    }

    public void connect(String username, String password, IMqttActionListener cbConnect){
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());

        try {
            this.mqttAndroidClient.connect(options, null, cbConnect);
        } catch (MqttException e) {
            e.printStackTrace();
        }


    }

    public void disconnect(String userMqtt, String passwordMqtt, IMqttActionListener cbDesconnect){
        try {
            this.mqttAndroidClient.disconnect(null, cbDesconnect);
        }catch (MqttException e){
            e.printStackTrace();
        }

    }

    public void publish(String topic, String msg, IMqttActionListener cbPublish){
        try {
            MqttMessage message = new MqttMessage();
            message.setPayload(msg.getBytes());
            message.setQos(Constantes.QOS_MQTT);
            message.setRetained(Constantes.RETAINED_MQTT);
            mqttAndroidClient.publish(topic,message, null, cbPublish);
        }catch (MqttException e){
            e.printStackTrace();

        }
    }


}
