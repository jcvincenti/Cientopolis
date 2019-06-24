package cientopolis;

public abstract class Pregunta {
	
	protected String descripcionPregunta;
	protected Boolean compuesta;
	protected Boolean esFinal;
	protected Pregunta preguntaSiguiente;
	protected Respuesta respuesta;
	
	public Pregunta(String pregunta, Boolean ultima, Boolean compuesta,Pregunta preguntaSig){
		this.descripcionPregunta = pregunta;
		this.esFinal = ultima;
		this.compuesta = compuesta;
		this.preguntaSiguiente = preguntaSig;
	}
	
	public Pregunta preguntaSiguiente(){
		return this.preguntaSiguiente;
	}
	
	public void setPreguntaSiguiente(Pregunta pregunta){
		this.preguntaSiguiente = pregunta;
		this.esFinal = false;
	}
	
	public String getPregunta(){
		return this.descripcionPregunta;
	}
	
	protected abstract void responder(Respuesta respuesta);
	
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
