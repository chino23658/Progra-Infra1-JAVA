import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import Nodo.Alias;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class CrearAlias extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JComboBox comboBoxProcesos;
	static ArrayList Alias = new ArrayList();


	/**
	 * Create the frame.
	 */
	public CrearAlias(final int tamaño) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 318, 199);
		contentPane = new ImagenFondo();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(Color.YELLOW);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombre.setBounds(37, 21, 85, 27);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(145, 26, 126, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblProceso = new JLabel("Proceso");
		lblProceso.setForeground(Color.YELLOW);
		lblProceso.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProceso.setBounds(37, 72, 85, 20);
		contentPane.add(lblProceso);
		
		comboBoxProcesos = new JComboBox();
		comboBoxProcesos.setBounds(145, 74, 126, 20);
		contentPane.add(comboBoxProcesos);
		int c = 1;
		while (c!=VentanaConfiguracion.cantidad+1){
			comboBoxProcesos.addItem(c);
			c++;
		}
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (VentanaConfiguracion.manejoCola == "FIFO"){
					Alias.add(txtNombre.getText());
					Alias.add(comboBoxProcesos.getSelectedItem());
					InicioDirectoImplicito inicia = new InicioDirectoImplicito(tamaño);
					inicia.setVisible(true);
					setVisible(false);
				}
				else{
					Alias.add(txtNombre.getText());
					Alias.add(comboBoxProcesos.getSelectedItem());
					InicioDirectoImplicitoPriori inicia = new InicioDirectoImplicitoPriori(tamaño);
					inicia.setVisible(true);
					setVisible(false);
				}
			}
		});
		btnCrear.setBounds(96, 116, 89, 23);
		contentPane.add(btnCrear);
		setTitle("Crear Alias");
	}
}
