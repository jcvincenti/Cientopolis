package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cientopolis.PreguntaAbierta;
import cientopolis.RespuestaCompuesta;
import cientopolis.PreguntaMultipleSeleccion;
import cientopolis.Respondible;
import cientopolis.Respuesta;
import cientopolis.RespuestaSimple;
import cientopolis.PreguntaSimpleSeleccion;

public class PreguntaTest {
	
	RespuestaSimple respuesta6 = new RespuestaSimple ("respuesta6");
	RespuestaSimple respuesta5 = new RespuestaSimple ("respuesta5");
	RespuestaSimple respuesta4 = new RespuestaSimple ("respuesta4");
	RespuestaSimple respuesta3 = new RespuestaSimple ("respuesta3");
	RespuestaSimple respuesta2 = new RespuestaSimple ("respuesta2");
	RespuestaSimple respuesta1 = new RespuestaSimple ("respuesta1");
	
	PreguntaAbierta pregunta6 = new PreguntaAbierta("pregunta6",true,null);
	
	PreguntaAbierta pregunta5 = new PreguntaAbierta ("pregunta5",false,pregunta6);
	PreguntaAbierta pregunta4 = new PreguntaAbierta ("pregunta4",false,pregunta6);
	
	Map <Respuesta,Respondible> opcionesPregunta3 = new HashMap <Respuesta,Respondible>();
	
	PreguntaSimpleSeleccion pregunta3 = new PreguntaSimpleSeleccion ("pregunta3",opcionesPregunta3,null);
	PreguntaAbierta pregunta2 = new PreguntaAbierta ("pregunta2",false,pregunta3);
	PreguntaAbierta pregunta1 = new PreguntaAbierta ("pregunta1",false,null);
	
	@Before
	public void setUp(){
	opcionesPregunta3.put(respuesta4, pregunta4);
	opcionesPregunta3.put(respuesta5, pregunta5);
	}
	
	@Test
	public void test01ChequearSiLasRespuetasSonSimplesOMultiples(){
		assertFalse(respuesta1.getTipoDeRespuesta());
	}
	
	@Test
	public void test02ObtenerOpcionesDePreguntaAbierta() {
		assertEquals(null,pregunta1.getOpciones());
	}
	
	@Test
	public void test03ObtenerLaRespuestaAUnPreguntaAbiertaRespondida(){

		pregunta1.responder(respuesta1);
		
		assertEquals(pregunta1.getRespuesta(),"respuesta1");
	}
	
	@Test
	public void test04ObtenerLaPreguntaSiguienteAUnaPreguntaDeSimpleSeleccion(){
		Map <Respuesta,Respondible> opcionesPregunta3 = new HashMap <Respuesta,Respondible>();
		opcionesPregunta3.put(respuesta3, pregunta4);
		opcionesPregunta3.put(respuesta4, pregunta5);
		
		PreguntaSimpleSeleccion pregunta3 = new PreguntaSimpleSeleccion ("pregunta3",opcionesPregunta3,null);
		
		pregunta3.responder(respuesta3);
		assertEquals(pregunta4,pregunta3.preguntaSiguiente());
	}
	
	@Test
	public void test05ObtenerSiEsCompuestaLaPreguntaDeSimpleSeleccion(){
		PreguntaSimpleSeleccion pregunta3 = new PreguntaSimpleSeleccion ("pregunta3",opcionesPregunta3,null);
		assertTrue(pregunta3.getTipoDePregunta());
	}
	
	@Test
	public void test06ObtenerLasOpcionesAUnaPreguntaSimpleSeleccion(){
		Map <Respuesta,Respondible> opcionesPregunta3 = new HashMap <Respuesta,Respondible>();
		opcionesPregunta3.put(respuesta3, pregunta4);
		opcionesPregunta3.put(respuesta4, pregunta5);
		PreguntaSimpleSeleccion pregunta3 = new PreguntaSimpleSeleccion ("pregunta3",opcionesPregunta3,null);
		assertTrue(pregunta3.getOpciones().contains(respuesta4)&& pregunta3.getOpciones().contains(respuesta3));
	}
	
	@Test
	public void test07ObtenerLaDescripcionDeUnaPregunta(){
		assertEquals(pregunta1.getPregunta(),"pregunta1");
	}
	
	@Test
	public void test08ObtenerSiEsUltimaPregunta(){
		assertTrue(pregunta6.esUltimaPregunta());
	}
	
	@Test
	public void test09ObtenerRespuestaDeUnaPreguntaMultipleSeleccion(){
		List<String> opciones = new ArrayList<String>();
		PreguntaMultipleSeleccion preguntaMS = new PreguntaMultipleSeleccion("PreguntaMSPrueba",true,null,opciones);
		preguntaMS.responder(respuesta1);
		
		assertEquals(preguntaMS.getRespuesta(),"respuesta1");
	}
	
	@Test
	public void test10ObtenerLasOpcionesDeUnaPreguntaDeMultipleSeleccion(){
		List<String> opciones = new ArrayList<String>();
		opciones.add("opcion1");
		opciones.add("opcion2");
		opciones.add("opcion3");
		PreguntaMultipleSeleccion preguntaMS = new PreguntaMultipleSeleccion("PreguntaMSPrueba",true,null,opciones);
		
		assertTrue(preguntaMS.getOpciones().contains("opcion1")&&
				preguntaMS.getOpciones().contains("opcion2")&&
				preguntaMS.getOpciones().contains("opcion3"));
	}
	
	@Test
	public void test11ObtenerLasOpcionesDeUnaRespuestaMultiple(){
		List<String> opciones = new ArrayList<String>();
		opciones.add("opcion1");
		opciones.add("opcion2");
		opciones.add("opcion3");
		RespuestaCompuesta respuesta7 = new RespuestaCompuesta (opciones);
		
		assertTrue(respuesta7.getDescripcion().contains("opcion1")&&
				respuesta7.getDescripcion().contains("opcion2")&&
				respuesta7.getDescripcion().contains("opcion3"));
	}
}
