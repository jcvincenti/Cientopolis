package cientopolis;

import java.util.ArrayList;
import java.util.List;

public class Encuesta {
	
	private String nombre;
	private Pregunta primerPregunta;
	private Integer cantidadDeVecesRespondida;
	private EncuestaRespondida encuestaRespondida;
	private List<EncuestaRespondida> encuestasFinalizadas;
	
	public Encuesta (String nombre){
		this.nombre = nombre;
		this.cantidadDeVecesRespondida = 0;
		this.encuestasFinalizadas = new ArrayList<EncuestaRespondida>();
	}
	
	public static Encuesta nuevaEncuesta(String nombre){
		return (new Encuesta (nombre));
	}
	
	public void setPrimerPregunta(Pregunta pregunta){
		this.primerPregunta = pregunta;
	}
	
	public void agregarPregunta (Pregunta pregunta, Pregunta preguntaPadre){
		preguntaPadre.setPreguntaSiguiente(pregunta);
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public Integer cantidadDeVecesRespondida(){
		return this.cantidadDeVecesRespondida;
	}
	
	public Pregunta getPrimerPregunta(){
		return this.primerPregunta;
	}
	
	public void comenzarEncuesta(){
		encuestaRespondida = new EncuestaRespondida(this);
		encuestaRespondida.setProximaPregunta(this.primerPregunta);
		System.out.println("La primer pregunta es: " + this.primerPregunta.descripcionPregunta);
	}
	
	public void responder(Respuesta respuesta){
		if (!encuestaRespondida.getProximaPregunta().esFinal){
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
			this.agregarEncuestaFinalizada(encuestaRespondida);
			this.cantidadDeVecesRespondida = this.cantidadDeVecesRespondida + 1;
		}
		
	}
	
	private void agregarEncuestaFinalizada(EncuestaRespondida encuesta){
		this.encuestasFinalizadas.add(encuesta);
	}
	
}
