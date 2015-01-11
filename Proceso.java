import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Proceso extends JFrame {

	private JPanel contentPane;
	public static JTextArea txtProceso;
	public static int id = 0;

	/**
	 * Create the frame.
	 */
	public Proceso(int numero) {
		id = numero;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Proceso " + numero);
		setResizable(false);
		contentPane.setLayout(null);		
		
		txtProceso = new JTextArea();
		txtProceso.setBounds(10, 11, 414, 239);
		txtProceso.setEditable(false);
		contentPane.add(txtProceso);		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 239);
		contentPane.add(scrollPane);
		
		VentanaConfiguracion.idVentanas.InsertaVentana(txtProceso, numero);
		
		JButton btnVerCola = new JButton("Ver Cola");
		btnVerCola.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCola verCola = new VentanaCola();
				verCola.setVisible(true);				
			}
		});
		btnVerCola.setBounds(166, 261, 89, 23);
		contentPane.add(btnVerCola);
	}
}
