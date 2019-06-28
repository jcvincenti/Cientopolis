package cientopolis;

public class EnEdicion extends EstadoDeEncuesta {
	//cuando una encuesta está en edicion, SOLO se le pueden agregar preguntas. 

	@Override
	public void addPregunta (Respondible pregunta, Respondible preguntaPadre,Encuesta encuesta) {
		preguntaPadre.setPreguntaSiguiente(pregunta);
	}
}
