package mx.com.gm.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PersonaDaoImpl implements PersonaDao {

    // Queries
    // Query con parámetros por nombre
    private static final String SQL_INSERT_PERSONA = "INSERT INTO persona (nombre, paterno, materno, email) VALUES (:nombre, :paterno, :materno, :email)";
    private static final String SQL_UPDATE_PERSONA = "UPDATE persona SET nombre = :nombre, paterno = :paterno, materno = :materno, email = :email WHERE id = :id";
    private static final String SQL_DELETE_PERSONA = "DELETE FROM persona WHERE id = :id";
    private static final String SQL_SELECT_PERSONA = "SELECT id, nombre, paterno, materno, email FROM persona";
    private static final String SQL_SELECT_PERSONA_BY_ID = SQL_SELECT_PERSONA + " WHERE id = :id";
    // Query con parámetros por índice
    private static final String SQL_INSERT_PERSONA_I = "INSERT INTO persona (nombre, paterno, materno, email) VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_PERSONA_BY_ID_I = SQL_SELECT_PERSONA + " WHERE id = ?";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        // No es común que se utilicen las 2 plantillas, sin embargo si es posible.
        // La diferencia es el manejo de parámetros por índice o por nombre.
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void insertPersona(Persona persona) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(persona);
        namedParameterJdbcTemplate.update(SQL_INSERT_PERSONA, parameterSource);
    }

    public void updatePersona(Persona persona) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(persona);
        namedParameterJdbcTemplate.update(SQL_UPDATE_PERSONA, parameterSource);
    }

    public void deletePersona(Persona persona) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(persona);
        namedParameterJdbcTemplate.update(SQL_DELETE_PERSONA, parameterSource);
    }

    public List<Persona> findAllPersonas() {
        // Ésta consulta es equivalente a SELECT * FROM persona
        RowMapper<Persona> personaRowMapper = BeanPropertyRowMapper.newInstance(Persona.class);
        return jdbcTemplate.query(SQL_SELECT_PERSONA, personaRowMapper);
    }

    public Persona findPersonaById(long id) {
        Persona persona;

        try {
            // Utilizamos la clase PersonaRawMapper
            persona = jdbcTemplate.queryForObject(SQL_SELECT_PERSONA_BY_ID_I, new PersonaRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            persona = null;
        }

        return persona;

        // Ésta es otra forma de utilizar la clase PersonaRawWrapper
//        BeanPropertyRowMapper<Persona> personaRowMapper = BeanPropertyRowMapper.newInstance(Persona.class);
//        return jdbcTemplate.queryForObject(SQL_SELECT_PERSONA_BY_ID, personaRowMapper, id);
    }

    public Persona findPersonaByEmail(Persona persona) {
        String sql = "SELECT * FROM persona WHERE email = :email";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(persona);

        // Si no se tiene el objeto RowMapper, se puede utilizar la siguiente línea para crear éste objeto
//        RowMapper<Persona> personaRowMapper = BeanPropertyRowMapper.newInstance(Persona.class);

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new PersonaRowMapper());
    }

    public int contadorPersonas() {
        String sql = "SELECT COUNT(*) FROM persona";
        return jdbcTemplate.queryForObject(sql, Integer.class);

        // Esta es la otra opción si no tuvieramos jdbcTemplate
//        return namedParameterJdbcTemplate.getJdbcOperations()
//                .queryForObject(sql, Integer.class);
    }

    public int contadorPersonasPorNombre(Persona persona) {
        String sql = "SELECT COUNT(*) FROM persona WHERE nombre = :nombre";

        // Permite evitar crear un MAP de parámetros y utilizar directamente el objeto Persona.
        // Los atributos que coincidan con el nombre de los parámetros por nombre del query
        // serán utilizados y proporcionados como atributos al query
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(persona);

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }
}
