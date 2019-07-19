package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cientopolis.PreguntaAbierta;
import cientopolis.Encuesta;
import cientopolis.PreguntaImportante;
import cientopolis.Investigador;
import cientopolis.Notificador;
import cientopolis.Respondible;
import cientopolis.Respuesta;
import cientopolis.RespuestaSimple;
import cientopolis.PreguntaSimpleSeleccion;

public class NotificadorTest {

	Notificador notificador = new Notificador();
	Investigador investigador1 = new Investigador ("pepe","rodriguez");
	Investigador investigador2 = new Investigador ("pepe","gonzalez");

	@Test
	public void test01UnNotificadorRecienCreadoNoTieneObservers(){
		assertTrue(notificador.getObservers().isEmpty());
	}
	
	@Test
	public void test02UnNotificadorAgregaUnObserver(){
		notificador.addObserver(investigador1);
		assertTrue(notificador.getObservers().contains(investigador1));
	}
	
	@Test
	public void test03UnNotificadorEliminaUnObserver(){
		notificador.addObserver(investigador1);
		notificador.addObserver(investigador2);
		notificador.removeObserver(investigador1);
		assertFalse(notificador.getObservers().contains(investigador1));
	}
	
	@Test
	public void test04SeRespondeUnaPreguntaImportanteYElNotificadorAvisaASusObservers(){
		notificador.addObserver(investigador1);
		Encuesta encuesta1 = Encuesta.nuevaEncuesta("Encuesta Cientopolis","13/07/2015");
		RespuestaSimple respuesta4 = new RespuestaSimple ("respuesta4");
		RespuestaSimple respuesta3 = new RespuestaSimple ("respuesta3");
		PreguntaAbierta pregunta5 = new PreguntaAbierta ("pregunta5",false,null);
		PreguntaAbierta pregunta4 = new PreguntaAbierta ("pregunta4",false,null);
		Map <Respuesta,Respondible> opcionesPregunta3 = new HashMap <Respuesta,Respondible>();
		opcionesPregunta3.put(respuesta3, pregunta4);
		opcionesPregunta3.put(respuesta4, pregunta5);
		
		Respondible pregunta3 = new PreguntaSimpleSeleccion ("pregunta3",opcionesPregunta3,null);
		pregunta3 = new PreguntaImportante(pregunta3,notificador,encuesta1);
		
		pregunta3.responder(respuesta3);
	}
}
