package com.micromax.incidencia.domain;

public enum Status {

    NUEVA("Nueva"),
    ASIGNADA("Asignada"),
    PROGRESO("Progreso"),
    RESUELTA("Resuelta"),
    CERRADA("Cerrada"),
    REABIERTA("Reabierta"),
    PAUSADA("Pausada");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


}

