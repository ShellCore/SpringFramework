package concursantes;

public class Soneto implements Poema {

	@Override
	public void recitar() {
		String sonetoPasionSorJuana = "Éste que ves, engaño colorido, \n"
				+ "que, del arte ostentando los primores, \n"
				+ "con falsos silogismos de colores \n"
				+ "es cauteloso engaño del sentido; \n"
				+ "\n"
				+ "éste en quien la lisonja ha pretendido \n"
				+ "excusar de los años los horrores \n"
				+ "y venciendo del tiempo los rigores \n"
				+ "triunfar de la vejez y del olvido: \n"
				+ "\n"
				+ "es un vano artificio del cuidado; \n"
				+ "es una flor al viento delicada; \n"
				+ "es un resguardo inútil para el hado; \n"
				+ "\n"
				+ "es una necia diligencia errada; \n"
				+ "es un afán caduco, y, bien mirado, \n"
				+ "es cadáver, es polvo, es sombra, es nada.";

		System.out.println("\nSoneto: \n" + sonetoPasionSorJuana + "\n");
	}

}
