package Negocio.kruskal;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Negocio.grafo.GrafoCompletoConPesos;
import Negocio.grafo.NexoGrafoVentana;
import Negocio.persona.Persona;

public class KruskalTest {

	private NexoGrafoVentana nexoGrafo;
	private GrafoCompletoConPesos grafo;
	private Kruskal kruskal;
    private Persona persona1, persona2, persona3, persona4, persona5;

    @Before
    public void setUp() {
        // Configuración inicial para las pruebas
        persona1 = Persona.crearPersona("Persona1", 5, 4, 3, 2);
        persona2 = Persona.crearPersona("Persona2", 5, 4, 3, 2);
        persona3 = Persona.crearPersona("Persona3", 1, 1, 1, 1);
        persona4 = Persona.crearPersona("Persona4", 1, 2, 3, 2);
        persona5 = Persona.crearPersona("Persona5", 4, 3, 4, 2);

        nexoGrafo = new NexoGrafoVentana();
        
        nexoGrafo.agregarPersona(persona1);
        nexoGrafo.agregarPersona(persona2);
        nexoGrafo.agregarPersona(persona3);
        nexoGrafo.agregarPersona(persona4);
        nexoGrafo.agregarPersona(persona5);

        grafo = nexoGrafo.armarGrafo();
        kruskal = new Kruskal(grafo);
        /**
         * CAMINO GENERADO
         * 	origen-destino-peso
         * 	0 1 -> 0
			0 4 -> 3
			1 4 -> 3
			2 3 -> 4
			3 4 -> 5
			0 3 -> 6
			1 3 -> 6
			2 4 -> 9
			0 2 -> 10
			1 2 -> 10
         */
    }
	
	@Test
	public void testAGMCorrecto() {
		int [] agm = kruskal.armarAGM();
		int[] esperado = {1, 4, 3, 4, 4};
		
		assertArrayEquals(esperado,agm);
}
	
	@Test
	public void testSizeCaminoGenerado() {
		ArrayList<Arista> caminos = kruskal.getCaminos();
		kruskal.armarAGM();
		
		assertEquals(10, caminos.size());
	}
	
	@Test
	public void testCaminoEspecificoCorrecto() {
		kruskal.armarAGM();
		ArrayList<Arista> caminos = kruskal.getCaminos();

	    assertTrue(caminoDeseadoTrue(caminos));
	}

	@Test
	public void testCaminoEspecificoIncorrecto() {
		kruskal.armarAGM();
		ArrayList<Arista> caminos = kruskal.getCaminos();

	    assertFalse(caminoNoDeseadoFalse(caminos));
	}
	
	private boolean caminoDeseadoTrue(ArrayList<Arista> caminos) {
		return 	 caminos.stream()
	            .anyMatch(c -> c.getOrigen() == 0 
	            		&& c.getDestino() == 1 
	            		&& c.getPeso() == 0);
	}
	
	private boolean caminoNoDeseadoFalse(ArrayList<Arista> caminos) {
		return 	 caminos.stream()
	            .anyMatch(c -> c.getOrigen() == 0 
	            		&& c.getDestino() == 1 
	            		&& c.getPeso() == 1);
	}
}
