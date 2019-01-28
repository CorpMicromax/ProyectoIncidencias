package com.micromax.incidencia.domain;

public enum TipoEstrategia {

    manual("Manual"),
    auto("Automática");

    private final String displayName;

    TipoEstrategia(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
