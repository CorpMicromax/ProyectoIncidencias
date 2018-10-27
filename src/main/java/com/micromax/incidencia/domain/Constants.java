package com.micromax.incidencia.domain;

import org.springframework.context.annotation.Configuration;


@Configuration
public class Constants {

    public enum nivel_categoria{
        UNO, DOS, TRES
    }


    public static final String AUTO = "Auto";
    public static final String MANUAL = "Manual";
    public static final String TITLE = "title";
    public static final String DATA = "data";

    public static final String USUARIO = "usuario";
    public static final String INCIDENCIA = "incidencia";
}
