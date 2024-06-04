package Negocio.kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Negocio.grafo.GrafoCompletoConPesos;

public class Kruskal {
	
	private GrafoCompletoConPesos _grafo;
  	private int [] _padres;
  	private int _nodos;
  	private int _aristas;
  	private ArrayList<Arista> _arcos;
  
  	public Kruskal (GrafoCompletoConPesos grafo) {
  		this._grafo = grafo;
  		this._padres = new int[grafo.tamano()];
  		this._nodos = grafo.getNumVertices();
  		this._aristas = grafo.getNumAristas();
  		this._arcos = new ArrayList<Arista>();
  	}
  	
  	public ArrayList<Arista> getCaminos(){
  		return this._arcos;
  	}
  	
  	public int[] armarAGM() {
		int agm_arcos = 0;
		int agm_contArist = 0;
		
		inicializarPadres();
		armarCaminos(_grafo);
		ordenarCaminos();
		
		while((agm_arcos<this._nodos-1) && (agm_contArist<_aristas)) {
			int origen = _arcos.get(agm_contArist).getOrigen();
			int destino = _arcos.get(agm_contArist).getDestino();
			if(buscaAlPadre(origen)!=buscaAlPadre(destino)) {
				unir(origen, destino);
				agm_arcos++;
			}
			
			agm_contArist++;
		}
		
		return _padres;
	}
	
  	private int buscaAlPadre (int nodo ) { // busca al Padre del nodo
		 if (_padres[nodo] == nodo ) {
			 return nodo; 
		 }
		 return buscaAlPadre(_padres[nodo]);
	 }
	
	 private void unir (int origen , int destino ) { // une el origen y destino , une dos nodos 
		 int unionOrigen = buscaAlPadre (origen);
		 int unionDestino = buscaAlPadre(destino);
		 _padres[unionOrigen] = unionDestino;
	 }

	private void ordenarCaminos(){
		Collections.sort(_arcos, new Comparator<Arista>(){
			@Override
			public int compare(Arista o1, Arista o2) {
				return o1.getPeso()-o2.getPeso();
			}
		});
	}
	
	private void inicializarPadres() {
		for(int i = 0; i<_padres.length; i++) {
			_padres[i] = i;
		}
	}

	private void armarCaminos(GrafoCompletoConPesos grafo) {
		for(int i = 0; i<grafo.getNumVertices(); i++) {
			for(int j = i+1; j<grafo.getNumVertices(); j++) {
				int origen = i;
				int destino = j;
				int peso = grafo.getPeso(i, j);
				_arcos.add(new Arista(peso, origen, destino));
			}
		}
	}

	public int[][] convertirEnAGM() {
		int[] listaPadres = armarAGM();
		int[][] matrizNueva = new int[_grafo.getNumVertices()][_grafo.getNumVertices()];
		matrizNueva = inicializarMatriz(matrizNueva);
		
		for(int i = 0; i<listaPadres.length; i++) {
			matrizNueva[i][listaPadres[i]] = _grafo.getNodo(i,listaPadres[i]);
			matrizNueva[listaPadres[i]][i] = _grafo.getNodo(listaPadres[i],i);
		}
		return matrizNueva;	//ahora el grafo es un arbol minimo, se eliminaron las demas aristas
	}
	
	private int[][] inicializarMatriz(int[][] matrizNueva) {
		for(int i = 0; i<matrizNueva.length; i++) {
			for(int j = i+1; j<matrizNueva.length; j++) {
				matrizNueva[i][j] = -1;
				matrizNueva[j][i] = -1;
			}
		}
		return matrizNueva;
	}
}
