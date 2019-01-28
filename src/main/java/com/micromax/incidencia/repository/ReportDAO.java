package com.micromax.incidencia.repository;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Transactional
@Repository
public class ReportDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    public JasperPrint exportPdfFile(Integer id) throws SQLException, JRException, IOException {
        try {
            String path = "";
            if(id == 1){
                path = resourceLoader.getResource("classpath:reports/horas_por_tecnico.jrxml").getURI().getPath();
            }
            if(id == 2){
                path = resourceLoader.getResource("classpath:reports/TiempoIncidencias.jrxml").getURI().getPath();
            }
            if(id == 3){
                path = resourceLoader.getResource("classpath:reports/ReporteActividad.jrxml").getURI().getPath();
            }
            JasperReport jasperReport = JasperCompileManager.compileReport(path);

            // Parameters for report
            Map<String, Object> parameters = new HashMap<>();

            return JasperFillManager.fillReport(jasperReport, parameters, Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection());
        }catch(NullPointerException e){

            System.out.print(e.getMessage());
        }
        return null;
    }
}
