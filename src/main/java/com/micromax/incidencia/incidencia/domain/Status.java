package com.micromax.incidencia.incidencia.domain;

import org.springframework.util.StringUtils;

public enum Status {
    NUEVA, ABIERTA, CERRADA, PROGRESO, ASIGNADA, REABIERTA, PAUSADA;



    @Override
    public String toString() {
        return StringUtils.capitalize(super.toString().replace("_"," "));
    }
}
