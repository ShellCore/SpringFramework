package beans;

public class InterpreteEspannol implements Interprete {
	
	@Override
	public void saludar() {
		System.out.println("Hola, mi nombre es");
	}
	
	@Override
	public void despedirse() {
		System.out.println("Hasta pronto...");
	}
}
