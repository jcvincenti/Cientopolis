package cientopolis;

public class Simple extends Respuesta {

	String respuesta;
	
	public Simple(String opcion) {
		super(false);
		this.respuesta = opcion;
	}

	public String getDescripcion(){
		return this.respuesta;
	}
	
}
