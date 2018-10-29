package com.micromax.incidencia.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties
public class Constants {

    public enum nivel_categoria{
        UNO, DOS, TRES
    }

    @Value("${micromax.prefijo.secuencia.incidencias}")
    public static final String SEQUENCE_PREFIX = "INC_";

    @Value("${micromax.estragegia}")
    public static final String TIPO_ESTRATEGIA = "manual";


    public static final String AUTO = "Auto";
    public static final String MANUAL = "Manual";
    public static final String TITLE = "title";
    public static final String DATA = "data";

    public static final String USUARIO = "usuario";
    public static final String INCIDENCIA = "incidencia";
}
