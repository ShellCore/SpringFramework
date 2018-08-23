package test;

import static org.junit.Assert.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import concursantes.Adivinador;
import concursantes.Pensador;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestMago {

	private static Log logger = LogFactory.getLog("TestMago");
	
	@Autowired
	private Pensador voluntario;
	@Autowired
	private Adivinador mago;
	
	@Test
	public void test() {
		logger.info("Inicio de la adivinanza...");
		
		voluntario.pensarEnAlgo("Hoy ganaré la lotería!");
		
		assertEquals("Hoy ganaré la lotería!", mago.getPensamientos());
		
		logger.info("Fin de la adivinanza...");
	}

}
