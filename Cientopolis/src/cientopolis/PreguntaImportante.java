package cientopolis;

public class PreguntaImportante extends PreguntaDecorator implements Respondible{

	public PreguntaImportante(Respondible preguntaD, Notificador notificadorD, Encuesta encuestaD) {
		super(preguntaD, notificadorD, encuestaD);
	}

	@Override
	public void responder(Respuesta respuesta) {
		this.pregunta.responder(respuesta);
		this.notificador.notificarObservers(this.encuesta, this.pregunta, respuesta);
	}

}
