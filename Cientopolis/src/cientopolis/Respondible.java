package cientopolis;

public interface Respondible{
	public void responder(Respuesta respuesta);
	
	public boolean esFinal();
	public String getPregunta();
	public Respondible preguntaSiguiente();
	public void setPreguntaSiguiente(Respondible pregunta);
}
