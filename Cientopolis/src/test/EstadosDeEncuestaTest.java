package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cientopolis.PreguntaAbierta;
import cientopolis.Encuesta;
import cientopolis.Excepciones;
import cientopolis.RespuestaSimple;

public class EstadosDeEncuestaTest {
	
	Encuesta encuesta1 = Encuesta.nuevaEncuesta("Encuesta Cientopolis","13/07/2015");
	PreguntaAbierta pregunta2 = new PreguntaAbierta ("pregunta2",false,null);
	PreguntaAbierta pregunta1 = new PreguntaAbierta ("pregunta1",false,pregunta2);
	RespuestaSimple respuesta1 = new RespuestaSimple ("respuesta1");
	
	@Test
	public void test01DebeActivarseLaEncuestaParaPoderResponderla(){
		try{
		encuesta1.responder(respuesta1);
		}catch (Excepciones e){
			assertEquals(e.getMessage(), "El estado de la encuesta debe ser 'Activa'. No se puede comenzar la encuesta");
		}
	}
	
	@Test
	public void test02DebeComenzarseLaEncuestaParaPoderResponderla(){
		encuesta1.activarEncuesta();
		try{
		encuesta1.responder(respuesta1);
		}catch (Excepciones e){
			assertEquals(e.getMessage(), "Debe comenzar la encuesta para poder responderla");
		}
	}
	
	@Test
	public void test03ParaPoderAgregarseUnaPreguntaLaEncuestaDebeEstarEnEdicion(){
		encuesta1.activarEncuesta();
		try{
			encuesta1.agregarPregunta(pregunta1, pregunta2);
		}catch (Excepciones e){
			assertEquals(e.getMessage(),"El estado de la encuesta debe ser 'EnEdicion'. No se puede agregar la pregunta");
		}
	}
	
	@Test
	public void test04DebeActivarseLaEncuestaParaPoderComenzarla(){
		try{
			encuesta1.comenzarEncuesta();
		}catch (Excepciones e){
			assertEquals(e.getMessage(),"El estado de la encuesta debe ser 'Activa'. No se puede comenzar la encuesta");
		}
	}
	
	@Test
	public void test05SetearEncuestaEnEdicionParaAgregarPreguntas(){
		encuesta1.activarEncuesta();
		try{
			encuesta1.agregarPregunta(pregunta1, pregunta2);
		}catch (Excepciones e){
			assertEquals(e.getMessage(),"El estado de la encuesta debe ser 'EnEdicion'. No se puede agregar la pregunta");
		}
		encuesta1.editarEncuesta();
		try{
			encuesta1.agregarPregunta(pregunta1, pregunta2);
		}catch (Excepciones e){
			//sin excepciones para capturar
		}
	}
	
	@Test
	public void test06UnaEncuestaCerradaNoSePuedeResponder(){
		encuesta1.cerrarEncuesta();
		try{
			encuesta1.responder(respuesta1);
		}catch (Excepciones e){
			assertEquals(e.getMessage(),"El estado de la encuesta debe ser 'Activa'. No se puede comenzar la encuesta");
		}
	}
}
