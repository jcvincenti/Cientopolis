package cientopolis;

public class Excepciones extends Exception{

	private int error;
	
	public Excepciones(int codigoError){
		super();
		this.error = codigoError;
	}
	
	@Override
	public String getMessage(){
		String mensaje = "";
		
		switch(error){
		case(1): mensaje = "El estado de la encuesta debe ser 'EnEdicion'. No se puede agregar la pregunta";
		break;
		case(2): mensaje = "El estado de la encuesta debe ser 'Activa'. No se puede comenzar la encuesta";
		break;
		case(3): mensaje = "Debe comenzar la encuesta para poder responderla";
		break;
		case(5): mensaje = "No puede agregar un proyecto a el mismo";
		}
		
		return mensaje;
	}

	private static final long serialVersionUID = 1L;
}
