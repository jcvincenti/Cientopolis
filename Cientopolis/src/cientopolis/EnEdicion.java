package cientopolis;

public class EnEdicion extends EstadoDeEncuesta {
	//cuando una encuesta está en edicion, SOLO se le pueden agregar preguntas. 

	@Override
	public void addPregunta (Pregunta pregunta, Pregunta preguntaPadre,Encuesta encuesta) {
		encuesta.agregarPregunta(pregunta, preguntaPadre);
	}
}
