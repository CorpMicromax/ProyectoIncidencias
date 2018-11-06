package com.micromax.incidencia.domain.entities;

import org.springframework.util.StringUtils;

public enum TipoCambio {

    CREACION_INCIDENCIA, EDICION_INCIDENCIA, ELIMINACION_INCIDENCIA, CAMBIO_STATUS, COMENTARIO, CAMBIO_STATUS_COMENTARIO;

    @Override
    public String toString() {
        return StringUtils.capitalize(super.toString());
    }
}
