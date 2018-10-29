package com.micromax.incidencia.domain;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
@Configuration
public class GeneradorSecuencias implements IdentifierGenerator {

    
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object o) throws HibernateException {
        Connection connection = session.connection();

        try {

            Statement statement=connection.createStatement();

            ResultSet rs=statement.executeQuery("select count(id_incidencia) as Id from incidencia");

            if(rs.next())
            {
                Integer id=rs.getInt(1);
                return Constants.SEQUENCE_PREFIX + String.format("%04d", id);
            }
        } catch (SQLException e) {
            log.debug(e.getSQLState());
        }


        return null;
    }
}
