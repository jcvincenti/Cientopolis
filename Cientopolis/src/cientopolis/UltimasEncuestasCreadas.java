package cientopolis;

public class UltimasEncuestasCreadas extends CriteriosDeOrden {

	@Override
	public int compare(Encuesta e1, Encuesta e2) {
		//guardar en una lista a ordenar, todas las encuestas.
		//una vez guardadas, se ordena la lista segun este criterio.
		//se retorna las primeras 20.
		return e2.getFechaDeCreacion().compareTo(e1.getFechaDeCreacion());
	}

}
