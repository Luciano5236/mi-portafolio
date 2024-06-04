package Interface.botonesListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Interface.MenuIngresarDatos;
import Negocio.grafo.NexoGrafoVentana;

public class EliminarListener implements ActionListener {
	
    private MenuIngresarDatos _menuIngresarDatos;
    private JTable _table;
    private JTextField _textFieldNombre;
    private DefaultTableModel _tableModel;
    private NexoGrafoVentana _nexoGrafo;
    
    public EliminarListener(JTextField textFieldNombre, JTable table, DefaultTableModel tableModel, NexoGrafoVentana nexoGrafo ,MenuIngresarDatos menuIngresarDatos) {
        this._menuIngresarDatos = menuIngresarDatos;
        this._table = table;
        this._tableModel = tableModel;
        this._nexoGrafo = nexoGrafo;
        this._textFieldNombre = textFieldNombre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) _table.getModel();
        String nombre = _textFieldNombre.getText();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (_tableModel.getValueAt(i, 0).toString().equals(nombre)) {
            	_tableModel.removeRow(i);
            }
        }
        _nexoGrafo.eliminarPersona(nombre);
        _menuIngresarDatos.resetInputs();
    }
}