package com.micromax.incidencia.viewmodel;

import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import lombok.Data;

import java.util.List;

@Data
public class DashboardViewmodel {

    private int nuevas = 0;
    private int asignadas = 0;
    private int progreso = 0;
    private int cerradas = 0;
    private int reabiertas = 0;
    private int todas = 0;
    private List<Incidencia> incidencias;

}
