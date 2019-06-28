package cientopolis;

public class PorProyecto extends CriteriosDeOrden {

	@Override
	public int compare(Trabajo e1, Trabajo e2) {
		//guardar todos los proyectos de un investigador en una lista, sin sus proyectos hijos, solo encuestas.
		//por cada proyecto, se ordena alfabeticamente la lista de encuestas que tiene.
		//una vez ordenada, se la agrega a otra lista que será retornada. Se sigue con el siguiente proyecto.
		
		return e2.getNombre().compareTo(e1.getNombre());
	}

}
