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
	
	Map <Respuesta,Respondible> opcionesPregunta3 = new HashMap <Respuesta,Respondible>();
	
	SimpleSeleccion pregunta3 = new SimpleSeleccion ("pregunta3",opcionesPregunta3,null);
	Abierta pregunta2 = new Abierta ("pregunta2",false,pregunta3);
	Abierta pregunta1 = new Abierta ("pregunta1",false,pregunta2);
	
	Proyecto proyecto1 = new Proyecto("Proyecto Cientopolis");
	Encuesta encuesta1 = Encuesta.nuevaEncuesta("Encuesta Cientopolis","13/07/2015");
	
	@Before
	public void setUp(){
		opcionesPregunta3.put(respuesta4, pregunta4);
		opcionesPregunta3.put(respuesta5, pregunta5);
		
		proyecto1.agregarEncuesta(encuesta1);
	}

	@Test
	public void test01ObtenemosElNombreDeLaEncuesta(){
		assertEquals("Encuesta Cientopolis",encuesta1.getNombre());
	}
	
	@Test
	public void test02SetearLaPrimerPreguntaDeLaEncuesta(){
		encuesta1.setPrimerPregunta(pregunta1);
		
		assertEquals(encuesta1.getPrimerPregunta(), pregunta1);
	}
	
	@Test
	public void test03SeRespondeUnaEncuestaEnSuTotalidadEIncrementaSuCantidadDeVecesRespondida() {
		
		Investigador investigador1 = new Investigador("Roberto","Gomez");
		Notificador notificador = new Notificador();
		notificador.addObserver(investigador1);
		
		Abierta pregunta6 = new Abierta("pregunta6",true,null);
		
		Abierta pregunta5 = new Abierta ("pregunta5",false,pregunta6);
		Abierta pregunta4 = new Abierta ("pregunta4",false,pregunta6);
		
		Map <Respuesta,Respondible> opcionesPregunta3 = new HashMap <Respuesta,Respondible>();
		opcionesPregunta3.put(respuesta3, pregunta4);
		opcionesPregunta3.put(respuesta4, pregunta5);
		
		Respondible pregunta3 = new SimpleSeleccion ("pregunta3",opcionesPregunta3,null);
		pregunta3 = new ImportanteEspecifica(pregunta3,notificador,encuesta1,respuesta3);
		Abierta pregunta2 = new Abierta ("pregunta2",false,pregunta3);
		Abierta pregunta1 = new Abierta ("pregunta1",false,pregunta2);
		proyecto1.agregarEncuesta(encuesta1);
		
		encuesta1.setPrimerPregunta(pregunta1);
		encuesta1.activarEncuesta();
		
		try{
			encuesta1.comenzarEncuesta();
			encuesta1.responder(respuesta1);
			encuesta1.responder(respuesta2);
			encuesta1.responder(respuesta3);
			encuesta1.responder(respuesta5);
			encuesta1.responder(respuesta6);
		}catch (Excepciones e){
			//No hay excepcion para capturar.
		}
		assertEquals(new Integer(1),encuesta1.cantidadDeVecesRespondida());
		
	}
	
	@Test
	public void test04UnaEncuestaRetornaNullSiLePidenSusTrabajos(){
		assertEquals(encuesta1.contieneAlTrabajo(encuesta1),null);
	}
	
	@Test
	public void test05UnaEncuestaRetornaUnaListaVaciaSiLePidenSusEncuestas(){
		assertTrue(encuesta1.getEncuestas().isEmpty());
	}
	
	@Test
	public void test06ObtenerTodasLasRespuestasDeUnaEncuesta() {
		
		Investigador investigador1 = new Investigador("Roberto","Gomez");
		Notificador notificador = new Notificador();
		notificador.addObserver(investigador1);
		
		Abierta pregunta6 = new Abierta("pregunta6",true,null);
		
		Abierta pregunta5 = new Abierta ("pregunta5",false,pregunta6);
		Abierta pregunta4 = new Abierta ("pregunta4",false,pregunta6);
		
		Map <Respuesta,Respondible> opcionesPregunta3 = new HashMap <Respuesta,Respondible>();
		opcionesPregunta3.put(respuesta3, pregunta4);
		opcionesPregunta3.put(respuesta4, pregunta5);
		
		Respondible pregunta3 = new SimpleSeleccion ("pregunta3",opcionesPregunta3,null);
		pregunta3 = new ImportanteEspecifica(pregunta3,notificador,encuesta1,respuesta3);
		Abierta pregunta2 = new Abierta ("pregunta2",false,pregunta3);
		Abierta pregunta1 = new Abierta ("pregunta1",false,pregunta2);
		proyecto1.agregarEncuesta(encuesta1);
		
		encuesta1.setPrimerPregunta(pregunta1);
		
		//assertEquals(6,encuesta1.getPreguntas().size());
		encuesta1.activarEncuesta();
		
		List<Respuesta> temp = new ArrayList<Respuesta>();
		
		temp.add(respuesta1);
		temp.add(respuesta2);
		temp.add(respuesta3);
		temp.add(respuesta5);
		temp.add(respuesta6);
		try{
			encuesta1.comenzarEncuesta();
			encuesta1.responder(respuesta1);
			encuesta1.responder(respuesta2);
			encuesta1.responder(respuesta3);
			encuesta1.responder(respuesta5);
			encuesta1.responder(respuesta6);
		}catch (Excepciones e){
			//No hay excepcion para capturar.
		}
		assertTrue(encuesta1.getTodasLasRespuestas().equals(temp));
		
	}
}


