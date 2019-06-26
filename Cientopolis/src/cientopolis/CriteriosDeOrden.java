package cientopolis;

import java.util.*;

//cambiar el tipo del comparador. Debe ser un wrapper de Encuesta y Proyecto.
public abstract class CriteriosDeOrden implements Comparator<Encuesta>{

	public abstract int compare(Encuesta e1, Encuesta e2);
}
