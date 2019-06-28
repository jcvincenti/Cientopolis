package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cientopolis.Abierta;
import cientopolis.Encuesta;
import cientopolis.ImportanteEspecifica;
import cientopolis.Investigador;
import cientopolis.Notificador;
import cientopolis.Proyecto;
import cientopolis.Excepciones;
import cientopolis.Respondible;
import cientopolis.Respuesta;
import cientopolis.Simple;
import cientopolis.SimpleSeleccion;


public class InvestigadorTest {
	Investigador investigador1 = new Investigador("Pepe","Rodriguez");
	Encuesta encuesta1 = Encuesta.nuevaEncuesta("Encuesta Cientopolis","13/07/2015");
	Proyecto proyecto1 = new Proyecto("Proyecto1");
	Proyecto proyecto2 = new Proyecto("Proyecto2");
	Proyecto proyecto3 = new Proyecto("Proyecto3");
	Encuesta encuesta2 = new Encuesta ("encuesta2", "13/07/2018");
	Encuesta encuesta3 = new Encuesta ("encuesta3", "13/07/2017");
	Simple respuesta6 = new Simple ("respuesta6");
	Simple respuesta5 = new Simple ("respuesta5");
	Simple respuesta4 = new Simple ("respuesta4");
	Simple respuesta3 = new Simple ("respuesta3");
	Simple respuesta2 = new Simple ("respuesta2");
	Simple respuesta1 = new Simple ("respuesta1");
	
	@Test
	public void test01CuandoSeCreaUnInvestigadorNoTieneProyectos(){
		assertTrue(investigador1.getProyectos().isEmpty());
	}
	
	@Test
	public void test02ObtenerElNombreDelInvestigador(){
		assertEquals(investigador1.getNombre(), "Pepe");
	}
	
	@Test
	public void test03ObtenerElApellidoDelInvestigador(){
		assertEquals(investigador1.getApellido(), "Rodriguez");
	}
	
	@Test
	public void test04ElInvestigadorAgregaUnProyecto(){
		investigador1.agregarProyecto(proyecto1);
		assertTrue(investigador1.getProyectos().contains(proyecto1));
	}
	
	@Test 
	public void test05ElInvestigadorObtieneSuProyectoPorElNombre(){
		investigador1.agregarProyecto(proyecto1);
		assertEquals(investigador1.getProyecto("Proyecto1"),proyecto1);
	}
	
	@Test
	public void test05ElInvestigadorAgregaUnaEncuestaAUnProyecto(){
		try{
			investigador1.agregarTrabajoAProyecto(encuesta1, proyecto1);
		}catch (Excepciones e){
			//sin excepciones para capturar
		}
		assertTrue(proyecto1.contieneAlTrabajo(encuesta1));
	}
	
	@Test
	public void test06ElInvestigadorIntentaAgregarUnProyectoASiMismo(){
		try{
			investigador1.agregarTrabajoAProyecto(proyecto1, proyecto1);
		}catch (Excepciones e){
			assertEquals(e.getMessage(),"No puede agregar un proyecto a el mismo");
		}
	}
	
	@Test
	public void test07ElInvestigadorAgregaUnSubProyecto(){
		try{
			investigador1.agregarProyecto(proyecto1);
			investigador1.agregarTrabajoAProyecto(proyecto2, proyecto1);
		}catch (Excepciones e){
			//sin excepciones para capturar
		}
		
		assertTrue(investigador1.getProyectos().contains(proyecto1));
	}
	
	@Test
	public void test07ObtenerLosProyectosDeUnInvestigador(){		
		investigador1.agregarProyecto(proyecto1);
		
		assertTrue(investigador1.getProyectos().contains(proyecto1));
	}
	
	@Test
	public void test08OrdenarEncuestasPorFechaDeCreacion(){
		
		try{
			proyecto3.agregarTrabajoAProyecto(encuesta1, proyecto3);
			proyecto3.agregarTrabajoAProyecto(encuesta2, proyecto3);
			proyecto1.agregarTrabajoAProyecto(encuesta3, proyecto1);
			proyecto2.agregarTrabajoAProyecto(proyecto3, proyecto2);
			proyecto1.agregarTrabajoAProyecto(proyecto2, proyecto1);
			investigador1.agregarProyecto(proyecto1);
		}catch (Excepciones e){
				//no hay excepciones que capturar
		}
		
		investigador1.setUltimasEncuestasCreadas();
		assertTrue(investigador1.getEncuestasOrdenadas().get(0).equals(encuesta1));
	}
	
	@Test
	public void test09OrdenarEncuestasPorProyecto(){
		
		try{
			proyecto3.agregarTrabajoAProyecto(encuesta1, proyecto3);
			proyecto3.agregarTrabajoAProyecto(encuesta2, proyecto3);
			proyecto1.agregarTrabajoAProyecto(encuesta3, proyecto1);
			proyecto2.agregarTrabajoAProyecto(proyecto3, proyecto2);
			proyecto1.agregarTrabajoAProyecto(proyecto2, proyecto1);
			investigador1.agregarProyecto(proyecto1);
		}catch (Excepciones e){
				//no hay excepciones que capturar
		}
		
		investigador1.setOrdenPorProyecto();
		assertTrue(investigador1.getEncuestasOrdenadas().get(0).equals(encuesta3));
	}
	
	@Test
	public void test10OrdenarEncuestasPorMasUtilizadas(){
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
		pregunta3 = new ImportanteEspecifica(pregunta3,notificador,encuesta1,respuesta4);
		Abierta pregunta2 = new Abierta ("pregunta2",false,pregunta3);
		Abierta pregunta1 = new Abierta ("pregunta1",false,pregunta2);
		proyecto1.agregarEncuesta(encuesta1);
		
		encuesta1.setPrimerPregunta(pregunta1);
		
		//assertEquals(6,encuesta1.getPreguntas().size());
		encuesta1.activarEncuesta();
		
		Proyecto proyecto1 = new Proyecto("Proyecto1");
		Proyecto proyecto2 = new Proyecto("Proyecto2");
		Proyecto proyecto3 = new Proyecto("Proyecto3");
		Encuesta encuesta2 = new Encuesta ("encuesta2", "13/07/2018");
		Encuesta encuesta3 = new Encuesta ("encuesta3", "13/07/2017");
		
		try{
			proyecto3.agregarTrabajoAProyecto(encuesta1, proyecto3);
			proyecto3.agregarTrabajoAProyecto(encuesta2, proyecto3);
			proyecto1.agregarTrabajoAProyecto(encuesta3, proyecto1);
			proyecto2.agregarTrabajoAProyecto(proyecto3, proyecto2);
			proyecto1.agregarTrabajoAProyecto(proyecto2, proyecto1);
			investigador1.agregarProyecto(proyecto1);
			encuesta1.comenzarEncuesta();
			encuesta1.responder(respuesta1);
			encuesta1.responder(respuesta2);
			encuesta1.responder(respuesta3);
			encuesta1.responder(respuesta5);
			encuesta1.responder(respuesta6);
		}catch (Excepciones e){
			//No hay excepcion para capturar.
		}
		
		investigador1.setEncuestasMasUtilizadas();
		assertTrue(investigador1.getEncuestasOrdenadas().get(0).equals(encuesta1));
	}
}
