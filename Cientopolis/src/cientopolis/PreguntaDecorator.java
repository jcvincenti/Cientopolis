package cientopolis;

public abstract class PreguntaDecorator{
	
	protected Respondible pregunta;
	protected Notificador notificador;
	protected Encuesta encuesta;
	
	public PreguntaDecorator(Respondible preguntaD, Notificador notificadorD, Encuesta encuestaD){
		this.pregunta = preguntaD;
		this.notificador = notificadorD;
		this.encuesta = encuestaD;
	}
	
	public boolean esFinal(){
		return this.pregunta.esFinal();
	}
	
	public String getPregunta(){
		return this.pregunta.getPregunta();
	}
	
	public Respondible preguntaSiguiente(){
		return this.pregunta.preguntaSiguiente();
	}
	
	public void setPreguntaSiguiente(Respondible pregunta){
		this.pregunta.setPreguntaSiguiente(pregunta);
	}
	
}
