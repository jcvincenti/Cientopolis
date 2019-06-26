package cientopolis;

public class EncuestasMasUtilizadas extends CriteriosDeOrden {

	@Override
	public int compare(Encuesta e1, Encuesta e2) {
		//guardar en una lista a ordenar, todas las encuestas.
		//una vez guardadas, se ordena la lista segun este criterio.
		//se retorna las primeras 25.
		return e2.cantidadDeVecesRespondida().compareTo(e1.cantidadDeVecesRespondida());
	}

}
