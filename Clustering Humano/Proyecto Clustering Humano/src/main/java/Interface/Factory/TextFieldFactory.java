package Interface.Factory;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TextFieldFactory {
	private final JLabel _frameImg;
	
    public TextFieldFactory(JLabel frameImg) {
        this._frameImg = frameImg;
    }
    
    public JTextField crearTextField(int x, int y, int ancho, int alto) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, ancho, alto);
        _frameImg.add(textField);
        return textField;
    }

    public JComboBox<String> crearComboBox(List<String> opciones, int x, int y, int ancho, int alto) {
        JComboBox<String> comboBox = new JComboBox<>(opciones.toArray(new String[0]));
        comboBox.setBounds(x, y, ancho, alto);
        _frameImg.add(comboBox);
        return comboBox;
    }
}
