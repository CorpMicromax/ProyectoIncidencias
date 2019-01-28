package com.micromax.incidencia.viewmodel;

import com.micromax.incidencia.domain.TipoEstrategia;
import lombok.Data;

import javax.validation.constraints.Email;
import java.util.EnumSet;

@Data
public class ConfigGeneralViewmodel {

    private EnumSet<TipoEstrategia> tiposEstrategia = EnumSet.allOf(TipoEstrategia.class);

    private TipoEstrategia tipoEstrategia;
    private int maxIncResueltas;

    @Email
    private String email_Notif;

    private String password_Notif;
}
