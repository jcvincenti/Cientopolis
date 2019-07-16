package cientopolis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Proyecto{
	
	private String nombre;
	public List <Encuesta> encuestas;
	public List <Proyecto> proyectosHijos;
	
	public Proyecto (String nombre){
		this.nombre = nombre;
		this.encuestas = new ArrayList <Encuesta>();
		this.proyectosHijos = new ArrayList <Proyecto>();
	}
	
	public static Proyecto nuevoProyecto(String nombre) {
		return (new Proyecto(nombre));
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public Boolean contieneAlProyecto(Proyecto proyecto) {
		return this.getProyectos().contains(proyecto);
	}
	
	public Boolean contieneALaEncuesta(Encuesta encuesta) {
		return this.getEncuestas().contains(encuesta);
	}
	
	public List<Proyecto> getProyectos(){	
		List<Proyecto> proyectosARetornar = new ArrayList<Proyecto>();
			if (!proyectosHijos.isEmpty()){
				proyectosARetornar.add(this);
				for(Proyecto proyectoHijo : proyectosHijos){
					proyectosARetornar.addAll(proyectoHijo.getProyectos());
		 		}
			}else{
				proyectosARetornar.add(this);
			}
		return proyectosARetornar;		
	}
	
	public List<Encuesta> getEncuestas(){
		return this.encuestas;
	}
	
	public List<Encuesta> getEncuestasTotales(){
		List<Encuesta> encuestasARetornar = new ArrayList<Encuesta>(this.getEncuestas());
		if (!proyectosHijos.isEmpty()){
			for(Proyecto proyectoHijo : proyectosHijos){
				encuestasARetornar.addAll(proyectoHijo.getEncuestas());
			}
		}
		return encuestasARetornar;
	}
	
	public List<Encuesta> getEncuestasFinalizadas(){
		List<Encuesta> topEncuestas = new ArrayList<Encuesta>();
		topEncuestas = this.getEncuestasTotales();
		Collections.sort(topEncuestas, (Encuesta e1, Encuesta e2)->e1.cantidadDeVecesRespondida()-e2.cantidadDeVecesRespondida());
		if (encuestas.size()>5){
			topEncuestas = topEncuestas.subList(0, 5);
		}
		return topEncuestas;
	}
	
	public void agregarProyectoHijo(Proyecto proyecto) {
		this.proyectosHijos.add(proyecto);
	}
	
	public void agregarEncuesta(Encuesta encuesta) {
		this.encuestas.add(encuesta);
	}
	
	public void agregarEncuestaAProyecto(Encuesta encuesta, Proyecto proyecto) throws Excepciones{
		if (this.contieneALaEncuesta(encuesta)) { //compara si el trabajo actual no lo tiene dentro de su lista de trabajo ya incluido
			throw new Excepciones(5);
		}else if(proyecto.equals(this)){ // compara si el proyecto actual es al que se le quiere insertar el nuevo trabajo
			this.encuestas.add(encuesta);
		}else{
			this.buscarProyectoAAgregarEncuesta(encuesta,proyecto); //hace un recorrido por las sublistas del proyecto actual para buscar si contiene el Proyecto deseado para agregar dicho trabajo
		}
	}
		
	public void buscarProyectoAAgregarEncuesta(Encuesta encuesta, Proyecto proyecto) throws Excepciones {
		for (Proyecto p : proyectosHijos) {
				if(p.equals(proyecto)){
					p.agregarEncuesta(encuesta);
				}else{
					p.buscarProyectoAAgregarEncuesta(encuesta, proyecto);
				}
			}
	}

	public void agregarProyectoHijoAProyecto(Proyecto proyectoHijo, Proyecto proyecto) throws Excepciones{
		if (this.contieneAlProyecto(proyectoHijo)) { //compara si el trabajo actual no lo tiene dentro de su lista de trabajo ya incluido
			throw new Excepciones(5);
		}else if(proyecto.equals(this)){ // compara si el proyecto actual es al que se le quiere insertar el nuevo trabajo
			this.proyectosHijos.add(proyectoHijo);
		}else{
			this.buscarProyectoAAgregarProyectoHijo(proyectoHijo, proyecto); //hace un recorrido por las sublistas del proyecto actual para buscar si contiene el Proyecto deseado para agregar dicho trabajo
		}
	}

	public void buscarProyectoAAgregarProyectoHijo(Proyecto proyectoAAgregar, Proyecto proyecto) throws Excepciones {
		for (Proyecto p : proyectosHijos) { ///while?
				if(p.equals(proyecto)){
					p.agregarProyectoHijo(proyectoAAgregar);
				}else{
					p.buscarProyectoAAgregarProyectoHijo(proyectoAAgregar, proyecto);
				}
			}
	}

	public boolean getEstaFinalizada(){
		List<Encuesta> temp = new ArrayList<Encuesta>(this.getEncuestasTotales());
		boolean finalizada = true;
		for (Encuesta encuesta : temp) {
			if (!encuesta.getEstaFinalizada()){
				finalizada = false;
			}
		}
		return finalizada;
	}

	
	
}
