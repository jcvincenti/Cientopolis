package cientopolis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SimpleSeleccion extends Pregunta {

	private Map <Respuesta,Pregunta> opciones;
	
	public SimpleSeleccion(String pregunta, Map<Respuesta,Pregunta> opciones,Pregunta preguntaSig) {
		super(pregunta,false,true,null);
		this.opciones = new HashMap <Respuesta,Pregunta>();
		this.opciones.putAll(opciones);
	}
	
	public void responder (Respuesta respuesta){
		this.respuesta = respuesta;
		this.preguntaSiguiente = this.opciones.get(respuesta);
	}
	
	public Set<Respuesta> getOpciones(){
		return this.opciones.keySet();
	}
	
}
