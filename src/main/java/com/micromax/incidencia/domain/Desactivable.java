package com.micromax.incidencia.domain;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class Desactivable {

    boolean activa = true;

}
