package beans;

public class InterpreteIngles implements Interprete {

	@Override
	public void saludar() {
		System.out.println("Hello, my name is");
	}

	@Override
	public void despedirse() {
		System.out.println("Bye...");
		
	}

}
