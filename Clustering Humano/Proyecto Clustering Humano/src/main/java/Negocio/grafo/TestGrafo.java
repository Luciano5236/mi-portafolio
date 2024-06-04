package Negocio.grafo;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Negocio.persona.Persona;

import java.util.ArrayList;
import java.util.List;

public class TestGrafo {

    private Persona persona1, persona2, persona3, persona4, persona5;
    private NexoGrafoVentana nexo;
    private GrafoCompletoConPesos grafo;
    
    @Before
    public void setUp() {
    	nexo = new NexoGrafoVentana();
    	grafo = new GrafoCompletoConPesos(3);
        persona1 = Persona.crearPersona("Persona1", 5, 4, 3, 2);
        persona2 = Persona.crearPersona("Persona2", 4, 3, 2, 1);
        persona3 = Persona.crearPersona("Persona3", 3, 2, 1, 5);
        persona4 = Persona.crearPersona("Persona4", 5, 5, 5, 5);
        persona5 = Persona.crearPersona("Persona5", 1, 1, 1, 2);
        
   
      List<Persona> personas = new ArrayList<>(); // creo lista de personas 
      personas.add(persona1); // los agrego para probarlos en el test 
      personas.add(persona2);
      personas.add(persona3);
      personas.add(persona5);
      personas.add(persona4);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAgregarPersonaRepetida() throws Exception {
    	NexoGrafoVentana diccio = new NexoGrafoVentana();
    	diccio.agregarPersona(persona1);
    	diccio.agregarPersona(persona1);
    }
    
    @Test
    public void testAgregarAristaIncorrecta() {
    	assertFalse(grafo.xyValidos(1, 1));
    	assertFalse(grafo.xyValidos(5, 1));
    	assertFalse(grafo.xyValidos(1, 5));
    }

    @Test
    public void testAgregarAristaCorrecta() {
    	assertTrue(grafo.xyValidos(1, 2));
    	assertTrue(grafo.xyValidos(2, 1));
    }
    
    @Test 
    public void testNoAgregaPersonasRepetidas() {
    	nexo.agregarPersona(persona1);
    	assertFalse(nexo.puedeAgregarPersona(persona1));
    }
    
    @Test 
    public void testPuedeAgregarPersonaNoRepetida() {
    	nexo.agregarPersona(persona1);
    	assertTrue(nexo.puedeAgregarPersona(persona2));
    }
    
    @Test 
    public void testEliminarPersonaOK() {
    	nexo.agregarPersona(persona1);
    	nexo.agregarPersona(persona2);
    	nexo.eliminarPersona(persona1.getNombre());
    	assertFalse(nexo.existeNombre(persona1.getNombre()));		
    }  
}