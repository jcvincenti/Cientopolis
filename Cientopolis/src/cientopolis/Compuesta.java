package cientopolis;

import java.util.ArrayList;
import java.util.List;

public class Compuesta extends Respuesta {

	List<String> respuestas;
	
	public Compuesta(List<String> opciones) {
		super(true);
		respuestas = new ArrayList<String>();
		respuestas = opciones;
	}

	public String getDescripcion(){
		return this.respuestas.toString();
	}
}
