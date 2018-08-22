package prueba;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import concursantes.Concursante;

public class PruebaConcursoTalento {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Concursante malabarista1 = (Concursante) context.getBean("solei");
		malabarista1.ejecutar();
	}
}
