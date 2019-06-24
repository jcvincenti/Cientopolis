package cientopolis;

import java.util.ArrayList;
import java.util.List;

public class MultipleSeleccion extends Pregunta {

	private List <String> opciones;
	
	public MultipleSeleccion(String pregunta, Boolean ultima,Pregunta preguntaSig,List<String> respuestas) {
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
}
