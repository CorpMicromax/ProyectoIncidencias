package com.micromax.incidencia.domain.entities.users;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue("C")
public class Cliente extends Usuario {

    private String razonSocial;
    private String denominacionComercial;

    @Length(max = 10, min = 10)
    private String rif;

}
