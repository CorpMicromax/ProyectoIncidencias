package com.micromax.incidencia.viewmodel;

import com.micromax.incidencia.domain.TipoEstrategia;
import lombok.Data;
import javax.validation.constraints.Email;
import java.util.EnumSet;

@Data


public class ConfigGeneralViewmodel {
    private EnumSet<TipoEstrategia> tipoEstrategia = EnumSet.allOf(TipoEstrategia.class);
    private int maxIncResueltas;
    @Email
    private String email_Notif;
    private String password_Notif;
}
