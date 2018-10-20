package com.micromax.incidencia.incidencia.domain;

import lombok.Data;

@Data
public class Cliente extends Usuario {

    private String razonSocial;
    private String denominacionComercial;
    private String rif;



}
