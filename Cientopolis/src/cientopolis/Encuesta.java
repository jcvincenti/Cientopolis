package cientopolis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Encuesta {
	
	private String nombre;
	private Respondible primerPregunta;
	private Integer cantidadDeVecesRespondida;
	private List<EncuestaRespondida> encuestasFinalizadas;
	private EstadoDeEncuesta estadoActual;
	private LocalDate fechaDeCreacion;
	
	public Encuesta (String nombre,String fecha){
		this.nombre = nombre;
		this.cantidadDeVecesRespondida = 0;
		this.encuestasFinalizadas = new ArrayList<EncuestaRespondida>();
		this.estadoActual = new EnEdicion();
		this.fechaDeCreacion = LocalDate.parse(fecha, DateTimeFormatter.ofPattern(("dd/MM/yyyy")));
	}
	
	public LocalDate getFechaDeCreacion(){
		return this.fechaDeCreacion;
	}
	
	private void setEstado(EstadoDeEncuesta estado) {
		this.estadoActual = estado;
	}
	
	public void editarEncuesta() {
		this.setEstado(new EnEdicion());
	}
	
	public void activarEncuesta() {
		this.setEstado(new Activa());
	}
	
	public void cerrarEncuesta(){
		this.setEstado(new Cerrada());
	}
	
	public EstadoDeEncuesta getEstado() {
		return this.estadoActual;
	}
	
	public static Encuesta nuevaEncuesta(String nombre,String fecha){
		return (new Encuesta (nombre,fecha));
	}
	
	public void setPrimerPregunta(Respondible pregunta){
		this.primerPregunta = pregunta;
	}
	
	public void agregarPregunta (Respondible pregunta, Respondible preguntaPadre){
		preguntaPadre.setPreguntaSiguiente(pregunta);
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public Integer cantidadDeVecesRespondida(){
		return this.cantidadDeVecesRespondida;
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
	
	public void sumarCantidadDeVecesRespondida(){
		this.cantidadDeVecesRespondida = this.cantidadDeVecesRespondida + 1;
	}
	
	public void agregarEncuestaFinalizada(EncuestaRespondida encuesta){
		this.encuestasFinalizadas.add(encuesta);
	}
	
}
