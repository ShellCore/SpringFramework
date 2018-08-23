package concursantes;

import org.springframework.stereotype.Component;

@Component
public class Voluntario implements Pensador {
	
	private String pensamientos;

	@Override
	public void pensarEnAlgo(String pensamiento) {
		System.out.println("Ejecutando el método pensarEnAlgo()");
		this.pensamientos = pensamiento;
	}

	public String getPensamiento() {
		return pensamientos;
	}
}
