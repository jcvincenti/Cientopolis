package cientopolis;

public class RespuestaSimple extends Respuesta {

	String respuesta;
	
	public RespuestaSimple(String opcion) {
		super(false);
		this.respuesta = opcion;
	}

	public String getDescripcion(){
		return this.respuesta;
	}
	
}
