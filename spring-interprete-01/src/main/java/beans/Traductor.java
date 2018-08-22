package beans;

public class Traductor {
	
	private Interprete interprete;
	private String nombre;
	
	public void hablar() {
		interprete.saludar();
		System.out.println(nombre);
		interprete.despedirse();
	}

	public Interprete getInterprete() {
		return interprete;
	}

	public void setInterprete(Interprete interprete) {
		this.interprete = interprete;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
