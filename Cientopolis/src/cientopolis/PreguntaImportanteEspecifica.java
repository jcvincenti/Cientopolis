package cientopolis;

public class PreguntaImportanteEspecifica extends PreguntaDecorator implements Respondible{
	
	private Respuesta respuesta;
	
	public PreguntaImportanteEspecifica(Respondible preguntaD, Notificador notificadorD, Encuesta encuestaD,Respuesta respuestaD) {
		super(preguntaD, notificadorD, encuestaD);
		this.respuesta = respuestaD;
	}

	@Override
	public void responder(Respuesta respuestaD) {
		this.pregunta.responder(respuestaD);
		if (this.respuesta == respuestaD){
			this.notificador.notificarObservers(this.encuesta, this.pregunta, respuestaD);
		}
	}
}
