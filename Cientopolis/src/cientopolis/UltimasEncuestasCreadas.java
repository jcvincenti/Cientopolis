package cientopolis;

public class UltimasEncuestasCreadas extends CriteriosDeOrden {

	@Override
	public int compare(Trabajo e1, Trabajo e2) {
		//guardar en una lista a ordenar, todas las encuestas.
		//una vez guardadas, se ordena la lista segun este criterio.
		//se retorna las primeras 20.
		return e1.getFechaDeCreacion().compareTo(e2.getFechaDeCreacion());
	}

}
