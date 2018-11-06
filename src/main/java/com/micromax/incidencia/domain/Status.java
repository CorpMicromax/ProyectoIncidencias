package com.micromax.incidencia.domain;

public enum Status {

    NUEVA("Nueva"),
    ABIERTA("Abierta"),
    ASIGNADA("Asignada"),
    PROGRESO("Progreso"),
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

