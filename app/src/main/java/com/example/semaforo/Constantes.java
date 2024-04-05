package com.example.semaforo;

public class Constantes {
    public static final String USER_MQTT = "";
    public static final String PASSWORD_MQTT = "";
    public static final  int QOS_MQTT = 1;
    public static final boolean RETAINED_MQTT = false;

    public static MQTTClient mqttClient;
    public static String TOPIC_LIGAR_DESLIGAR_SEMAFORO = "status_semaforo/13";
}
