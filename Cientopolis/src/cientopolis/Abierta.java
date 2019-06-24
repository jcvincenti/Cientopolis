package cientopolis;

public class Abierta extends Pregunta{
	
	public Abierta (String pregunta, Boolean ultima,Pregunta preguntaSig){
		super (pregunta,ultima, false, preguntaSig);
	}
	
	public void responder(Respuesta respuesta){
		this.respuesta = respuesta;
	}
	
	public String getOpciones(){
		return null;
	}
	
}
