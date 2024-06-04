package Interface;

import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JFrame;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import Negocio.grafo.NexoGrafoVentana;
import Negocio.persona.Persona;


public class VentanaDeDatos {

	protected JFrame _frame;
	private NexoGrafoVentana _nexoGrafo;
	private JScrollPane _scrollPane;
	private JTextPane _textPane;

	public VentanaDeDatos(JFrame frame, NexoGrafoVentana nexoGrafo) {
		this.inicializarComponentes(frame, nexoGrafo);
		this.initialize();
	}
	
	public void setVisible(boolean flag) {
		_scrollPane.setVisible(flag);
	}
	
	private void inicializarComponentes(JFrame frame, NexoGrafoVentana nexoGrafo) {
		_nexoGrafo = nexoGrafo;
		_frame = frame;
		inicializarScrollPane();
	}
	
	private void initialize() {
		_frame.getContentPane().add(_scrollPane);
	}
	
	private void inicializarScrollPane() {
		_scrollPane = new JScrollPane();
		_scrollPane.setBounds(73, 259, 806, 233);
		_scrollPane.setBackground(Color.black);
		
		_textPane = new JTextPane();
		_nexoGrafo.armarListaGrupos(2);
		
		StringBuilder texto = crearTextoGrupos();
		
		_textPane.setText(texto.toString());
		
		_scrollPane.setViewportView(_textPane);
	}

	private StringBuilder crearTextoGrupos() {
		int cantGrupos = _nexoGrafo.cantGrupos();
		StringBuilder texto = new StringBuilder();
		
		for(int i = 0; i<cantGrupos; i++) {
			texto.append("Grupo "+i+":"+"\n");
			LinkedList<Persona> personas = _nexoGrafo.dameListaGrupo(i);
			for(Persona p : personas) {
				texto.append(p.getNombre());
				texto.append("\n");
			}
		}
		
		return texto;
	}

	
}
