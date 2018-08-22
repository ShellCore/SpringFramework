package prueba;

import beans.InterpreteEspannol;
import beans.Traductor;

public class PruebaInterprete {
	
	public static void main(String[] args) {
		Traductor traductor = new Traductor();
		InterpreteEspannol interprete = new InterpreteEspannol();
		
		// El intérprete se inyecta manualmente, y solo puede recibir un
		// intérprete en español, no un intérprete en inglés u otros idiomas
		
		traductor.setInterprete(interprete);
		traductor.setNombre("Carlos Esparza");
		traductor.hablar();
	}
}
