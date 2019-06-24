package cientopolis;

import java.text.MessageFormat;

public abstract class EstadoDeEncuesta {

	public void addPregunta (Pregunta pregunta,Pregunta preguntaPadre, Encuesta encuesta) {
		throw new IllegalStateException(MessageFormat.format("El estado de la encuesta debe ser 'EnEdicion'. No se puede agregar la pregunta", encuesta));
	}
	
	public void comenzarEncuesta(Encuesta encuestaAResponder){
		throw new IllegalStateException(MessageFormat.format("El estado de la encuesta debe ser 'Activa'. No se puede comenzar la encuesta", encuestaAResponder));
	}
	
	public void responderEncuesta(Respuesta respuesta){
		//throw new IllegalStateException(MessageFormat.format("El estado de la encuesta debe ser 'Activa'. No se puede responder la encuesta", encuesta));
	}
}
