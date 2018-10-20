package com.micromax.incidencia.domain;

public class TiempoEstimado {

    private int dias;
    private int hora;
    private int minutos;

    TiempoEstimado(int horas){
        dias = horas / 24;
        hora = horas % 24;
    }

    TiempoEstimado(int horas, int minutos){
        hora = horas + (minutos / 60);
        dias = horas / 24;
        hora = horas % 24;
        minutos = minutos % 60;
    }

    @Override
    public String toString() {
        return String.format("%d-%d-%d", dias, hora, minutos);
    }
}
