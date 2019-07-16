package cientopolis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CriterioDeOrdenPorProyecto extends CriterioDeOrden  implements Comparator<Proyecto> {

	List<Proyecto> listaProyectos;
	
	public int compare(Proyecto p1, Proyecto p2) {
		return p2.getNombre().compareTo(p1.getNombre());
	}
	
	
	
	//guardar todos los proyectos de un investigador en una lista, sin sus proyectos hijos, solo encuestas.
	//por cada proyecto, se ordena alfabeticamente la lista de encuestas que tiene.
	//una vez ordenada, se la agrega a otra lista que será retornada. Se sigue con el siguiente proyecto.
	
	@Override
	public void setListaSegunCriterio(Investigador investigador) {
		listaProyectos = new ArrayList<Proyecto>(investigador.getProyectos());
		Collections.sort(listaProyectos,this);
		for(Proyecto proyecto : listaProyectos) {
			listaAOrdenar.addAll(proyecto.getEncuestas());
		}
	}
	
	/*	public void setOrdenPorProyecto(){
		for(Trabajo trabajo : listaARetornar){
			Collections.sort(trabajo.getEncuestas(),this.criterio);
			listaAOrdenar.addAll(trabajo.getEncuestas());
		}
	
	*/
}
