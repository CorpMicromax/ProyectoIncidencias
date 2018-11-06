package com.micromax.incidencia.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.thymeleaf.util.StringUtils;

import java.util.Date;

@Data
@Slf4j
public class TiempoEstimado {

    private int dias = 0;
    private int hora = 0;
    private int minutos = 0;
    private Date creacion;
    private Date entrega;

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
            entrega = getFechaEntregaEstimada();
        } else {
            dias = 0;
            hora = 0;
            minutos = 0;
            entrega = DateUtils.addHours(new Date(),2);
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

    public Date getFechaEntregaEstimada(){
        Date estimado = creacion;
        estimado = DateUtils.addDays(estimado,dias);
        estimado = DateUtils.addHours(estimado,hora);
        estimado = DateUtils.addMinutes(estimado, minutos);
        return estimado;
    }

    public String entregaFormateada(){
        return Constants.formateador.format(entrega);
    }

    public String entregaFormateadaCorta(){
        return Constants.formateadorCorto.format(entrega);
    }

}
