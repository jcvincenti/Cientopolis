package cientopolis;

import java.util.ArrayList;
import java.util.List;

public class Investigador {
	private String nombre;
	private String apellido;
	private List <Proyecto> proyectos;
	
	
	public Investigador (String nombre, String apellido){
		this.nombre = nombre;
		this.apellido = apellido;
		this.proyectos = new ArrayList<Proyecto>();
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getApellido(){
		return this.apellido;
	}
	
	public List<Proyecto> getProyectos(){
		return this.proyectos;
	}
	
	public void agregarProyecto (Proyecto proyecto){
		this.proyectos.add(proyecto);
	}
	
	public void agregarEncuestaAProyecto (String nombreProyecto, String nombreEncuesta){
		this.getProyecto(nombreProyecto).agregarEncuesta(Encuesta.nuevaEncuesta(nombreEncuesta));
	}
	
	public Proyecto getProyecto(String nombre){
		//buscamos el proyecto, si existe lo retornamos, sino retorna null.
		return this.proyectos.stream().filter(proyecto -> proyecto.getNombre().equals(nombre)).findFirst().orElse(null);
	}
}
