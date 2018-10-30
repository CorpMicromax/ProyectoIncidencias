package com.micromax.incidencia.domain;

import org.springframework.util.StringUtils;

public enum Status {

    NUEVA, ABIERTA, ASIGNADA, PROGRESO, CERRADA, REABIERTA, PAUSADA;

    @Override
    public String toString() {
        return StringUtils.capitalize(super.toString());
    }

    public String textName(){
        return StringUtils.capitalize(super.toString().toLowerCase());
    }
}

