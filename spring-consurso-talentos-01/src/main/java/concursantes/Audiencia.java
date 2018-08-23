package concursantes;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Audiencia {

//	public void sentarse() {
//		System.out.println("El show está por comenzar. Favor de tomar asiento...");
//	}
//	
//	public void apagarCelulares() {
//		System.out.println("Favor de apagar los celulares...");
//	}
//	
//	public void aplaudir() {
//		System.out.println("El show ha terminado. Clap clap clap.");
//	}
//	
//	public void devolucion() {
//		System.out.println("El show fue terrible, se devolverán las entradas.");
//	}
	
	@Pointcut("execution(* concursantes.Concursante.ejecutar(..))")
	public void ejecutarShow() {}
	
	@Around("ejecutarShow()")
	public void monitorearShow(ProceedingJoinPoint joinPoint) {
		try {
			System.out.println("El show esa por comenzar. Favor de tomar asiento...");
			System.out.println("Favor de apagar los celulares...");
			
			// Anotamos la hora de inicio
			long horaInicio = System.currentTimeMillis();

			// Se llama al método de negocio (método objetivo)
			joinPoint.proceed();
			
			// Éste delay en milisegundos es opcional y se puede poner en los métodos
			// de negocio para simular la duración del método
			Thread.sleep(1000); // 1 segundo
			long horaTermino = System.currentTimeMillis();
			
			System.out.println("El show ha terminado. Aplausos.");
			System.out.println("El show tuvo una duración de " + (horaTermino - horaInicio));
			
		} catch (Throwable t) {
			System.out.println("El show fue terrible, se devolverán las entradas.");
		}
	}
}
