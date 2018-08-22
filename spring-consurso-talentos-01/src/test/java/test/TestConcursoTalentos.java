package test;

import static org.junit.Assert.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import concursantes.Concursante;
import concursantes.Malabarista;
import concursantes.Musico;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestConcursoTalentos {
	
	private static Log logger = LogFactory.getLog("TestConcursoTalentos");
	@Autowired
	@Qualifier("solei")
	private Concursante malabarista1;
	@Autowired
	@Qualifier("soleiRecitador")
	private Concursante malabarista2;
	@Autowired
	@Qualifier("jasonPiano")
	private Concursante musico1;
	@Autowired
	@Qualifier("jasonSax")
	private Concursante musico2;

	@Test
	public void test() {
		testMalabarista();
		testMalabaristaRecitador();
		testMusicos();
	}
	
	private void testMalabarista() {
		logger.info("Inicio de ejecución de prueba Malabarista");
		
		int numPelotas = 10;
		malabarista1.ejecutar();
		assertEquals(numPelotas, ((Malabarista) malabarista1).getPelotas());
		
		logger.info("Fin de ejecución de prueba Malabarista");
	}
	
	private void testMalabaristaRecitador() {
		logger.info("Inicio de ejecución de prueba Malabarista Recitador");
		
		int numPelotas = 15;
		malabarista2.ejecutar();
		assertEquals(numPelotas, ((Malabarista) malabarista2).getPelotas());
		
		logger.info("Fin de ejecución de prueba Malabarista Recitador");
	}
	
	private void testMusicos() {
		logger.info("Inicio de ejecución de prueba Músicos");
		
		logger.info("Pianista...");
		String cancion = "Noche de Paz";
		musico1.ejecutar();
		assertEquals(cancion, ((Musico) musico1).getCancion());
		
		logger.info("Saxofonista...");
		cancion = "Equinox";
		musico2.ejecutar();
		assertEquals(cancion, ((Musico) musico2).getCancion());
		
		logger.info("Inicio de ejecución de prueba Músicos");
	}
}
