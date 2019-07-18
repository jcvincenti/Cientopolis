package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cientopolis.Encuesta;
import cientopolis.Excepciones;
import cientopolis.Proyecto;

public class ProyectoTest {
	
	Proyecto proyecto1 = new Proyecto("proyecto re piola");
	Proyecto proyecto2 = new Proyecto("Proyecto no tan piola");
	Proyecto proyecto3 = new Proyecto("Proyecto...3");
	Encuesta encuesta1 = Encuesta.nuevaEncuesta("encuesta piola","13/07/2015");
	

	@Before
	public void setUp() throws Exception {
		proyecto1.agregarEncuesta(encuesta1);
	}

	@Test
	public void test01ObtenerElNombreDeUnProyecto(){
		assertEquals(proyecto1.getNombre(), "proyecto re piola");
	}
	
	@Test
	public void test02AgregarUnaEncuestaAUnProyecto(){
		assertTrue(proyecto1.contieneALaEncuesta(encuesta1));
		assertTrue(proyecto1.getEncuestas().contains(encuesta1));
	}
	
	@Test
	public void test03AgregarUnaSubproyectoAUnProyecto(){
		proyecto1.agregarProyectoHijo(proyecto2);
		assertTrue(proyecto1.contieneAlProyecto(proyecto2));
		assertTrue(proyecto1.getProyectos().contains(proyecto2));
	}

	@Test
	public void test04AgregarUnSubProyectoAUnProyecto() throws Excepciones{
		try{
			proyecto1.agregarProyectoHijoAProyecto(proyecto2, proyecto1);
		}catch (Excepciones e){}
		assertTrue(proyecto1.getProyectos().contains(proyecto2));
	}

	@Test
	public void test05AgregarUnProyectoASiMismoTiraUnaExcepcion(){
		try{
			proyecto1.agregarProyectoHijoAProyecto(proyecto1, proyecto1);
		}catch (Excepciones e){
			assertEquals(e.getMessage(), "No puede agregar un proyecto a el mismo");
		}
	}	

	@Test
	public void test06AgregarUnaEncuestaAUnSubroyecto(){
		Proyecto proyecto1 = new Proyecto("Proyecto1");
		Proyecto proyecto2 = new Proyecto("Proyecto2");
		Proyecto proyecto3 = new Proyecto("Proyecto3");
		Encuesta encuesta2 = new Encuesta ("encuesta2", "13/07/2015");

		try{
		proyecto2.agregarProyectoHijoAProyecto(proyecto3, proyecto2);
		proyecto1.agregarProyectoHijoAProyecto(proyecto2, proyecto1);
		proyecto1.agregarEncuestaAProyecto(encuesta1, proyecto1);
		proyecto1.agregarEncuestaAProyecto(encuesta2, proyecto2);
		
		}catch (Excepciones e){}
		
		assertTrue(proyecto1.getEncuestasTotales().contains(encuesta1) && proyecto1.getEncuestasTotales().contains(encuesta2)); //se verifica desde el proyecto "padre", si la encuesta está dentro de los subproyectos
		assertTrue(proyecto1.getProyectos().contains(proyecto2));
		assertTrue(proyecto1.getProyectos().contains(proyecto3));
		assertFalse(proyecto3.getEncuestasTotales().contains(encuesta1)); //vemos si el proyecto hijo restante, no contiene la encuesta.
		assertTrue(proyecto2.getEncuestas().contains(encuesta2));
	}
	
	@Test
	public void test07ObtenerLasPrimeras5EncuestasConMayorCantidadDeRespuestas(){
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
				proyecto1.getEncuestasFinalizadas().contains(encuesta2)&&
				proyecto1.getEncuestasFinalizadas().contains(encuesta3)&&
				proyecto1.getEncuestasFinalizadas().contains(encuesta4)&& 
				proyecto1.getEncuestasFinalizadas().contains(encuesta5));
	}
	
	@Test
	public void test08ObtenerLaUnicaEncuestaDelTop5(){
		Proyecto proyecto1 = Proyecto.nuevoProyecto("proyectoX");
		proyecto1.agregarEncuesta(encuesta1);
		List<Encuesta> listaDePrueba = new ArrayList<Encuesta>();
		listaDePrueba.add(encuesta1);
		
		assertEquals(proyecto1.getEncuestasFinalizadas(),listaDePrueba);
	}
	
	@Test
	public void test09BuscarUnProyectoInexistenteParaAgregarUnaEncuestaTiraUnaExcepcion(){
		try{
			Proyecto proyecto4 = new Proyecto("Proyecto4");
			proyecto1.buscarProyectoAAgregarEncuesta(encuesta1, proyecto4);
		}catch (Excepciones e){
			assertEquals(e.getMessage(), "El proyecto no forma parte de los sub-proyectos");
		}
	}
	
	@Test
	public void test10BuscarUnProyectoInexistenteParaAgregarleUnSubProyectoTiraUnaExcepcion(){
		try{
			Proyecto proyecto4 = new Proyecto("Proyecto4");
			proyecto1.buscarProyectoAAgregarProyectoHijo(proyecto1, proyecto4);
		}catch (Excepciones e){
			assertEquals(e.getMessage(), "El proyecto no forma parte de los sub-proyectos");
		}
	}
	
	@Test
	public void test11VerificarSiUnProyectoEstaTotalmenteFinalizado(){
		Encuesta encuesta2 = Encuesta.nuevaEncuesta("Encuesta Cientopolis","13/07/2015");
		Proyecto proyecto1 = new Proyecto("Proyecto1");
		Proyecto proyecto2 = new Proyecto("Proyecto2");
		encuesta1.cerrarEncuesta();
		encuesta2.cerrarEncuesta();
		try{
		proyecto1.agregarEncuestaAProyecto(encuesta1, proyecto1);
		proyecto2.agregarEncuestaAProyecto(encuesta2, proyecto2);
		}catch (Excepciones e){
			//sin excepciones para capturar
		}
		assertTrue(proyecto1.getEstaFinalizada());
	}
}
	

