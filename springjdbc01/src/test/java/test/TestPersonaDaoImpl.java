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
}
