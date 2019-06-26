package cientopolis;

public class ImportanteEspecifica extends PreguntaDecorator implements Respondible{
	
	private Respuesta respuesta;
	
	public ImportanteEspecifica(Respondible preguntaD, Notificador notificadorD, Encuesta encuestaD,Respuesta respuestaD) {
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
