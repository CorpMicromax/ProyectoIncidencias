package com.micromax.incidencia.domain;

public enum Status {

    NUEVA("Nueva"), //0
    ASIGNADA("Asignada"), //1
    PROGRESO("Progreso"), //2
    RESUELTA("Resuelta"), //3
    CERRADA("Cerrada"), //4
    REABIERTA("Reabierta"), //5
    PAUSADA("Pausada"); //6

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


}

