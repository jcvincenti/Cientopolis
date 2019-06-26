package cientopolis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Investigador implements Notificable{
	private String nombre;
	private String apellido;
	private List <Proyecto> proyectos;
	private CriteriosDeOrden criterio;
	private List<Encuesta> listaAOrdenar;
	
	
	public Investigador (String nombre, String apellido){
		this.nombre = nombre;
		this.apellido = apellido;
		this.proyectos = new ArrayList<Proyecto>();
		this.listaAOrdenar = new ArrayList<Encuesta>();
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getApellido(){
		return this.apellido;
	}
	
	public void setOrdenPorProyecto(){
		this.criterio = new PorProyecto();
	}
	
	public void setUltimasEncuestasCreadas(){
		for(Proyecto trabajo : this.proyectos){
			this.listaAOrdenar.addAll(trabajo.getEncuestas());
		}
		this.criterio = new UltimasEncuestasCreadas();
	}
	
	public void setEncuestasMasUtilizadas(){
		for(Proyecto trabajo : this.proyectos){
			this.listaAOrdenar.addAll(trabajo.getEncuestas());
		}
		this.criterio = new EncuestasMasUtilizadas();
	}
	
	public List<Encuesta> getEncuestasOrdenadas(){
		Collections.sort(this.listaAOrdenar, this.criterio);
		return this.listaAOrdenar;
	}
	
	public List<Proyecto> getProyectos(){
		return this.proyectos;
	}
	
	public void agregarProyecto (Proyecto proyecto){
		this.proyectos.add(proyecto);
	}
	
	public void agregarEncuestaAProyecto (String nombreProyecto, String nombreEncuesta,String fechaDeEncuesta){
		this.getProyecto(nombreProyecto).agregarEncuesta(Encuesta.nuevaEncuesta(nombreEncuesta,fechaDeEncuesta));
	}
	
	public Proyecto getProyecto(String nombre){
		//buscamos el proyecto, si existe lo retornamos, sino retorna null.
		return this.proyectos.stream().filter(proyecto -> proyecto.getNombre().equals(nombre)).findFirst().orElse(null);
	}
	
	public void notificar(Encuesta encuesta, Respondible pregunta, Respuesta respuesta){
		System.out.println("Se ha respondido la pregunta " + pregunta.getPregunta() + " de la encuesta " + encuesta.getNombre() 
				+ " con la respuesta " + respuesta.getDescripcion());
	}
}
