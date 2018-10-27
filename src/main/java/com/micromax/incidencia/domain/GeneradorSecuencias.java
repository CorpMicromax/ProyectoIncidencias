package com.micromax.incidencia.domain;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class GeneradorSecuencias implements IdentifierGenerator {

    public static final String SEQUENCE_PREFIX = "Inc-";


    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object o) throws HibernateException {
        String prefix = "DEP";
        Connection connection = session.connection();

        try {

            Statement statement=connection.createStatement();

            ResultSet rs=statement.executeQuery("select count(id_incidencia) as Id from incidencia");

            if(rs.next())
            {
                Integer id=rs.getInt(1)+101;
                return prefix + id.toString();
            }
        } catch (SQLException e) {
            log.debug(e.getSQLState());
        }


        return null;
    }
}
