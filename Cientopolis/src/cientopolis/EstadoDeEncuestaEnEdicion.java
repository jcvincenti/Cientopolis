package cientopolis;

public class EstadoDeEncuestaEnEdicion extends EstadoDeEncuesta {
	//cuando una encuesta est� en edicion, SOLO se le pueden agregar preguntas. 

	@Override
	public void addPregunta (Respondible pregunta, Respondible preguntaPadre,Encuesta encuesta) {
		preguntaPadre.setPreguntaSiguiente(pregunta);
	}
}
