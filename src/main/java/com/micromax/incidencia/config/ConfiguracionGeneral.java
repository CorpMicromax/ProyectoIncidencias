package com.micromax.incidencia.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;


@Data
@Service
@ConfigurationProperties
public class ConfiguracionGeneral {

    @Value("${micromax.prefijo.secuencia.incidencias}")
    public String sequencePrefix = "INC_";

    @Value("${micromax.estragegia}")
    public String tipoEstrategia = "auto";

}
