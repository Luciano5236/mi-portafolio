package Interface.Factory;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

public class BotonFactory {
    private JLabel _frameImg;

    public BotonFactory(JLabel _frameImg) {
        this._frameImg = _frameImg;
    }

    public JButton crearBoton(String texto, int x, int y, int ancho, int alto) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, ancho, alto);
        boton.setFont(new Font("Tahoma", Font.BOLD, 12));
        boton.setBackground(Color.WHITE);
        _frameImg.add(boton);
        
        return boton;
    }
}
