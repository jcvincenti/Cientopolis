package cientopolis;

import java.util.ArrayList;
import java.util.List;

public class PreguntaMultipleSeleccion extends Pregunta implements Respondible{

	private List <String> opciones;
	
	public PreguntaMultipleSeleccion(String pregunta, Boolean ultima,Respondible preguntaSig,List<String> respuestas) {
		super(pregunta,ultima,false,preguntaSig);
		opciones = new ArrayList <String>();
		opciones.addAll(respuestas);
	}
	
	public void responder(Respuesta respuestas){
		this.respuesta = respuestas;
	}
	
	public List<String> getOpciones(){
		return this.opciones;
	}
	
	public boolean esFinal(){
		return this.esFinal;
	}
}
