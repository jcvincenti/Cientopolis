package cientopolis;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Proyecto extends Trabajo{
	
	private String nombre;
	public List <Trabajo> trabajos;

	public Proyecto (String nombre){
		this.nombre = nombre;
		this.trabajos = new ArrayList <Trabajo>();
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void agregarEncuesta(Encuesta encuesta){
		this.trabajos.add(encuesta);
	}
	
	@Override
	public Boolean contieneAlTrabajo(Trabajo trabajo) {
		return this.getTrabajos().contains(trabajo);
	}
	
	@Override
	public List<Trabajo> getTrabajos(){	
		List<Trabajo> trabajosARetornar = new ArrayList<Trabajo>();
			if (!trabajos.isEmpty()){
				trabajosARetornar.add(this);
				for(Trabajo trabajoHijo : trabajos){
					trabajosARetornar.addAll(trabajoHijo.getTrabajos());
		 		}
			}else{
				trabajosARetornar.add(this);
			}
		return trabajosARetornar;
			
	}
	
	public List<Trabajo> getEncuestas(){
		List<Trabajo> encuestasARetornar = new ArrayList<Trabajo>();
		
		for(Trabajo trabajo : trabajos){
			if (!trabajo.esProyecto()){
				encuestasARetornar.add(trabajo);
			}
		}
		
		return encuestasARetornar;
	}
	
	@Override
	public List<Trabajo> getProyectos(){
		List<Trabajo> trabajosARetornar = new ArrayList<Trabajo>();
		if (!trabajos.isEmpty()){
			trabajosARetornar.add(this);
			for(Trabajo trabajoHijo : trabajos){
				trabajosARetornar.addAll(trabajoHijo.getProyectos());
	 		}
		}else{
			trabajosARetornar.add(this);
		}
	return trabajosARetornar;
	}
	
	public List<Trabajo> getTodasLasEncuestas (){
		List<Trabajo> encuestasARetornar = new ArrayList<Trabajo>();		
		if (!trabajos.isEmpty()){
			for(Trabajo trabajoHijo : trabajos){
				encuestasARetornar.addAll(trabajoHijo.getTodasLasEncuestas());
			}
		}
		return encuestasARetornar;
	}

	public static Proyecto nuevoProyecto(String nombre) {
		return (new Proyecto(nombre));
	}
	
	public List<Trabajo> getTopCincoEncuestasFinalizadas(){
		List<Trabajo> topEncuestas = new ArrayList<Trabajo>();
		Collections.sort(this.trabajos, (Trabajo e1, Trabajo e2)->e1.cantidadDeVecesRespondida()-e2.cantidadDeVecesRespondida());
		if (trabajos.size()<5){
			topEncuestas = trabajos;
		}else{
		topEncuestas = this.trabajos.subList(0, 5);
		}
		return topEncuestas;
	}
	
	//nose si esta bien hecha o si es un quilombo y se podria hacer mejor (me refiero a buscarProyectoAAgregarTrabajo(Trabajo trabajo)
		@Override
		public void agregarTrabajoAProyecto(Trabajo trabajo,Proyecto proyecto) throws Excepciones{
			if (this.contieneAlTrabajo(trabajo)) { //compara si el trabajo actual no lo tiene dentro de su lista de trabajo ya incluido
				throw new Excepciones(5);
			}else if(proyecto.equals(this)){ // compara si el proyecto actual es al que se le quiere insertar el nuevo trabajo
				this.trabajos.add(trabajo);
			}else{
				this.buscarProyectoAAgregarTrabajo(trabajo,proyecto); //hace un recorrido por las sublistas del proyecto actual para buscar si contiene el Proyecto deseado para agregar dicho trabajo
			}
		}

		//devolver una Excepcion que indique que ningún proyecto contiene el proyecto deseado para agregar dicho Trabajo? 
		//lo implemente de todas maneras pero no modifique nada creado anteriormente :)
		public void buscarProyectoAAgregarTrabajo(Trabajo trabajo,Proyecto proyecto) throws Excepciones {
			for (Trabajo trabajo2 : trabajos) {
				if(trabajo2.esProyecto()){
					if(trabajo2.equals(proyecto)){
						trabajo2.agregarTrabajoAProyecto(trabajo2, proyecto);
					}else{
						trabajo2.buscarProyectoAAgregarTrabajo(trabajo, proyecto);
					}
				}
			}
		}
		
		
		// modificar? podría recorrerse la lista y pregunta a cada elemento la cantidad de veces que se respondio.
		public Integer cantidadDeVecesRespondida(){
			return null;
		}
		
		public LocalDate getFechaDeCreacion(){
			return null;
		}
		
		@Override
		public boolean esProyecto(){
			return true;
		}
	
}
