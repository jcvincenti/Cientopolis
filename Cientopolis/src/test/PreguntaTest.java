package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cientopolis.Abierta;
import cientopolis.Compuesta;
import cientopolis.MultipleSeleccion;
import cientopolis.Respondible;
import cientopolis.Respuesta;
import cientopolis.Simple;
import cientopolis.SimpleSeleccion;

public class PreguntaTest {
	
	Simple respuesta6 = new Simple ("respuesta6");
	Simple respuesta5 = new Simple ("respuesta5");
	Simple respuesta4 = new Simple ("respuesta4");
	Simple respuesta3 = new Simple ("respuesta3");
	Simple respuesta2 = new Simple ("respuesta2");
	Simple respuesta1 = new Simple ("respuesta1");
	
	Abierta pregunta6 = new Abierta("pregunta6",true,null);
	
	Abierta pregunta5 = new Abierta ("pregunta5",false,pregunta6);
	Abierta pregunta4 = new Abierta ("pregunta4",false,pregunta6);
	
	Map <Respuesta,Respondible> opcionesPregunta3 = new HashMap <Respuesta,Respondible>();
	
	SimpleSeleccion pregunta3 = new SimpleSeleccion ("pregunta3",opcionesPregunta3,null);
	Abierta pregunta2 = new Abierta ("pregunta2",false,pregunta3);
	Abierta pregunta1 = new Abierta ("pregunta1",false,null);
	
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
		
		SimpleSeleccion pregunta3 = new SimpleSeleccion ("pregunta3",opcionesPregunta3,null);
		
		pregunta3.responder(respuesta3);
		assertEquals(pregunta4,pregunta3.preguntaSiguiente());
	}
	
	@Test
	public void test05ObtenerSiEsCompuestaLaPreguntaDeSimpleSeleccion(){
		SimpleSeleccion pregunta3 = new SimpleSeleccion ("pregunta3",opcionesPregunta3,null);
		assertTrue(pregunta3.getTipoDePregunta());
	}
	
	@Test
	public void test06ObtenerLasOpcionesAUnaPreguntaSimpleSeleccion(){
		Map <Respuesta,Respondible> opcionesPregunta3 = new HashMap <Respuesta,Respondible>();
		opcionesPregunta3.put(respuesta3, pregunta4);
		opcionesPregunta3.put(respuesta4, pregunta5);
		SimpleSeleccion pregunta3 = new SimpleSeleccion ("pregunta3",opcionesPregunta3,null);
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
		MultipleSeleccion preguntaMS = new MultipleSeleccion("PreguntaMSPrueba",true,null,opciones);
		preguntaMS.responder(respuesta1);
		
		assertEquals(preguntaMS.getRespuesta(),"respuesta1");
	}
	
	@Test
	public void test10ObtenerLasOpcionesDeUnaPreguntaDeMultipleSeleccion(){
		List<String> opciones = new ArrayList<String>();
		opciones.add("opcion1");
		opciones.add("opcion2");
		opciones.add("opcion3");
		MultipleSeleccion preguntaMS = new MultipleSeleccion("PreguntaMSPrueba",true,null,opciones);
		
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
		Compuesta respuesta7 = new Compuesta (opciones);
		
		assertTrue(respuesta7.getDescripcion().contains("opcion1")&&
				respuesta7.getDescripcion().contains("opcion2")&&
				respuesta7.getDescripcion().contains("opcion3"));
	}
}
