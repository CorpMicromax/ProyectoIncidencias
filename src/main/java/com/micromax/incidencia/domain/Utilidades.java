package com.micromax.incidencia.domain;

import com.micromax.incidencia.domain.entities.incidencias.Incidencia;

import java.util.Date;

public class Utilidades {

    public static boolean retrasada(Incidencia i){
        if(i.getTiempoEstimado() != null){
            Date ahora = new Date();
            Date fechaEstimada = i.getTiempoEstimado().getEntrega();
            return (ahora.after(fechaEstimada) || ahora.equals(fechaEstimada));
        }
        return false;
    }

}
