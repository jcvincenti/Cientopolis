package cientopolis;

public abstract class Pregunta implements Respondible{
	
	protected String descripcionPregunta;
	protected Boolean compuesta;
	protected Boolean esFinal;
	protected Respondible preguntaSiguiente;
	protected Respuesta respuesta;
	
	public Pregunta(String pregunta, Boolean ultima, Boolean compuesta,Respondible preguntaSig){
		this.descripcionPregunta = pregunta;
		this.esFinal = ultima;
		this.compuesta = compuesta;
		this.preguntaSiguiente = preguntaSig;
	}
	
	public Respondible preguntaSiguiente(){
		return this.preguntaSiguiente;
	}
	
	public void setPreguntaSiguiente(Respondible pregunta){
		this.preguntaSiguiente = pregunta;
		this.esFinal = false;
	}
	
	public String getPregunta(){
		return this.descripcionPregunta;
	}
	
	public abstract void responder(Respuesta respuesta);
	
	public Boolean getTipoDePregunta(){
		return this.compuesta;
	}
	
	public Boolean esUltimaPregunta(){
		return this.esFinal;
	} 
	
	public String getRespuesta(){
		return this.respuesta.getDescripcion();
	}
}
