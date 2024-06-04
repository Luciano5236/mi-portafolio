package Negocio.grafo;

import java.util.ArrayList;

import Negocio.kruskal.Kruskal;

public class GrafoCompletoConPesos {
	
	private Kruskal _kruskal;
	private int[][] _matrizAdy;
	   
	public GrafoCompletoConPesos(int cantVertices) {
		_matrizAdy = new int[cantVertices][cantVertices];
	}
	
	public int getNumVertices() {
		return _matrizAdy.length;
	}
	
	public int tamano() {
		return _matrizAdy.length;
	}
	
	public int getNumAristas() {
		return sumaConsecutiva(getNumVertices());
	}
	
	public int getPeso(int i, int j) {
		return _matrizAdy[i][j];
	}
		    
	public void agregarArista(int x, int y, int peso) {
		if(x<0 || y<0)
			throw new RuntimeException("No se puede ingresar valores negativos.");
		if(xyValidos(x, y)) {
			_matrizAdy[x][y] = peso;
			_matrizAdy[y][x] = peso;
		}
	}

	boolean xyValidos(int x, int y) {
		return x!=y && _matrizAdy.length>=x && _matrizAdy.length>=y;
	}
	 
	public void eliminarArista(int x, int y) {
		if (xyValidos(x, y)) {
			_matrizAdy[x][y] = -1;
			_matrizAdy[y][x] = -1; // La matriz es simétrica
		}
	}
		   
	public boolean existeArista(int x, int y) {
		if (xyValidos(x, y)) {
			return _matrizAdy[x][y] > 0;
		}
		return false;
	}

	    public ArrayList<Integer> obtenerVecinos(int vertice) {
	        ArrayList<Integer> vecinos = new ArrayList<>();
	        
	        for (int i = 0; i < _matrizAdy.length; i++) {
	            if (_matrizAdy[vertice][i] > 0) {
	                vecinos.add(i);
	            }
	        }
	        
	        return vecinos;
	    }

		public void convertirEnAGM() {
			_kruskal = new Kruskal(this);
			_matrizAdy = _kruskal.convertirEnAGM();
		}

		private int sumaConsecutiva(int length) {
			return length * (length + 1) / 2;
		}
		    
		public void quitarAristaMayorPeso(){
	    	int aristaMayorPeso = 0;
	    	int aristaMayorPesoX = 0;
	    	int aristaMayorPesoY = 0;
		    	
	    	for(int i = 0; i<_matrizAdy.length; i++)
	    		for(int j = i+1; j<_matrizAdy.length; j++) {
	    			if(i!=j && _matrizAdy[i][j]>aristaMayorPeso) { //guarda la arista de mayor peso
	    					aristaMayorPeso = _matrizAdy[i][j];
	    					aristaMayorPesoX = i;
	    					aristaMayorPesoY = j;
	    			}
	    	}
	    	
	    	_matrizAdy[aristaMayorPesoX][aristaMayorPesoY] = -1; //se elimina la arista de mayor peso
	    	_matrizAdy[aristaMayorPesoY][aristaMayorPesoX] = -1;
	    }
		
		public int getNodo(int i, int j) {
			return _matrizAdy[i][j];
		}

		public int[][] getMatriz() {
			return _matrizAdy;
		}

}