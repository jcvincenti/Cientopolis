package test;

import static org.junit.Assert.*;
import org.junit.Test;

import cientopolis.PreguntaAbierta;
import cientopolis.RespuestaSimple;

public class AbiertaTest {

	PreguntaAbierta pregunta1 = new PreguntaAbierta("¿Que edad tiene?",true,null);
	RespuestaSimple respuesta1 = new RespuestaSimple ("25");

	@Test
	public void test01ObtenerOpcionesDePreguntaAbierta() {
		assertEquals(null,pregunta1.getOpciones());
	}
	
	@Test
	public void test02ObtenerLaRespuestaAUnPreguntaRespondida(){

		pregunta1.responder(respuesta1);
		
		assertEquals(pregunta1.getRespuesta(),"25");
	}

}
