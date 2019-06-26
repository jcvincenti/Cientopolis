package cientopolis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SimpleSeleccion extends Pregunta implements Respondible {

	private Map <Respuesta,Respondible> opciones;
	
	public SimpleSeleccion(String pregunta, Map<Respuesta,Respondible> opciones,Respondible preguntaSig) {
		super(pregunta,false,true,null);
		this.opciones = new HashMap <Respuesta,Respondible>();
		this.opciones.putAll(opciones);
	}
	
	public void responder (Respuesta respuesta){
		this.respuesta = respuesta;
		this.preguntaSiguiente = this.opciones.get(respuesta);
	}
	
	public Set<Respuesta> getOpciones(){
		return this.opciones.keySet();
	}
	
	public boolean esFinal(){
		return this.esFinal;
	}
	
}
