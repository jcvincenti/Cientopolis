package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cientopolis.Encuesta;
import cientopolis.Excepciones;
import cientopolis.Proyecto;

public class ProyectoTest {
	
	Encuesta encuesta1 = Encuesta.nuevaEncuesta("Encuesta Cientopolis","13/07/2015");
	
	@Test
	public void test1ObtenerLasEncuestasDeUnProyecto(){

		Proyecto proyecto1 = Proyecto.nuevoProyecto("proyectoX");
		proyecto1.agregarEncuesta(encuesta1);
		
		
		assertTrue(proyecto1.getTodasLasEncuestas().contains(encuesta1));
	}
	
	@Test
	public void test2ObtenerLasPrimeras5EncuestasConMayorCantidadDeRespuestas(){
		Proyecto proyecto1 = Proyecto.nuevoProyecto("proyectoX");
		proyecto1.agregarEncuesta(encuesta1);
		Encuesta encuesta2 = new Encuesta ("encuesta2", "13/07/2015");
		Encuesta encuesta3 = new Encuesta ("encuesta3", "13/07/2015");
		Encuesta encuesta4 = new Encuesta ("encuesta4", "13/07/2015");
		Encuesta encuesta5 = new Encuesta ("encuesta5", "13/07/2015");
		Encuesta encuesta6 = new Encuesta ("encuesta6", "13/07/2015");
		proyecto1.agregarEncuesta(encuesta2);
		proyecto1.agregarEncuesta(encuesta3);
		proyecto1.agregarEncuesta(encuesta4);
		proyecto1.agregarEncuesta(encuesta5);
		proyecto1.agregarEncuesta(encuesta6);
		
		assertTrue(proyecto1.getEncuestasFinalizadas().contains(encuesta1)&&
		proyecto1.getTodasLasEncuestas().contains(encuesta2)&&
		proyecto1.getTodasLasEncuestas().contains(encuesta3)&&
		proyecto1.getTodasLasEncuestas().contains(encuesta4)&& 
		proyecto1.getTodasLasEncuestas().contains(encuesta5));
	}
	
	@Test
	public void test3ObtenerLaUnicaEncuestaDelTop5(){
		Proyecto proyecto1 = Proyecto.nuevoProyecto("proyectoX");
		proyecto1.agregarEncuesta(encuesta1);
		List<Encuesta> listaDePrueba = new ArrayList<Encuesta>();
		listaDePrueba.add(encuesta1);
		
		assertEquals(proyecto1.getEncuestasFinalizadas(),listaDePrueba);
	}
	
	@Test
	public void test4AgregarUnaEncuestaAUnSubproyecto(){
		Proyecto proyecto1 = new Proyecto("Proyecto1");
		Proyecto proyecto2 = new Proyecto("Proyecto2");
		Proyecto proyecto3 = new Proyecto("Proyecto3");
		try{
		proyecto3.agregarTrabajoAProyecto(encuesta1, proyecto3);
		proyecto2.agregarTrabajoAProyecto(proyecto3, proyecto2);
		proyecto1.agregarTrabajoAProyecto(proyecto2, proyecto1);
		}catch (Excepciones e){
			//no hay excepciones que capturar
		}
		assertTrue(proyecto1.getTodasLasEncuestas().contains(encuesta1));
		assertFalse(proyecto1.getTodasLasEncuestas().contains(proyecto1));
		assertTrue(proyecto1.getTrabajos().contains(encuesta1));
		assertTrue(proyecto1.getTrabajos().contains(proyecto1));
		assertTrue(proyecto1.getProyectos().contains(proyecto3));
		assertFalse(proyecto1.getProyectos().contains(encuesta1));
		
	}
	
	@Test
	public void test5AgregarUnProyectoASiMismoTiraUnaExcepcion(){
		Proyecto proyecto1 = new Proyecto("Proyecto1");
		try{
			proyecto1.agregarTrabajoAProyecto(proyecto1, proyecto1);
		}catch (Excepciones e){
			assertEquals(e.getMessage(), "No puede agregar un proyecto a el mismo");
		}
	}
	
	@Test
	public void test6VerificarSiUnProyectoEstaTotalmenteFinalizado(){
		Encuesta encuesta2 = Encuesta.nuevaEncuesta("Encuesta Cientopolis","13/07/2015");
		Proyecto proyecto1 = new Proyecto("Proyecto1");
		Proyecto proyecto2 = new Proyecto("Proyecto2");
		encuesta1.cerrarEncuesta();
		encuesta2.cerrarEncuesta();
		try{
		proyecto1.agregarTrabajoAProyecto(encuesta1, proyecto1);
		proyecto2.agregarTrabajoAProyecto(encuesta2, proyecto2);
		}catch (Excepciones e){
			//sin excepciones para capturar
		}
		assertTrue(proyecto1.getEstaFinalizada());
	}
}
