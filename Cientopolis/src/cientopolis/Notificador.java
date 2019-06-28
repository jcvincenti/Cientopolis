package cientopolis;

import java.util.ArrayList;
import java.util.List;

public class Notificador {
	
	private List<Notificable> observers;
	
	
	public Notificador() {
		observers = new ArrayList<Notificable>();
	}
	
	public void addObserver(Notificable notificable){
		observers.add(notificable);
	}
	
	public void removeObserver(Notificable notificable){
		observers.remove(notificable);
	}
	
	public List<Notificable> getObservers(){
		return this.observers;
	}
	
	public void notificarObservers(Encuesta encuesta, Respondible pregunta, Respuesta respuesta){
		for (Notificable notificable : observers) {
			notificable.notificar(encuesta, pregunta, respuesta);
		}
	}
}
