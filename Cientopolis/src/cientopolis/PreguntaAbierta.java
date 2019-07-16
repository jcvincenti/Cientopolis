package cientopolis;

public class PreguntaAbierta extends Pregunta implements Respondible{
	
	public PreguntaAbierta (String pregunta, Boolean ultima,Respondible preguntaSig){
		super (pregunta,ultima, false, preguntaSig);
	}
	
	public void responder(Respuesta respuesta){
		this.respuesta = respuesta;
	}
	
	public String getOpciones(){
		return null;
	}
	
	public boolean esFinal(){
		return this.esFinal;
	}
	
}
