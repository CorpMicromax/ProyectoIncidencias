package com.micromax.incidencia.config;

import com.micromax.incidencia.domain.TipoEstrategia;
import com.micromax.incidencia.viewmodel.ConfigGeneralViewmodel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;


@Data
@Service
@ConfigurationProperties
@PropertySource("classpath:application.properties")
public class ConfiguracionGeneral {

    @Value("${micromax.prefijo.secuencia.incidencias}")
    public String sequencePrefix = "INC_";

    @Value("${micromax.estragegia}")
    public String tipoEstrategia;

    @Value("${spring.mail.username}")
    public String emailNotif;

    @Value("${spring.mail.password}")
    public String claveCorreoAlertas;

    @Value("${micromax.incidencias.cantidadMaxima}")
    public Integer cantidadMaxima;

    public ConfigGeneralViewmodel obtenerViewModel(){
        ConfigGeneralViewmodel vm = new ConfigGeneralViewmodel();
        vm.setEmail_Notif(emailNotif);
        vm.setTipoEstrategia(TipoEstrategia.valueOf(tipoEstrategia));
        vm.setPassword_Notif(claveCorreoAlertas);
        vm.setMaxIncResueltas(cantidadMaxima);
        return vm;
    }

    public void cambiarConfig(ConfigGeneralViewmodel vm) {
        tipoEstrategia = vm.getTipoEstrategia().toString().toLowerCase();
        emailNotif = vm.getEmail_Notif();
        claveCorreoAlertas = vm.getPassword_Notif();
        cantidadMaxima = vm.getMaxIncResueltas();
    }
}
