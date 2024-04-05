package com.example.semaforo;

public class Status {

    public String ligarSemaforo(){
        return "{\"status\": \"ligado\"}";
    }
    public String desligarSemaforo(){
        return "{\"status\": \"desligado\"}";
    }


    public String ligarVermelho(){
        return "{\"status\": \"vermelho_ligado\"}";
    }

    public String ligarVerde(){
        return "{\"status\": \"verde_ligado\"}";
    }
    public String ligarAmarelo(){
        return "{\"status\": \"amarelo_ligado\"}";
    }


    public String desligarVermelho(){
        return "{\"status\": \"vermelho_desligado\"}";
    }
}
