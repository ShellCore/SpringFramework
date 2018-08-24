package test;

import mx.com.gm.jdbc.Persona;
import mx.com.gm.jdbc.PersonaDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:datasource-test.xml",
        "classpath:applicationContext.xml"})
public class TestPersonaDaoImpl {

    private static Log logger = LogFactory.getLog("TestPersonaDaoImpl");

    @Autowired
    private PersonaDao personaDao;

    @Test
    public void testMostrarPersonas() {
        try {
            System.out.println();
            logger.info("Inicio del test testMostrarPersonas");

            List<Persona> personas = personaDao.findAllPersonas();

            personas.forEach(p -> logger.info(p));

            // Segun el número de personas recuperadas, debería ser el mismo de la tabla
            assertEquals(personas.size(), personaDao.contadorPersonas());

            logger.info("Fin del test testMostrarPersonas");
        } catch (Exception e) {
            logger.error("Error JDBC", e);
        }
    }

    @Test
    public void testContarPersonasPorNombre() {
        try {
            System.out.println();
            logger.info("Inicio del test testContarPersonasPorNombre");

            String nombre = "Juan";
            Persona personaEjemplo = new Persona();
            personaEjemplo.setNombre(nombre);

            int numPersonasEncontradas = personaDao.contadorPersonasPorNombre(personaEjemplo);
            logger.info("Número de personas encontradas con nombre " + nombre + ": " + numPersonasEncontradas);
            assertEquals(2, numPersonasEncontradas);

            logger.info("Fin del test testContarPersonasPorNombre");
        } catch (Exception e) {
            logger.error("Error JDBC", e);
        }
    }

    @Test
    public void testEncontrarPersonaPorId() {
        try {
            System.out.println();
            logger.info("Inicio del test testEncontrarPersonaPorId");

            int id = 1;
            Persona persona = personaDao.findPersonaById(id);

            // Segun la persona recuperada, debería ser la misma que el registro 1
            assertEquals("Admin", persona.getNombre());

            // Mostramos el objeto recuperado
            logger.info("Persona recuperada (id = " + id + "): " + persona);

            logger.info("Fin del test testEncontrarPersonaPorId");
        } catch (Exception e) {
            logger.error("Error JDBC", e);
        }
    }

    @Test
    public void testAgregarPersona() {
        try {
            System.out.println();
            logger.info("Inicio del test testAgregarPersona");

            // El script de datos tiene 3 registros
            assertEquals(3, personaDao.contadorPersonas());

            Persona persona = new Persona();
            persona.setNombre("Carlos");
            persona.setPaterno("Romero");
            persona.setMaterno("Esparza");
            persona.setEmail("cromero@mail.com");

            personaDao.insertPersona(persona);

            // Recuperamos a la persona recien insertada por su email
            persona = personaDao.findPersonaByEmail(persona);

            logger.info("Persona recuperada: " + persona);

            // Debería haber ya 4 personas registradas
            assertEquals(4, personaDao.contadorPersonas());

            logger.info("Fin del test testAgregarPersona");
        } catch (Exception e) {
            logger.error("Error JDBC", e);
        }
    }

    @Test
    public void testActualizarPersona() {
        try {
            System.out.println();
            logger.info("Inicio de test testActualizarPersona");

            int id = 1;
            Persona persona = personaDao.findPersonaById(id);
            logger.info("Persona a modificar (id = " + id + "): " + persona);

            // Actualizamos el nombre y apellido materno
            persona.setNombre("Administrador");
            persona.setMaterno("Sistemas");
            personaDao.updatePersona(persona);

            // Volvemos a leer la persona
            persona = personaDao.findPersonaById(id);

            // Segun la persona recuperada, debería ser la misma que el registro 1
            assertEquals("Administrador", persona.getNombre());

            // Mostramos la persona recuperada
            logger.info("Persona modificada (id = " + id + "): " + persona);

            logger.info("Fin de test testActualizarPersona");
        } catch (Exception e) {
            logger.error("Error JDBC", e);
        }
    }

    @Test
    public void testEliminarPersona() {
        try {
            System.out.println();
            logger.info("Inicio de test testEliminarPersona");

            // Buscamos eliminar la persona con id = 2
            int id = 2;

            Persona persona = personaDao.findPersonaById(id);

            logger.info("Persona a  eliminar (id = " + id + "): " + persona);

            // Eliminamos la persona recuperada
            personaDao.deletePersona(persona);

            persona = personaDao.findPersonaById(id);

            // Debería regresar nulo al buscar a la persona con id = 2
            assertNull(persona);

            // Mostramos toda la lista de personas registradas
            logger.info("Nuevo listado de personas");

            List<Persona> personas = personaDao.findAllPersonas();

            personas.forEach(p -> logger.info(p));

            // Segun el número de personas recuperadas, debería ser el mismo de la tabla
            assertEquals(personaDao.contadorPersonas(), personas.size());

            logger.info("Fin de test testEliminarPersona");
        } catch (Exception e) {
            logger.error("Error JDBC", e);
        }
    }
}
