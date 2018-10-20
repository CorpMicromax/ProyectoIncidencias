package com.micromax.incidencia.incidencia.domain.entities.users;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue("C")
public class Cliente extends Usuario {

    private String razonSocial;
    private String denominacionComercial;
    private String rif;

}
