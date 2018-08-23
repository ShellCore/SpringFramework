package test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:datasource-test.xml"})
public class TestJdbc {

    private static Log logger = LogFactory.getLog("TestJdbc");

    @Autowired
    JdbcTemplate template;

    @Test
    public void testJdbc() {
        logger.info("Inicio del test JDBC");

        String sqlQuery = "SELECT COUNT(*) FROM persona";
        int numPersonas = template.queryForObject(sqlQuery, null, Integer.class);

        logger.info("Número de personas: " + numPersonas);

        assertEquals(3, numPersonas);

        logger.info("Fin del test JDBC");
    }
}
