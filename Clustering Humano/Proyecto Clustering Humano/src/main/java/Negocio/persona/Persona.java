package Negocio.persona;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Persona {
	private String _nombre ; 
	private int _interesDeportes;
	private int _interesMusica;
	private int _interesEspectaculos;
	private int _interesCiencia;
	
	private Persona(String nombre, int interesDeportes, int interesMusica, int interesEspectaculos, int interesCiencia) {
		this._nombre = nombre;
		this._interesDeportes = interesDeportes;
		this._interesMusica = interesMusica;
		this._interesEspectaculos = interesEspectaculos;
		this._interesCiencia = interesCiencia;
	}

	public static Persona crearPersona(String nombre, int interesDeportes, int interesMusica, int interesEspectaculos, int interesCiencia) {
		return new Persona(nombre, interesDeportes, interesMusica, interesEspectaculos, interesCiencia);
	}

	public String getNombre() {
		return _nombre;
	}
	
	public int getInteresDeportes() {
		return _interesDeportes;
	}

	public int getInteresEspectaculos() {
		return _interesEspectaculos;
	}

	public int getInteresMusica() {
		return _interesMusica;
	}

	public int getInteresCiencia() {
		return _interesCiencia;
	}
	
	public boolean EsUnNombreValido (String nombrePersona) { // regla para que sea un nombre valido y que no se ingrese numeros o caracteres 
		String patron = "^[a-zA-Z � � � � � �\\s]+$";
		Pattern pattern = Pattern.compile(patron);
		Matcher matcher = pattern.matcher(nombrePersona);
		return matcher.matches();
	}

	public int calcularSimilitud(Persona personaB) {
        return  Math.abs(this.getInteresDeportes() - personaB.getInteresDeportes()) +
                Math.abs(this.getInteresMusica() - personaB.getInteresMusica()) +
                Math.abs(this.getInteresEspectaculos() - personaB.getInteresEspectaculos()) +
                Math.abs(this.getInteresCiencia() - personaB.getInteresCiencia());
    }
	
}
