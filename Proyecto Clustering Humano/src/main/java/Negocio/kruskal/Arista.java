package Negocio.kruskal;

public class Arista {
	private int _peso;
	private int _origen;
	private int _destino;
	
	public Arista(int peso, int origen, int destino) {
		this._peso = peso;
		this._origen = origen;
		this._destino = destino;
	}

	public int getPeso() {
		return _peso;
	}

	public int getOrigen() {
		return _origen;
	}

	public int getDestino() {
		return _destino;
	}

}
