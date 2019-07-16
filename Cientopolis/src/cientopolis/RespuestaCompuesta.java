package cientopolis;

import java.util.ArrayList;
import java.util.List;

public class RespuestaCompuesta extends Respuesta {

	List<String> respuestas;
	
	public RespuestaCompuesta(List<String> opciones) {
		super(true);
		respuestas = new ArrayList<String>();
		respuestas = opciones;
	}

	public String getDescripcion(){
		return this.respuestas.toString();
	}
}
