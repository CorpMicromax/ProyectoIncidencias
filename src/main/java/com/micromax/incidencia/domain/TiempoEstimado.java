package com.micromax.incidencia.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.util.StringUtils;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
@Slf4j
public class TiempoEstimado {

    private int dias = 0;
    private int hora = 0;
    private int minutos = 0;
    private Date creacion;

    public TiempoEstimado() {
    }

    public TiempoEstimado(int horas) {
        dias = horas / 24;
        hora = horas % 24;
    }

    public TiempoEstimado(Date creacion, String ti) {
        if (creacion != null) this.creacion = Date.from(creacion.toInstant());
        if (!StringUtils.isEmpty(ti)) {
            String s[] = ti.split("-");
            dias = Integer.valueOf(s[0]);
            hora = Integer.valueOf(s[1]);
            minutos = Integer.valueOf(s[2]);
        } else {
            dias = 0;
            hora = 0;
            minutos = 0;
        }

    }

    public TiempoEstimado(int horas, int minutos) {
        hora = horas + (minutos / 60);
        dias = horas / 24;
        hora = horas % 24;
        minutos = minutos % 60;
    }

    public TiempoEstimado(int dias, int horas, int minutos) {
        hora = horas + (minutos / 60);
        dias = dias + (horas / 24);
        hora = horas % 24;
        minutos = minutos % 60;
    }

    @Override
    public String toString() {
        return String.format("%d-%d-%d", dias, hora, minutos);
    }

    public String getEntregaEstimada() {

        try {

            LocalDateTime ll = LocalDateTime.ofInstant(creacion.toInstant(), ZoneId.systemDefault());
            return ll.plusDays(dias).plusHours(hora).plusMinutes(minutos).format(Constants.formatter);
        } catch (DateTimeException e) {
            log.info("Falle convirtiendo fechas");
            return null;
        }
    }

}
