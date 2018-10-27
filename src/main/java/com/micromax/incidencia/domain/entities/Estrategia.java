package com.micromax.incidencia.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Estrategia extends ItemLista  implements Serializable {


}
