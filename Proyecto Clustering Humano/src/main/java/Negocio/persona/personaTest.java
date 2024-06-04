package Negocio.persona;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import Negocio.grafo.NexoGrafoVentana;

public class personaTest {
	
    private HashMap<String, Persona> personas;
    private Persona persona1, persona2, persona3;
    private Persona nombreValido, nombreIncorrecto1, nombreIncorrecto2;

    @Before
    public void setUp() {
        personas = new HashMap<>();
        persona1 = Persona.crearPersona("Pepe", 2, 4, 3, 5);
        persona2 = Persona.crearPersona("Juan", 3, 4, 5, 2);
        persona3 = Persona.crearPersona("Pedro", 3, 4, 5, 2);
        nombreValido = Persona.crearPersona( "Pesñ", 2, 3, 4, 5);
        nombreIncorrecto1 = Persona.crearPersona( "Pes45", 2, 3, 4, 5);
        nombreIncorrecto2 = Persona.crearPersona( "Pes%&", 2, 3, 4, 5);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testAgregarPersonaRepetida() throws Exception {
    	NexoGrafoVentana diccio = new NexoGrafoVentana();
    	diccio.agregarPersona(persona1);
    	diccio.agregarPersona(persona1);
    }

    @Test
    public void testNoExistePersona() {
        assertFalse(personas.containsKey(persona2.getNombre()));
    }
    
    @Test
    public void testExistePersona() {
        personas.put(persona2.getNombre(), persona2);
        assertTrue(personas.containsKey(persona2.getNombre()));
    }
    
    @Test
    public void testCalculaSimilitudResultadoCero() {
    	assertEquals(0, persona2.calcularSimilitud(persona3));
    }
    
    @Test
    public void testCalculaSimilitudResultadoEspecifico() {
    	assertEquals(6, persona1.calcularSimilitud(persona3));
    }
    
    @Test
    public void testNombreValidoLetras() {
    	
    	
		assertTrue(nombreValido.EsUnNombreValido(nombreValido.getNombre()));  
    }
    
    @Test
    public void testNombreNOValidoLetrasYNumeros() {
    	assertFalse(nombreIncorrecto1.EsUnNombreValido(nombreIncorrecto1.getNombre()));
    }
    
    @Test
    public void testNombreNoValidoNumeros() {
    	assertFalse(persona1.EsUnNombreValido(nombreIncorrecto2.getNombre()));
    }
    
}
