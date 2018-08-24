package mx.com.gm.jdbc;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonaRowMapper implements RowMapper<Persona> {

    /**
     * Método que es llamado por la plantilla de Spring.
     * Éste es un método Callback
     */
    public Persona mapRow(ResultSet resultSet, int i) throws SQLException {
        // Creación del objeto Persona por cada registro encontrado por el resultSet
        Persona persona = new Persona();
        persona.setId(resultSet.getLong("id"));
        persona.setNombre(resultSet.getString("nombre"));
        persona.setPaterno(resultSet.getString("paterno"));
        persona.setMaterno(resultSet.getString("materno"));
        persona.setEmail(resultSet.getString("email"));
        return persona;
    }
}
