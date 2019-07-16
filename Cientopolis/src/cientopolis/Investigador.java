package cientopolis;

import java.util.ArrayList;
import java.util.List;

public class Investigador implements Notificable{
	private String nombre;
	private String apellido;
	private List <Proyecto> proyectos;
	private CriterioDeOrden criterio;
	
	
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
		List<Proyecto> temp = new ArrayList<Proyecto>();
		for (Proyecto proyecto : proyectos) {
			temp.addAll(proyecto.getProyectos());
		}
		return temp;
	}
	
	public List<Encuesta> getEncuestas(){
		List<Encuesta> temp = new ArrayList<Encuesta>();
		for (Proyecto proyecto : proyectos) {
			temp.addAll(proyecto.getEncuestasTotales());
		}
		return temp;
	}
	
	public void agregarProyecto (Proyecto proyecto){
		this.proyectos.add(proyecto);
	}
	
	public void agregarEncuestaAProyecto (Encuesta encuesta, Proyecto proyecto) throws Excepciones{
		proyecto.agregarEncuestaAProyecto(encuesta, proyecto);
	}
	
	public void agregarSubproyectoAProyecto (Proyecto proyectoAAgregar, Proyecto proyecto) throws Excepciones{
		proyecto.agregarProyectoHijoAProyecto(proyectoAAgregar, proyecto);
	}
	
	public Proyecto getProyecto(String nombre){
		//buscamos el proyecto, si existe lo retornamos, sino retorna null.
		return this.proyectos.stream().filter(proyecto -> proyecto.getNombre().equals(nombre)).findFirst().orElse(null);
	}
	
	public void notificar(Encuesta encuesta, Respondible pregunta, Respuesta respuesta){
		System.out.println("Se ha respondido la pregunta " + pregunta.getPregunta() + " de la encuesta " + encuesta.getNombre() 
				+ " con la respuesta " + respuesta.getDescripcion());
	}
	
	public void setCriterioDeOrden(CriterioDeOrden criterioElegido) {
		this.criterio = criterioElegido;
		this.criterio.setListaSegunCriterio(this);
	}
	
	public CriterioDeOrden getCriterioDeOrden() {
		return this.criterio;
	}
	
	public List<Encuesta> getListaPorCriterioElegido(){
		return this.criterio.getListaSegunCriterio();
	}
}
