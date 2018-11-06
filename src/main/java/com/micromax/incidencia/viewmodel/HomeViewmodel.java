package com.micromax.incidencia.viewmodel;

import com.micromax.incidencia.domain.entities.users.Rol;
import lombok.Data;

@Data
public class HomeViewmodel {


   private String nombre;
   private boolean authenticated;
   private Rol rol;
   private boolean isAdmin;
   private boolean isTech;
   private boolean isClient;
}
