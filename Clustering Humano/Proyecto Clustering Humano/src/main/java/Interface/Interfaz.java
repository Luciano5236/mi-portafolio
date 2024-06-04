package Interface;

import javax.swing.JFrame;

public class Interfaz extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Interfaz() {
		initialize();
		MenuIngresarDatos.crear(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		new JFrame();
		
		setBounds(100, 100, 1024, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
        setLocationRelativeTo(null);
        
        setTitle("Clustering Humano");
	}

	public JFrame getFrame() {
		return this;
	}
}


	

