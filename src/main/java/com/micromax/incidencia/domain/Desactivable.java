package com.micromax.incidencia.domain;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class Desactivable {

    boolean habilitado = true;

}
