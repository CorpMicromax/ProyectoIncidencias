package com.micromax.incidencia.incidencia.domain.entities.users;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@DiscriminatorValue("T")
@Entity
public class Tecnico extends Usuario {
    private int capacidad;
}
