package cientopolis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Proyecto{
	
	private String nombre;
	private List <Encuesta> encuestas;

	public Proyecto (String nombre){
		this.nombre = nombre;
		this.encuestas = new ArrayList <Encuesta>();
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void agregarEncuesta(Encuesta encuesta){
		this.encuestas.add(encuesta);
	}
	
	public List<Encuesta> getEncuestas (){
		return this.encuestas;
	}

	public static Proyecto nuevoProyecto(String nombre) {
		return (new Proyecto(nombre));
	}
	
	public List<Encuesta> getTopCincoEncuestasFinalizadas(){
		List<Encuesta> topEncuestas = new ArrayList<Encuesta>();
		Collections.sort(this.encuestas, (Encuesta e1, Encuesta e2)->e1.cantidadDeVecesRespondida()-e2.cantidadDeVecesRespondida());
		if (encuestas.size()<5){
			topEncuestas = encuestas;
		}else{
		topEncuestas = this.encuestas.subList(0, 5);
		}
		return topEncuestas;
	}
}
