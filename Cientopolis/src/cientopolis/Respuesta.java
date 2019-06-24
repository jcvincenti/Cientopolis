package cientopolis;

public abstract class Respuesta {

	protected Boolean compuesta;
	
	public Respuesta(Boolean compuesta) {
		this.compuesta = compuesta;
	}
	
	public Boolean getTipoDeRespuesta(){
		return this.compuesta;
	}
	
	protected abstract String getDescripcion();

}
