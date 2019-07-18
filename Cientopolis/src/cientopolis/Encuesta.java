package cientopolis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Encuesta{
	
	private String nombre;
	private Respondible primerPregunta;
	private List<EncuestaRespondida> encuestasFinalizadas;
	private EstadoDeEncuesta estadoActual;
	private LocalDate fechaDeCreacion;
	private boolean finalizada;
	
	public Encuesta (String nombre,String fecha){
		this.nombre = nombre;
		this.encuestasFinalizadas = new ArrayList<EncuestaRespondida>();
		this.estadoActual = new EstadoDeEncuestaEnEdicion();
		this.fechaDeCreacion = LocalDate.parse(fecha, DateTimeFormatter.ofPattern(("dd/MM/yyyy")));
		finalizada = false;
	}

	public LocalDate getFechaDeCreacion(){
		return this.fechaDeCreacion;
	}
	
	private void setEstado(EstadoDeEncuesta estado) {
		this.estadoActual = estado;
	}
	
	public void editarEncuesta() {
		this.setEstado(new EstadoDeEncuestaEnEdicion());
		finalizada = false;
	}
	
	public void activarEncuesta() {
		this.setEstado(new EstadoDeEncuestaActiva());
		finalizada = false;
	}
	
	public void cerrarEncuesta(){
		this.setEstado(new EstadoDeEncuestaCerrada());
		finalizada = true;
	}
	
	public boolean getEstaFinalizada(){
		return this.finalizada;
	}
	
	public static Encuesta nuevaEncuesta(String nombre,String fecha){
		return (new Encuesta (nombre,fecha));
	}
	
	public void setPrimerPregunta(Respondible pregunta){
		this.primerPregunta = pregunta;
	}
	
	public void agregarPregunta (Respondible pregunta, Respondible preguntaPadre) throws Excepciones{
		estadoActual.addPregunta(pregunta, preguntaPadre, this);
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public Integer cantidadDeVecesRespondida(){
		return this.encuestasFinalizadas.size();
	}
	
	public Respondible getPrimerPregunta(){
		return this.primerPregunta;
	}
	
	public void comenzarEncuesta() throws Excepciones{
		estadoActual.comenzarEncuesta(this);
	}
	
	public void responder (Respuesta respuesta) throws Excepciones{
			estadoActual.responderEncuesta(respuesta);
	}
	
	public void agregarEncuestaFinalizada(EncuestaRespondida encuesta){
		this.encuestasFinalizadas.add(encuesta);
	}
	

	public List<Respuesta> getTodasLasRespuestas(){
		List<Respuesta> temp = new ArrayList<Respuesta>();
		for (EncuestaRespondida encuesta : encuestasFinalizadas) {
			temp.addAll(encuesta.getRespuestas());
		}
		return temp;
	}
	
}
