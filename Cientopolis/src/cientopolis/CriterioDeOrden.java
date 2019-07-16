package cientopolis;

import java.util.*;

public abstract class CriterioDeOrden{
	
	List<Encuesta>listaAOrdenar;
	
	protected abstract void setListaSegunCriterio(Investigador investigador);
	
	public List<Encuesta> getListaSegunCriterio(){
		return this.listaAOrdenar;
	}

}