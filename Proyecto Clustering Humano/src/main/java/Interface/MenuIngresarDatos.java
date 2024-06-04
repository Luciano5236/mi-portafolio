package Interface;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Interface.botonesListener.AgregarPersonaListener;
import Interface.botonesListener.ClusterListener;
import Interface.botonesListener.EliminarListener;
import Interface.botonesListener.VolverListener;
import Interface.Factory.BotonFactory;
import Interface.Factory.LabelFactory;
import Interface.Factory.TextFieldFactory;

import javax.swing.JComboBox;
import java.util.Arrays;
import java.util.List;
import java.awt.Font;
import java.awt.Image;

import Negocio.grafo.NexoGrafoVentana;
import Negocio.persona.Persona;

public class MenuIngresarDatos {
	private JFrame _frame;
	
	private JTextField _textFieldNombre;
	
    private NexoGrafoVentana _nexoGrafo;
    
    private ImageIcon _fondoImg;
    private Image _iconImagen;
    
    private JLabel _frameImg;
    private JLabel _labelNombre;
    private JLabel _labelDeportes;
    private JLabel _labelMusica;
    private JLabel _labelEspectaculos;
    private JLabel _labelCiencia;
    private JLabel _titleLabel;

    private JComboBox<String> _interesDeportes;
    private JComboBox<String> _interesMusica;
    private JComboBox<String> _interesEspectaculos;
    private JComboBox<String> _interesCiencia;

    private JScrollPane _scrollPane;
    private JPanel _panel;
    private JTable _table;

    private JButton _btnAgregar;
    private JButton _btnCluster;
    private JButton _btnVolver;
    private JButton _btnEliminar;
    
    private DefaultTableModel _tableModel;
    private VentanaDeDatos _ventanaSecundaria;

    protected static MenuIngresarDatos crear(JFrame frame) {
		return new MenuIngresarDatos(frame);	
	}
    
    public MenuIngresarDatos(JFrame frame) {
		_frame = frame;
		initialize();
	}
   
    public JFrame getFrame() {
		return _frame;
	}
    
	//ESTO ERA PRIVADO
	public Persona crearPersona() {
		Persona nuevaPersona = 
		    		Persona.crearPersona(
		    			_textFieldNombre.getText(), 
		    			Integer.parseInt(_interesDeportes.getSelectedItem().toString()), 
		    			Integer.parseInt(_interesMusica.getSelectedItem().toString()), 
		    			Integer.parseInt(_interesEspectaculos.getSelectedItem().toString()), 
		    			Integer.parseInt(_interesCiencia.getSelectedItem().toString())
		    		);
		return nuevaPersona;
	}
	
	public void resetInputs() {
		_textFieldNombre.setText("");
		_interesDeportes.setSelectedIndex(0);
		_interesMusica.setSelectedIndex(0);
		_interesEspectaculos.setSelectedIndex(0);
		_interesCiencia.setSelectedIndex(0);
	}

	public void cambiarEstadoVentanaSecundaria(VentanaDeDatos secundaria) {
		_ventanaSecundaria = secundaria;
	}
	
	public void ventanaPrincipalVisible(boolean flag) {
		setMenuVisible(flag);
		_btnVolver.setVisible(!flag);
	    _ventanaSecundaria.setVisible(!flag);
	}
    
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		iniciarComponentes();
		
		actionListener();
	}

	private void iniciarComponentes() {
		_nexoGrafo = new NexoGrafoVentana();
		
		iniciarImagenes();	
		iniciarLabels();
		iniciarTextFields();     
		iniciarPanelNombres();
		iniciarBotones();
	}

	private void actionListener() {
		_btnAgregar.addActionListener(new AgregarPersonaListener(_textFieldNombre, _nexoGrafo, _table, this));
		_btnCluster.addActionListener(new ClusterListener(_nexoGrafo, this));
		_btnVolver.addActionListener(new VolverListener(this));
		_btnEliminar.addActionListener(new EliminarListener(_textFieldNombre, _table, _tableModel, _nexoGrafo, this));
	}

	private void iniciarImagenes() {
		_fondoImg = new ImageIcon(this.getClass().getResource("/assets/imagen/inicio.jpg"));
		_frameImg = new JLabel(_fondoImg);
		_frameImg.setSize(1024,768);
		_frame.getContentPane().add(_frameImg);
		
		_iconImagen = new ImageIcon(this.getClass().getResource("/assets/imagen/icono.png")).getImage();
		Image iconResize = _iconImagen.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
		_frame.setIconImage(iconResize);
	}
	
	private void iniciarLabels() {
	    LabelFactory labelFactory = new LabelFactory(_frameImg);
	    _labelNombre = labelFactory.crearLabel("Nombre:", 80, 300, 100, 50, 20);
	    _labelDeportes = labelFactory.crearLabel("Deportes", 102, 360, 95, 18, 15);
	    _labelMusica = labelFactory.crearLabel("Musica", 102, 385, 95, 18, 15);
	    _labelEspectaculos = labelFactory.crearLabel("Espectaculos", 102, 410, 95, 18, 15);
	    _labelCiencia = labelFactory.crearLabel("Ciencia", 102, 435, 95, 18, 15);

	}

	private void iniciarBotones() {
	    BotonFactory botonFactory = new BotonFactory(_frameImg);
	    _btnAgregar = botonFactory.crearBoton("AGREGAR", 100, 480, 95, 30);
	    _btnCluster = botonFactory.crearBoton("CLUSTER", 330, 480, 95, 30);
	    _btnVolver = botonFactory.crearBoton("VOLVER", 73, 540, 95, 30);
	    _btnVolver.setVisible(false);
	    _btnEliminar = botonFactory.crearBoton("ELIMINAR", 215, 480, 95, 30);
	}
	
	private void iniciarTextFields() {
		TextFieldFactory textFieldFactory = new TextFieldFactory(_frameImg);
		_textFieldNombre = textFieldFactory.crearTextField(180, 310, 250, 30);
		
		List<String> opciones = Arrays.asList("1", "2", "3", "4", "5");
		
		_interesDeportes = textFieldFactory.crearComboBox(opciones, 250, 360, 37, 22);
		_interesMusica = textFieldFactory.crearComboBox(opciones, 250, 385, 37, 22);
		_interesEspectaculos = textFieldFactory.crearComboBox(opciones, 250, 410, 37, 22);
		_interesCiencia = textFieldFactory.crearComboBox(opciones, 250, 435, 37, 22);
	}

	private void setMenuVisible(boolean flag) {
		_btnAgregar.setVisible(flag);
		_interesCiencia.setVisible(flag);
		_interesMusica.setVisible(flag);
		_interesEspectaculos.setVisible(flag);
		_interesDeportes.setVisible(flag);
		_labelNombre.setVisible(flag);
		_scrollPane.setVisible(flag);
		_textFieldNombre.setVisible(flag);
		_titleLabel.setVisible(flag);
		_btnCluster.setVisible(flag);
		_labelCiencia.setVisible(flag);
		_labelDeportes.setVisible(flag);
		_labelEspectaculos.setVisible(flag);
		_labelMusica.setVisible(flag);
		_panel.setVisible(flag);
		_btnEliminar.setVisible(flag);
	}
	
	private void iniciarPanelNombres() {
	    configurarTabla();
	    configurarTitulo();
	    configurarPanel();
	}
	
	private void configurarTabla() {
	    _tableModel = new DefaultTableModel();
	    _table = new JTable(_tableModel) {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // Esto desactiva la edición de las celdas
	        }
	    };

	    _tableModel.addColumn("Nombre");
	    _tableModel.addColumn("Deportes");
	    _tableModel.addColumn("Musica");
	    _tableModel.addColumn("Espectaculo");
	    _tableModel.addColumn("Ciencia");
	}

	private void configurarTitulo() {
	    _titleLabel = new JLabel("DATOS INGRESADOS");
	    _titleLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
	    _titleLabel.setHorizontalAlignment(JLabel.CENTER); // Alinea el título al centro
	}

	private void configurarPanel() {
	    _scrollPane = new JScrollPane(_table);
	    _scrollPane.setBounds(450, 310, 520, 150);

	    _panel = new JPanel();
	    _panel.setLayout(new BorderLayout());
	    _panel.add(_titleLabel, BorderLayout.NORTH); // Coloca el título en la parte superior
	    _panel.add(_scrollPane, BorderLayout.CENTER); // Coloca la lista en el centro

	    _panel.setBounds(450, 310, 520, 200);
	    _frameImg.add(_panel);
	}
}
