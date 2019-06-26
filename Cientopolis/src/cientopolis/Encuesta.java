package cientopolis;

import java.util.ArrayList;
import java.util.List;

public class Encuesta {
	
	private String nombre;
	private Respondible primerPregunta;
	private Integer cantidadDeVecesRespondida;
	private List<EncuestaRespondida> encuestasFinalizadas;
	private EstadoDeEncuesta estadoActual;
	
	public Encuesta (String nombre){
		this.nombre = nombre;
		this.cantidadDeVecesRespondida = 0;
		this.encuestasFinalizadas = new ArrayList<EncuestaRespondida>();
		this.estadoActual = new EnEdicion();
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
	
	public static Encuesta nuevaEncuesta(String nombre){
		return (new Encuesta (nombre));
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
