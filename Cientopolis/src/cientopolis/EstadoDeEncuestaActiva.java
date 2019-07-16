package cientopolis;

public class EstadoDeEncuestaActiva extends EstadoDeEncuesta {
	
	private Encuesta encuesta;
	private EncuestaRespondida encuestaRespondida;
	
	@Override
	public void comenzarEncuesta(Encuesta encuestaAResponder){
		this.encuesta = encuestaAResponder;
		this.encuestaRespondida = new EncuestaRespondida(encuestaAResponder);
		encuestaRespondida.setProximaPregunta(this.encuesta.getPrimerPregunta());
		System.out.println("La primer pregunta es: " + this.encuesta.getPrimerPregunta().getPregunta());
	}
	
	@Override
	public void responderEncuesta(Respuesta respuesta) throws Excepciones {
		if(this.encuestaRespondida == null){
			throw new Excepciones(3);
		}else{
			if (!encuestaRespondida.getProximaPregunta().esFinal()){
				//se agrega la respuesta a encuesta respondida,se responde la pregunta, se setea la proxima pregunta
				System.out.println("Su respuesta fue: " + respuesta.getDescripcion());
				encuestaRespondida.agregarRespuesta(respuesta);
				encuestaRespondida.getProximaPregunta().responder(respuesta);
				encuestaRespondida.setProximaPregunta(encuestaRespondida.getProximaPregunta().preguntaSiguiente());
				System.out.println("La próxima pregunta es: " + encuestaRespondida.getProximaPregunta().getPregunta());
			}else{
				//se agrega la respuesta a encuesta respondida,se responde la pregunta, se agrega la encuesta respondida
				//a la lista y se incrementa en uno la cantidad de veces respondida
				System.out.println("Su respuesta fue: " + respuesta.getDescripcion());
				System.out.println("Gracias por participar de la encuesta");
				encuestaRespondida.agregarRespuesta(respuesta);
				encuestaRespondida.getProximaPregunta().responder(respuesta);
				encuesta.agregarEncuestaFinalizada(encuestaRespondida);
				encuesta.sumarCantidadDeVecesRespondida();
				this.encuestaRespondida = null;
				}
		}	
	}
}
