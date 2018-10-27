package com.micromax.incidencia.domain.entities.users;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@DiscriminatorValue("T")
@Entity
public class Tecnico extends Usuario implements Serializable {

    @Transient
    private static final long serialVersionUID = 9L;

    @Column(name = "capacidad")
    private int capacidad;
}
