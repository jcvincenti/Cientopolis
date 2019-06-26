package cientopolis;

import java.util.ArrayList;
import java.util.List;

public class EncuestaRespondida {

	private List<Respuesta> respuestas;
	private Encuesta encuestaRespondida;
	private Respondible proximaPregunta;
	
	public EncuestaRespondida(Encuesta encuesta){
		respuestas = new ArrayList<Respuesta>();
		encuestaRespondida = encuesta;
	}
	
	public Encuesta getEncuesta(){
		return this.encuestaRespondida;
	}
	
	public List<Respuesta> getRespuestas(){
		return this.respuestas;
	}
	
	public void setProximaPregunta(Respondible pregunta){
		this.proximaPregunta = pregunta;
	}
	
	public Respondible getProximaPregunta(){
		return this.proximaPregunta;
	}
	
	public void agregarRespuesta(Respuesta respuesta){
		this.respuestas.add(respuesta);
	}
}
