package cientopolis;

import java.util.Collections;
import java.util.Comparator;

public class CriterioDeOrdenPorUltimasEncuestasCreadas extends CriterioDeOrden  implements Comparator<Encuesta>{

	@Override
	public int compare(Encuesta e1, Encuesta e2) {
		return e2.getFechaDeCreacion().compareTo(e1.getFechaDeCreacion());
	}

	
	
	//guardar en una lista a ordenar, todas las encuestas.
	//una vez guardadas, se ordena la lista segun este criterio.
	//se retorna las primeras 20.
	@Override
	protected void setListaSegunCriterio(Investigador investigador) {
		listaAOrdenar = investigador.getEncuestas();
		Collections.sort(listaAOrdenar,this);
		if(listaAOrdenar.size()>20){
			listaAOrdenar = listaAOrdenar.subList(0, 20);
		}
	}

}