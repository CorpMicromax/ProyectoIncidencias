package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.FechasDTO;
import com.micromax.incidencia.repository.ReportDAO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@Service
public class ReportService {


    @Autowired
    private ReportDAO reportDAO;

    public JasperPrint exportPdfFile(Integer id, FechasDTO fechas) throws SQLException, JRException, IOException {
        return reportDAO.exportPdfFile(id, fechas);
    }
}
