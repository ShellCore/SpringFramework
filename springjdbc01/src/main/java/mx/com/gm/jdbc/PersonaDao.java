package mx.com.gm.jdbc;

import java.util.List;

public interface PersonaDao {

    void insertPersona(Persona persona);
    void updatePersona(Persona persona);
    void deletePersona(Persona persona);

    List<Persona> findAllPersonas();
    Persona findPersonaById(long id);
    Persona findPersonaByEmail(Persona persona);

    int contadorPersonas();
    int contadorPersonasPorNombre(Persona persona);
}
