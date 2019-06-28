package cientopolis;

import java.util.*;

//cambiar el tipo del comparador. Debe ser un wrapper de Encuesta y Proyecto.
public abstract class CriteriosDeOrden implements Comparator<Trabajo>{

	public abstract int compare(Trabajo e1, Trabajo e2);
}
