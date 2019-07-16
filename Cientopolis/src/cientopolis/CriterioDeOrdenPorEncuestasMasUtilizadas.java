package cientopolis;

import java.util.Collections;
import java.util.Comparator;

public class CriterioDeOrdenPorEncuestasMasUtilizadas extends CriterioDeOrden  implements Comparator<Encuesta>{

	@Override
	public int compare(Encuesta e1, Encuesta e2) {
		return e2.cantidadDeVecesRespondida().compareTo(e1.cantidadDeVecesRespondida());
	}

	
	
	//guardar en una lista a ordenar, todas las encuestas.
	//una vez guardadas, se ordena la lista segun este criterio.
	//se retorna las primeras 25.
	@Override
	protected void setListaSegunCriterio(Investigador investigador) {
		listaAOrdenar = investigador.getEncuestas();
		Collections.sort(listaAOrdenar,this);
		if(listaAOrdenar.size()>25){
			listaAOrdenar = listaAOrdenar.subList(0, 25);
		}
	}
	
}