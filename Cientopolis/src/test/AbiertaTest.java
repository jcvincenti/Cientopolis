package test;

import static org.junit.Assert.*;
import org.junit.Test;

import cientopolis.Abierta;
import cientopolis.Simple;

public class AbiertaTest {

	Abierta pregunta1 = new Abierta("¿Que edad tiene?",true,null);
	Simple respuesta1 = new Simple ("25");

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
