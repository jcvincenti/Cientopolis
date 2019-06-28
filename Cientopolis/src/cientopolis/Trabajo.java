package cientopolis;

import java.time.LocalDate;
import java.util.List;

public abstract class Trabajo {
	/*
	//no sobra ya? al usar agregarTrabajoAProyecto(Trabajo trabajo,Proyecto proyecto)
	public void agregarEncuesta(Encuesta encuesta) throws Excepciones{
		throw new Excepciones(4);
	}
	
	//no sobra ya? al usar agregarTrabajoAProyecto(Trabajo trabajo,Proyecto proyecto)
	public void agregarProyecto(Proyecto proyecto) throws Excepciones{
		throw new Excepciones(4);
	}
	*/
	
	public abstract boolean getEstaFinalizada();
	
	public abstract List<Encuesta> getTodasLasEncuestas();
	
	public abstract List<Trabajo> getEncuestas();
	
	public abstract List<Trabajo> getProyectos();
	
	protected abstract Boolean contieneAlTrabajo(Trabajo trabajo);
	
	protected void agregarTrabajoAProyecto(Trabajo trabajo,Proyecto proyecto) throws Excepciones{
		throw new Excepciones(4);
	}
	
	public abstract void buscarProyectoAAgregarTrabajo(Trabajo trabajo,Proyecto proyecto) throws Excepciones;

	public abstract List<Trabajo> getTrabajos();
	
	protected abstract Integer cantidadDeVecesRespondida();
	
	protected abstract LocalDate getFechaDeCreacion();
	
	public abstract String getNombre();
	
	protected abstract void agregarEncuesta(Encuesta encuesta);
	
	public boolean esProyecto(){
		return false;
	}
}
