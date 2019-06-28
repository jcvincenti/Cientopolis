package cientopolis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Encuesta extends Trabajo{
	
	private String nombre;
	private Respondible primerPregunta;
	private Integer cantidadDeVecesRespondida;
	private List<EncuestaRespondida> encuestasFinalizadas;
	private EstadoDeEncuesta estadoActual;
	private LocalDate fechaDeCreacion;
	private boolean finalizada;
	
	public Encuesta (String nombre,String fecha){
		this.nombre = nombre;
		this.cantidadDeVecesRespondida = 0;
		this.encuestasFinalizadas = new ArrayList<EncuestaRespondida>();
		this.estadoActual = new EnEdicion();
		this.fechaDeCreacion = LocalDate.parse(fecha, DateTimeFormatter.ofPattern(("dd/MM/yyyy")));
		finalizada = false;
	}
	
	@Override
	public List<Encuesta> getTodasLasEncuestas() {
		List<Encuesta> encuestasADevolver = new ArrayList<Encuesta>();
		encuestasADevolver.add(this);
		return encuestasADevolver;	
	}
	
	public LocalDate getFechaDeCreacion(){
		return this.fechaDeCreacion;
	}
	
	private void setEstado(EstadoDeEncuesta estado) {
		this.estadoActual = estado;
	}
	
	public void editarEncuesta() {
		this.setEstado(new EnEdicion());
		finalizada = false;
	}
	
	public void activarEncuesta() {
		this.setEstado(new Activa());
		finalizada = false;
	}
	
	public void cerrarEncuesta(){
		this.setEstado(new Cerrada());
		finalizada = true;
	}
	
	public EstadoDeEncuesta getEstado() {
		return this.estadoActual;
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
	
	@Override
	public Boolean contieneAlTrabajo(Trabajo trabajo) {
		return null;
	}

	@Override
	public List<Trabajo> getTrabajos() {
		List<Trabajo> encuestasADevolver = new ArrayList<Trabajo>();
		encuestasADevolver.add(this);
		return encuestasADevolver;
	}
	
	@Override
	public void agregarEncuesta(Encuesta encuesta){}
	
	@Override
	public void buscarProyectoAAgregarTrabajo(Trabajo trabajo, Proyecto proyecto){}
	
	@Override
	public List<Trabajo> getProyectos(){
		List<Trabajo> encuestasADevolver = new ArrayList<Trabajo>();
		return encuestasADevolver;
	}
		
	@Override
	public List<Trabajo> getEncuestas(){
		List<Trabajo> encuestasADevolver = new ArrayList<Trabajo>();
		return encuestasADevolver;
	}
	
	public List<Respuesta> getTodasLasRespuestas(){
		List<Respuesta> temp = new ArrayList<Respuesta>();
		for (EncuestaRespondida encuesta : encuestasFinalizadas) {
			temp.addAll(encuesta.getRespuestas());
		}
		return temp;
	}
	
}
