package Interface.botonesListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Interface.MenuIngresarDatos;
import Interface.VentanaDeDatos;
import Negocio.grafo.NexoGrafoVentana;

public class ClusterListener implements ActionListener{

	private MenuIngresarDatos _menuIngresarDatos;
    private VentanaDeDatos _ventanaSecundaria;
    private NexoGrafoVentana _nexoGrafo;
    
    public ClusterListener(NexoGrafoVentana nexoGrafo, MenuIngresarDatos menuIngresarDatos) {
        this._nexoGrafo = nexoGrafo;
        this._ventanaSecundaria = null;
    	this._menuIngresarDatos = menuIngresarDatos;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (_nexoGrafo.dameListaPersonas().size() > 3) {
             _ventanaSecundaria = new VentanaDeDatos(_menuIngresarDatos.getFrame(), _nexoGrafo);
             _menuIngresarDatos.cambiarEstadoVentanaSecundaria(_ventanaSecundaria);
            _menuIngresarDatos.ventanaPrincipalVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Ingresa mas personas.", "Cantidad insuficiente.", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
