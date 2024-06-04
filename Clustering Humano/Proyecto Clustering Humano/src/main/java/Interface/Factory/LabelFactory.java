package Interface.Factory;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class LabelFactory {
    private JLabel _frameImg;

    public LabelFactory(JLabel frameImg) {
        this._frameImg = frameImg;
    }

    public JLabel crearLabel(String texto, int x, int y, int ancho, int alto, int fontSize) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        label.setForeground(Color.WHITE);
        label.setBounds(x, y, ancho, alto);
        _frameImg.add(label);
        return label;
    }
}
