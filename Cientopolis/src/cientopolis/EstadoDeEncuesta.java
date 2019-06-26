package cientopolis;

public abstract class EstadoDeEncuesta {

	public void addPregunta (Respondible pregunta,Respondible preguntaPadre, Encuesta encuesta) throws Excepciones{
		throw new Excepciones(1);
	}
	
	public void comenzarEncuesta(Encuesta encuestaAResponder) throws Excepciones{
		throw new Excepciones(2);
	}
	
	public void responderEncuesta(Respuesta respuesta) throws Excepciones{
		throw new Excepciones(2);
	}
}
