package Interface.botonesListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Interface.MenuIngresarDatos;

public class VolverListener implements ActionListener {
    private MenuIngresarDatos _menuIngresarDatos;
    
    public VolverListener(MenuIngresarDatos menuIngresarDatos) {
        _menuIngresarDatos = menuIngresarDatos;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        _menuIngresarDatos.ventanaPrincipalVisible(true);
    }
}
