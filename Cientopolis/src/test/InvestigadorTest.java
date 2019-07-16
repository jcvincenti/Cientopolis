package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import cientopolis.*;

import cientopolis.PreguntaAbierta;
import cientopolis.Encuesta;
import cientopolis.ImportanteEspecifica;
import cientopolis.Investigador;
import cientopolis.Notificador;
import cientopolis.Proyecto;
import cientopolis.Excepciones;
import cientopolis.Respondible;
import cientopolis.Respuesta;
import cientopolis.RespuestaSimple;
import cientopolis.PreguntaSimpleSeleccion;


public class InvestigadorTest {
	Investigador investigador1 = new Investigador("Pepe","Rodriguez");
	Encuesta encuesta1 = Encuesta.nuevaEncuesta("Encuesta Cientopolis","13/07/2015");
	Proyecto proyecto1 = new Proyecto("Proyecto1");
	Proyecto proyecto2 = new Proyecto("Proyecto2");
	Proyecto proyecto3 = new Proyecto("Proyecto3");
	Encuesta encuesta2 = new Encuesta ("encuesta2", "13/07/2018");
	Encuesta encuesta3 = new Encuesta ("encuesta3", "13/07/2017");
	RespuestaSimple respuesta6 = new RespuestaSimple ("respuesta6");
	RespuestaSimple respuesta5 = new RespuestaSimple ("respuesta5");
	RespuestaSimple respuesta4 = new RespuestaSimple ("respuesta4");
	RespuestaSimple respuesta3 = new RespuestaSimple ("respuesta3");
	RespuestaSimple respuesta2 = new RespuestaSimple ("respuesta2");
	RespuestaSimple respuesta1 = new RespuestaSimple ("respuesta1");
	
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
			investigador1.agregarEncuestaAProyecto(encuesta1, proyecto1);
		}catch (Excepciones e){
			//sin excepciones para capturar
		}
		assertTrue(proyecto1.contieneALaEncuesta(encuesta1));
	}
	
	@Test
	public void test06ElInvestigadorIntentaAgregarUnProyectoASiMismo(){
		try{
			investigador1.agregarSubproyectoAProyecto(proyecto1, proyecto1);
		}catch (Excepciones e){
			assertEquals(e.getMessage(),"No puede agregar un proyecto a el mismo");
		}
	}
	
	@Test
	public void test07ElInvestigadorAgregaUnSubProyecto(){
		try{
			investigador1.agregarProyecto(proyecto1);
			investigador1.agregarSubproyectoAProyecto(proyecto2, proyecto1);
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

	/*
	@Test
	public void test08OrdenarEncuestasPorProyecto() throws Excepciones{
			proyecto3.agregarEncuestaAProyecto(encuesta1, proyecto1);
			proyecto3.agregarEncuestaAProyecto(encuesta2, proyecto2);
			
			investigador1.agregarProyecto(proyecto1);
			investigador1.agregarProyecto(proyecto2);

			CriterioDeOrden criterioPorOrdenAlfabeticoDeProyectos = new PorProyecto();
			investigador1.setCriterioDeOrden(criterioPorOrdenAlfabeticoDeProyectos);
	
		assertTrue(investigador1.getListaPorCriterioElegido().get(0).equals(encuesta1));
	}
	*/
	
	@Test
	public void test09OrdenarEncuestasPorUltimasCreadas(){
		
		try{
			proyecto3.agregarEncuestaAProyecto(encuesta1, proyecto3);
			proyecto3.agregarEncuestaAProyecto(encuesta2, proyecto3);
			proyecto1.agregarEncuestaAProyecto(encuesta3, proyecto1);
			proyecto2.agregarProyectoHijoAProyecto(proyecto3, proyecto2);
			proyecto1.agregarProyectoHijoAProyecto(proyecto2, proyecto1);
			investigador1.agregarProyecto(proyecto1);
			CriterioDeOrden ultimasCreadas = new CriterioDeOrdenPorUltimasEncuestasCreadas();
			investigador1.setCriterioDeOrden(ultimasCreadas);
	
		}catch (Excepciones e){
				//no hay excepciones que capturar
		}
		
		assertEquals(investigador1.getListaPorCriterioElegido().get(0).getFechaDeCreacion(),(encuesta3.getFechaDeCreacion()));
	}

	/*
	@Test
	public void test10OrdenarEncuestasPorMasUtilizadas(){
		Investigador investigador1 = new Investigador("Roberto","Gomez");
		Notificador notificador = new Notificador();
		notificador.addObserver(investigador1);
		
		PreguntaAbierta pregunta6 = new PreguntaAbierta("pregunta6",true,null);
		
		PreguntaAbierta pregunta5 = new PreguntaAbierta ("pregunta5",false,pregunta6);
		PreguntaAbierta pregunta4 = new PreguntaAbierta ("pregunta4",false,pregunta6);
		
		Map <Respuesta,Respondible> opcionesPregunta3 = new HashMap <Respuesta,Respondible>();
		opcionesPregunta3.put(respuesta3, pregunta4);
		opcionesPregunta3.put(respuesta4, pregunta5);
		
		Respondible pregunta3 = new PreguntaSimpleSeleccion ("pregunta3",opcionesPregunta3,null);
		pregunta3 = new ImportanteEspecifica(pregunta3,notificador,encuesta1,respuesta4);
		PreguntaAbierta pregunta2 = new PreguntaAbierta ("pregunta2",false,pregunta3);
		PreguntaAbierta pregunta1 = new PreguntaAbierta ("pregunta1",false,pregunta2);
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
			proyecto3.agregarEncuestaAProyecto(encuesta1, proyecto3);
			proyecto3.agregarEncuestaAProyecto(encuesta2, proyecto3);
			proyecto1.agregarEncuestaAProyecto(encuesta3, proyecto1);
			proyecto2.agregarProyectoHijoAProyecto(proyecto3, proyecto2);
			proyecto1.agregarProyectoHijoAProyecto(proyecto2, proyecto1);
			investigador1.agregarProyecto(proyecto1);
			encuesta1.comenzarEncuesta();
			encuesta1.responder(respuesta1);
			encuesta1.responder(respuesta2);
			encuesta1.responder(respuesta3);
			encuesta1.responder(respuesta5);
			encuesta1.responder(respuesta6);
			CriterioDeOrden masUtilizadas = new EncuestasMasUtilizadas();
			investigador1.setCriterioDeOrden(masUtilizadas);
		}catch (Excepciones e){
			//No hay excepcion para capturar.
		}
		
		assertTrue(investigador1.getListaPorCriterioElegido().get(0).equals(encuesta1)); //ERROR ASSERT
	}
	*/
	
}
