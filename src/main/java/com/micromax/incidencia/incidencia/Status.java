package com.micromax.incidencia.incidencia;

public enum Status {
    NEW, CLOSED, IN_PROGRESS, ASSIGNED;



    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
