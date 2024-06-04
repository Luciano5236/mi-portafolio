package Negocio.grafo;

import java.util.ArrayList;
import java.util.LinkedList;

import Negocio.persona.Persona;

public class NexoGrafoVentana {
	private ArrayList<Persona> _listaPersonas;
	private LinkedList<LinkedList<Persona>> _gruposDePersonas;
	
	public NexoGrafoVentana() {
		_listaPersonas = new ArrayList<Persona>();
		_gruposDePersonas = new LinkedList<LinkedList<Persona>>();
	}
	
	public ArrayList<Persona> dameListaPersonas() {
		return _listaPersonas;
	}
	
	public void agregarPersona(Persona nuevaPersona) {
		if (!puedeAgregarPersona(nuevaPersona)) {
			throw new IllegalArgumentException("La persona esta repetida , debe de ingresar otro diferente ");
		}
		else {
		 _listaPersonas.add(nuevaPersona);
		}
	}

	public boolean puedeAgregarPersona(Persona nuevaPersona) {
		return !existeNombre(nuevaPersona.getNombre());
	}

	boolean existeNombre(String nombre) { // quito el private para testear y es alcansable dentro de las clases  del paquete 
		for(int i = 0; i<_listaPersonas.size(); i++)
			if(_listaPersonas.get(i).getNombre().equals(nombre))
				return true;
		
		return false;
	}
	
	public void eliminarPersona(String nombre) {
	    _listaPersonas.removeIf(persona -> persona.getNombre().equals(nombre));
	}
	
	public void armarListaGrupos(int cantGrupos) {
		_gruposDePersonas = new LinkedList<LinkedList<Persona>>();
		GrafoCompletoConPesos grafo = armarGrafo();
		grafo.convertirEnAGM();
		
		for(int i = 0; i<cantGrupos-1; i++)
			grafo.quitarAristaMayorPeso();
		
		int[][] matriz = grafo.getMatriz();

		LinkedList<Integer> grupo1 = armarGrupo(matriz, 0, 0); //se arma el primer grupo
		
		agregarGrupoDePersonas(_gruposDePersonas, grupo1);
		
		for(int j = 0; j<cantGrupos-1; j++) { //se arma el resto de los grupos
			LinkedList<Integer> otroGrupo = buscarGrupo(matriz, _gruposDePersonas);
			agregarGrupoDePersonas(_gruposDePersonas, otroGrupo);
		}
	}
	
	private void agregarGrupoDePersonas(LinkedList<LinkedList<Persona>> gruposDePersonas, LinkedList<Integer> grupo) {
		LinkedList<Persona> personas = new LinkedList<Persona>();
		for(int i = 0; i < _listaPersonas.size(); i++) {
			if(grupo.contains(i)) {
				Persona persona = _listaPersonas.get(i);
				personas.add(persona);
			}
		}
		
		gruposDePersonas.add(personas);
	}

	public LinkedList<Persona> dameListaGrupo(int numeroGrupo) { //ac� deber�a retornar en texto, los grupos que se hicieron
		 return _gruposDePersonas.get(numeroGrupo);
	}

	private LinkedList<Integer> buscarGrupo(int[][] matriz, LinkedList<LinkedList<Persona>> grupos) {
		LinkedList<Integer> grupo = armarGrupo(matriz, 1, 0);
		LinkedList<Persona> personasDelGrupo = armarGrupoDePersonas(grupo);
		int nodo = 1;
		
		for(int i = 0; i<grupos.size(); i++) {
			while(personasDelGrupo.contains(grupos.get(i).getFirst())) {	//si entre las personas del grupo se encuentra alguna de otro grupo, se sigue buscando
				nodo++;
				grupo = armarGrupo(matriz, nodo, 0);
				personasDelGrupo = armarGrupoDePersonas(grupo);
			}
		}
		
		return grupo;
	}

	private LinkedList<Persona> armarGrupoDePersonas(LinkedList<Integer> grupo) {
		LinkedList<Persona> personas = new LinkedList<Persona>();
		for(int i = 0; i < _listaPersonas.size(); i++) {
			if(grupo.contains(i)) {
				Persona persona = _listaPersonas.get(i);
				personas.add(persona);
			}
		}
		
		return personas;
	}

	public GrafoCompletoConPesos armarGrafo() {
		int cantPersonas = dameCantidadPersonas();
		GrafoCompletoConPesos nuevoGrafo = new GrafoCompletoConPesos(cantPersonas);
		
		for(int i = 0; i<cantPersonas; i++) {
			for(int j = 0; j<cantPersonas; j++) {
				if(i!=j)
					nuevoGrafo.agregarArista(i, j, _listaPersonas.get(i).calcularSimilitud(_listaPersonas.get(j))); 
			}
		}
		
		return nuevoGrafo;
	}
	
	LinkedList<Integer> armarGrupo(int[][] matrizGrafo, int nodoX, int nodoY) { // quito el private para usarlo en el test y el alcance esta solo lo que esta dentro del paquete
		LinkedList<Integer> grupo = new LinkedList<Integer>();
		nodosAlcanzables(matrizGrafo, nodoX, nodoY, grupo);
		return grupo;
	}
	
	private int dameCantidadPersonas() {
		return _listaPersonas.size();
	}
	
	private void nodosAlcanzables(int[][] matrizGrafo, int nodoX, int nodoY, LinkedList<Integer> alcanzados){
		if(nodoX >= matrizGrafo.length || nodoY >= matrizGrafo.length) {
			return;
		}
		
		if(matrizGrafo[nodoX][nodoY] >= 0 && nodoX!=nodoY) {
			if(!alcanzados.contains(nodoX))
				alcanzados.add(nodoX);
			if(!alcanzados.contains(nodoY)) {
				alcanzados.add(nodoY);
				nodosAlcanzables(matrizGrafo, nodoY, 0, alcanzados);
			}
			nodosAlcanzables(matrizGrafo, nodoX, nodoY+1, alcanzados);
		}
		else
			nodosAlcanzables(matrizGrafo, nodoX, nodoY+1, alcanzados);
	}

	public int cantGrupos() {
		return _gruposDePersonas.size();
	}
}
