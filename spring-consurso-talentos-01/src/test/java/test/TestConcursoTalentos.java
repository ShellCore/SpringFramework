package test;

import static org.junit.Assert.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import concursantes.Concursante;
import concursantes.Malabarista;

public class TestConcursoTalentos {
	
	private static Log logger = LogFactory.getLog("TestConcursoTalentos");
	private Concursante malabarista1;

	@Before
	public void before() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		malabarista1 = (Concursante) context.getBean("solei");
	}
	
	@Test
	public void test() {
		logger.info("Inicio de ejecución de prueba Malabarista");
		
		int numPelotas = 5;
		malabarista1.ejecutar();
		assertEquals(numPelotas, ((Malabarista) malabarista1).getPelotas());
		
		logger.info("Fin de ejecución de prueba Malabarista");
	}

}
