package com.micromax.incidencia.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Locale;


@EnableConfigurationProperties
@Data
@Configuration
@ConfigurationProperties
public class Constants {

    public enum nivel_categoria{
        UNO, DOS, TRES
    }

    public static final SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy, hh:mm a", new Locale("es_ES"));

    public static final String AUTO = "Auto";
    public static final String MANUAL = "Manual";
    public static final String TITLE = "title";
    public static final String DATA = "data";
    public static final String ADMINROLE = "ROLE_ADMIN";
    public static final String TECHROLE = "ROLE_TECH";
    public static final String CLIENTROLE = "ROLE_CLIENT";

    public static final String USUARIO = "usuario";
    public static final String INCIDENCIA = "incidencia";
}
