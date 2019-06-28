package cientopolis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Investigador implements Notificable{
	private String nombre;
	private String apellido;
	private List <Trabajo> proyectos;
	private CriteriosDeOrden criterio;
	private List<Trabajo> listaAOrdenar;
	
	
	public Investigador (String nombre, String apellido){
		this.nombre = nombre;
		this.apellido = apellido;
		this.proyectos = new ArrayList<Trabajo>();
		this.listaAOrdenar = new ArrayList<Trabajo>();
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getApellido(){
		return this.apellido;
	}
	
	public void setOrdenPorProyecto(){
		List<Trabajo> listaARetornar = new ArrayList<Trabajo>();
		this.criterio = new PorProyecto();
		for (Trabajo trabajo : proyectos) {
			listaARetornar.addAll(trabajo.getProyectos());
		}
		
		for(Trabajo trabajo : listaARetornar){
			Collections.sort(trabajo.getEncuestas(),this.criterio);
			listaAOrdenar.addAll(trabajo.getEncuestas());
		}
	}
	
	public void setUltimasEncuestasCreadas(){
		for(Trabajo trabajo : this.proyectos){
			this.listaAOrdenar.addAll(trabajo.getTodasLasEncuestas());
		}
		this.criterio = new UltimasEncuestasCreadas();
		Collections.sort(this.listaAOrdenar, this.criterio);
		if(listaAOrdenar.size()>20){
		listaAOrdenar = listaAOrdenar.subList(0, 20);
		}
	}
	
	public void setEncuestasMasUtilizadas(){
		for(Trabajo trabajo : this.proyectos){
			this.listaAOrdenar.addAll(trabajo.getTodasLasEncuestas());
		}
		this.criterio = new EncuestasMasUtilizadas();
		Collections.sort(this.listaAOrdenar, this.criterio);
		if(listaAOrdenar.size()>25){
			listaAOrdenar = listaAOrdenar.subList(0, 25);
			}
	}
	
	public List<Trabajo> getEncuestasOrdenadas(){
		return this.listaAOrdenar;
	}
	
	public List<Trabajo> getProyectos(){
		return this.proyectos;
	}
	
	public void agregarProyecto (Proyecto proyecto){
		this.proyectos.add(proyecto);
	}
	
	public void agregarTrabajoAProyecto (Trabajo trabajo, Proyecto proyecto) throws Excepciones{
		proyecto.agregarTrabajoAProyecto(trabajo, proyecto);
	}
	
	public Trabajo getProyecto(String nombre){
		//buscamos el proyecto, si existe lo retornamos, sino retorna null.
		return this.proyectos.stream().filter(proyecto -> proyecto.getNombre().equals(nombre)).findFirst().orElse(null);
	}
	
	public void notificar(Encuesta encuesta, Respondible pregunta, Respuesta respuesta){
		System.out.println("Se ha respondido la pregunta " + pregunta.getPregunta() + " de la encuesta " + encuesta.getNombre() 
				+ " con la respuesta " + respuesta.getDescripcion());
	}
}
