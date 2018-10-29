package com.micromax.incidencia.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@ConfigurationProperties
@Data
@Configuration
public class Constants {

    public enum nivel_categoria{
        UNO, DOS, TRES
    }

    @Value("${micromax.prefijo.secuencia.incidencias}")
    private final String SEQUENCE_PREFIX = "INC_";

    @Value("${micromax.estragegia}")
    private final String TIPO_ESTRATEGIA = "manual";


    public static final String AUTO = "Auto";
    public static final String MANUAL = "Manual";
    public static final String TITLE = "title";
    public static final String DATA = "data";

    public static final String USUARIO = "usuario";
    public static final String INCIDENCIA = "incidencia";
}
