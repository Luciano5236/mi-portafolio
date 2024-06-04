package Interface.botonesListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Interface.MenuIngresarDatos;
import Negocio.grafo.NexoGrafoVentana;
import Negocio.persona.Persona;

public class AgregarPersonaListener implements ActionListener {
    private MenuIngresarDatos _menuIngresarDatos;
	private JTextField _textFieldNombre;
    private NexoGrafoVentana _nexoGrafo;
    private JTable _tabla;
    private Persona _persona;
    
    public AgregarPersonaListener(JTextField _textFieldNombre, NexoGrafoVentana nexoGrafo, JTable table, MenuIngresarDatos menuIngresarDatos) {
        this._textFieldNombre = _textFieldNombre;
        this._nexoGrafo = nexoGrafo;
        this._tabla = table;
        this._menuIngresarDatos = menuIngresarDatos;
        this._persona= Persona.crearPersona("", 0, 0, 0, 0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (_textFieldNombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debes ingresar un nombre.", "Nombre vacio", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Persona nuevaPersona = _menuIngresarDatos.crearPersona();

            if (!_persona.EsUnNombreValido(nuevaPersona.getNombre())) { 
                JOptionPane.showMessageDialog(null, "Puso un numero o caracter no valido, debe ingresar un nombre.", "Numero o caracter no valido", JOptionPane.INFORMATION_MESSAGE);
            } else if (_nexoGrafo.puedeAgregarPersona(nuevaPersona)) {
            	_nexoGrafo.agregarPersona(nuevaPersona);

                Object[] rowData = {
                        nuevaPersona.getNombre(),
                        nuevaPersona.getInteresDeportes(),
                        nuevaPersona.getInteresMusica(),
                        nuevaPersona.getInteresEspectaculos(),
                        nuevaPersona.getInteresCiencia()
                };
                DefaultTableModel model = (DefaultTableModel) _tabla.getModel();
                model.addRow(rowData);
            } else {
                JOptionPane.showMessageDialog(null, "Ya ingresaste ese nombre.", "Nombre repetido", JOptionPane.INFORMATION_MESSAGE);
            }

            _menuIngresarDatos.resetInputs();
        }
    }
}
