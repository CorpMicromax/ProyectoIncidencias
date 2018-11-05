package com.micromax.incidencia.viewmodel;

import com.micromax.incidencia.domain.entities.users.Permiso;
import com.micromax.incidencia.domain.entities.users.Rol;
import lombok.Data;

import java.util.List;

@Data
public class HomeViewmodel {


   private String nombre;
   private boolean authenticated;
   private Rol rol;
   private boolean isAdmin;
   List<Permiso> permisos;
}
