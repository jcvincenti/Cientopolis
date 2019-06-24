package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cientopolis.*;

public class EncuestaTest {
	
	List<Respuesta> respuestas = new ArrayList<Respuesta>();
	
	Simple respuesta6 = new Simple ("respuesta6");
	Simple respuesta5 = new Simple ("respuesta5");
	Simple respuesta4 = new Simple ("respuesta4");
	Simple respuesta3 = new Simple ("respuesta3");
	Simple respuesta2 = new Simple ("respuesta2");
	Simple respuesta1 = new Simple ("respuesta1");
	
	Abierta pregunta6 = new Abierta("pregunta6",true,null);
	
	Abierta pregunta5 = new Abierta ("pregunta5",false,pregunta6);
	Abierta pregunta4 = new Abierta ("pregunta4",false,pregunta6);
	
	Map <Respuesta,Pregunta> opcionesPregunta3 = new HashMap <Respuesta,Pregunta>();
	
	SimpleSeleccion pregunta3 = new SimpleSeleccion ("pregunta3",opcionesPregunta3,null);
	Abierta pregunta2 = new Abierta ("pregunta2",false,pregunta3);
	Abierta pregunta1 = new Abierta ("pregunta1",false,pregunta2);
	
	Proyecto proyecto1 = new Proyecto("proyecto re piola");
	Encuesta encuesta1 = Encuesta.nuevaEncuesta("encuesta piola");
	
	@Before
	public void setUp(){
		opcionesPregunta3.put(respuesta4, pregunta4);
		opcionesPregunta3.put(respuesta5, pregunta5);
		
		proyecto1.agregarEncuesta(encuesta1);
	}
	
	@Test
	public void test00ChequearSiLasRespuetasSonSimplesOMultiples(){
		assertFalse(respuesta1.getTipoDeRespuesta());
	}

	@Test
	public void test01ObtenemosElNombreDeLaEncuesta(){
		assertEquals("encuesta piola",encuesta1.getNombre());
	}
	
	@Test
	public void test02SetearLaPrimerPreguntaDeLaEncuesta(){
		encuesta1.setPrimerPregunta(pregunta1);
		
		assertEquals(encuesta1.getPrimerPregunta(), pregunta1);
	}
	
	@Test
	public void test() {
		
		Abierta pregunta6 = new Abierta("pregunta6",true,null);
		
		Abierta pregunta5 = new Abierta ("pregunta5",false,pregunta6);
		Abierta pregunta4 = new Abierta ("pregunta4",false,pregunta6);
		
		Map <Respuesta,Pregunta> opcionesPregunta3 = new HashMap <Respuesta,Pregunta>();
		opcionesPregunta3.put(respuesta3, pregunta4);
		opcionesPregunta3.put(respuesta4, pregunta5);
		
		SimpleSeleccion pregunta3 = new SimpleSeleccion ("pregunta3",opcionesPregunta3,null);
		Abierta pregunta2 = new Abierta ("pregunta2",false,pregunta3);
		Abierta pregunta1 = new Abierta ("pregunta1",false,pregunta2);
		proyecto1.agregarEncuesta(encuesta1);
		
		encuesta1.setPrimerPregunta(pregunta1);
		
		//assertEquals(6,encuesta1.getPreguntas().size());
		encuesta1.activarEncuesta();
		
		encuesta1.comenzarEncuesta();
		encuesta1.responder(respuesta1);
		encuesta1.responder(respuesta2);
		encuesta1.responder(respuesta4);
		encuesta1.responder(respuesta5);
		encuesta1.responder(respuesta6);
		
		assertEquals(new Integer(1),encuesta1.cantidadDeVecesRespondida());
	}
	
	@Test
	public void testExcepciones(){
		encuesta1.activarEncuesta();
		encuesta1.responder(respuesta1);
	}
	
	@Test
	public void test05ObtenerOpcionesDePreguntaAbierta() {
		assertEquals(null,pregunta1.getOpciones());
	}
	
	@Test
	public void test06ObtenerLaRespuestaAUnPreguntaAbiertaRespondida(){

		pregunta1.responder(respuesta1);
		
		assertEquals(pregunta1.getRespuesta(),"respuesta1");
	}
	
	@Test
	public void test07ObtenerLaPreguntaSiguienteAUnaPreguntaDeSimpleSeleccion(){
		Map <Respuesta,Pregunta> opcionesPregunta3 = new HashMap <Respuesta,Pregunta>();
		opcionesPregunta3.put(respuesta3, pregunta4);
		opcionesPregunta3.put(respuesta4, pregunta5);
		
		SimpleSeleccion pregunta3 = new SimpleSeleccion ("pregunta3",opcionesPregunta3,null);
		
		pregunta3.responder(respuesta3);
		assertEquals(pregunta4,pregunta3.preguntaSiguiente());
	}
	
	@Test
	public void test08ObtenerSiEsCompuestaLaPreguntaDeSimpleSeleccion(){
		SimpleSeleccion pregunta3 = new SimpleSeleccion ("pregunta3",opcionesPregunta3,null);
		assertTrue(pregunta3.getTipoDePregunta());
	}
	
	@Test
	public void test09ObtenerLasOpcionesAUnaPreguntaSimpleSeleccion(){
		Map <Respuesta,Pregunta> opcionesPregunta3 = new HashMap <Respuesta,Pregunta>();
		opcionesPregunta3.put(respuesta3, pregunta4);
		opcionesPregunta3.put(respuesta4, pregunta5);
		SimpleSeleccion pregunta3 = new SimpleSeleccion ("pregunta3",opcionesPregunta3,null);
		assertTrue(pregunta3.getOpciones().contains(respuesta4)&& pregunta3.getOpciones().contains(respuesta3));
	}
	
	@Test
	public void test10ObtenerLaDescripcionDeUnaPregunta(){
		assertEquals(pregunta1.getPregunta(),"pregunta1");
	}
	
	@Test
	public void test11ObtenerSiEsUltimaPregunta(){
		assertTrue(pregunta6.esUltimaPregunta());
	}
	
	@Test
	public void test12ObtenerLaRespuestaAUnaPreguntaRespondida(){
		pregunta1.responder(respuesta1);
		assertEquals(pregunta1.getRespuesta(),"respuesta1");
	}
	
	@Test
	public void test13ObtenerElNombreDelInvestigador(){
		Investigador pepe = new Investigador("pepe","pepito");
		
		assertEquals("pepe",pepe.getNombre());
	}
	
	@Test
	public void test14ObtenerElApellidoDelInvestigador(){
		Investigador pepe = new Investigador("pepe","pepito");
		
		assertEquals("pepito",pepe.getApellido());
	}
	
	@Test
	public void test15ObtenerLosProyectosDeUnInvestigador(){
		Investigador pepe = new Investigador("pepe","pepito");
		Proyecto proyecto1 = Proyecto.nuevoProyecto("proyectoX");
		
		pepe.agregarProyecto(proyecto1);
		
		assertTrue(pepe.getProyectos().contains(proyecto1));
	}
	
	@Test
	public void test16VerificarSiElProyectoExiste(){
		Investigador pepe = new Investigador("pepe","pepito");
		Proyecto proyecto1 = Proyecto.nuevoProyecto("proyectoX");
		
		pepe.agregarProyecto(proyecto1);
		pepe.agregarEncuestaAProyecto("proyectoX", "encuesta piola");
		
		assertEquals(pepe.getProyecto("proyectoX"),proyecto1);
	}
	
	@Test
	public void test17ObtenerLasEncuestasDeUnProyecto(){

		Proyecto proyecto1 = Proyecto.nuevoProyecto("proyectoX");
		proyecto1.agregarEncuesta(encuesta1);
		
		
		assertTrue(proyecto1.getEncuestas().contains(encuesta1));
	}
	
	@Test
	public void test18ObtenerLasPrimeras5EncuestasConMayorCantidadDeRespuestas(){
		Proyecto proyecto1 = Proyecto.nuevoProyecto("proyectoX");
		proyecto1.agregarEncuesta(encuesta1);
		Encuesta encuesta2 = new Encuesta ("encuesta2");
		Encuesta encuesta3 = new Encuesta ("encuesta3");
		Encuesta encuesta4 = new Encuesta ("encuesta4");
		Encuesta encuesta5 = new Encuesta ("encuesta5");
		Encuesta encuesta6 = new Encuesta ("encuesta6");
		proyecto1.agregarEncuesta(encuesta2);
		proyecto1.agregarEncuesta(encuesta3);
		proyecto1.agregarEncuesta(encuesta4);
		proyecto1.agregarEncuesta(encuesta5);
		proyecto1.agregarEncuesta(encuesta6);
		
		assertTrue(proyecto1.getTopCincoEncuestasFinalizadas().contains(encuesta1)&&
				proyecto1.getEncuestas().contains(encuesta2)&&
				proyecto1.getEncuestas().contains(encuesta3)&&
				proyecto1.getEncuestas().contains(encuesta4)&& 
				proyecto1.getEncuestas().contains(encuesta5));
	}
	
	@Test
	public void test19ObtenerLaUnicaEncuestaDelTop5(){
		Proyecto proyecto1 = Proyecto.nuevoProyecto("proyectoX");
		proyecto1.agregarEncuesta(encuesta1);
		List<Encuesta> listaDePrueba = new ArrayList<Encuesta>();
		listaDePrueba.add(encuesta1);
		
		assertEquals(proyecto1.getTopCincoEncuestasFinalizadas(),listaDePrueba);
	}
	
	@Test
	public void test20ObtenerRespuestaDeUnaPreguntaMultipleSeleccion(){
		List<String> opciones = new ArrayList<String>();
		MultipleSeleccion preguntaMS = new MultipleSeleccion("PreguntaMSPrueba",true,null,opciones);
		preguntaMS.responder(respuesta1);
		
		assertEquals(preguntaMS.getRespuesta(),"respuesta1");
	}
	
	@Test
	public void test21ObtenerLasOpcionesDeUnaPreguntaDeMultipleSeleccion(){
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
	public void test22ObtenerLasOpcionesDeUnaRespuestaMultiple(){
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


