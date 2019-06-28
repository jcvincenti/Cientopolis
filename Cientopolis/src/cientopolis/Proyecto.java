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
	
	public List<Encuesta> getTodasLasEncuestas (){
		List<Encuesta> encuestasARetornar = new ArrayList<Encuesta>();		
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
	
	public List<Encuesta> getEncuestasFinalizadas(){
		List<Encuesta> topEncuestas = new ArrayList<Encuesta>();
		topEncuestas = this.getTodasLasEncuestas();
		Collections.sort(topEncuestas, (Trabajo e1, Trabajo e2)->e1.cantidadDeVecesRespondida()-e2.cantidadDeVecesRespondida());
		if (trabajos.size()>5){
			topEncuestas = topEncuestas.subList(0, 5);
		}
		return topEncuestas;
	}
	
	public boolean getEstaFinalizada(){
		List<Encuesta> temp = new ArrayList<Encuesta>(this.getTodasLasEncuestas());
		boolean finalizada = true;
		for (Encuesta encuesta : temp) {
			if (!encuesta.getEstaFinalizada()){
				finalizada = false;
			}
		}
		return finalizada;
	}
	
		@Override
		public void agregarTrabajoAProyecto(Trabajo trabajo,Proyecto proyecto) throws Excepciones{
			if (this.contieneAlTrabajo(trabajo)) {
				throw new Excepciones(5);
			}else if(proyecto.equals(this)){ 
				this.trabajos.add(trabajo);
			}else{
				this.buscarProyectoAAgregarTrabajo(trabajo,proyecto);
			}
		}

		public void buscarProyectoAAgregarTrabajo(Trabajo trabajo,Proyecto proyecto) throws Excepciones {
			for (Trabajo trabajo2 : trabajos) {
				if(trabajo2.esProyecto()){
					if(trabajo2.equals(proyecto)){
						trabajo2.agregarTrabajoAProyecto(trabajo2, proyecto);
						break;
					}else{
						trabajo2.buscarProyectoAAgregarTrabajo(trabajo, proyecto);
					}
				}
			}
		}
		
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
