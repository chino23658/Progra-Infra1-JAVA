import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class InicioDirectoPriori extends JFrame {

	private JPanel contentPane;
	private JTextField txtPrioridad;
	private JComboBox comboBoxEmisor;
	private JComboBox comboBoxReceptor;
	private JTextArea txtMensaje;

	/**
	 * Create the frame.
	 */
	public InicioDirectoPriori(final int tamaño) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 316, 424);
		contentPane = new ImagenFondo();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 224, 280, 110);
		contentPane.add(scrollPane);
		
		txtMensaje = new JTextArea();
		scrollPane.setViewportView(txtMensaje);
		
		JLabel lblEmisor = new JLabel("Emisor");
		lblEmisor.setForeground(Color.YELLOW);
		lblEmisor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmisor.setBounds(32, 26, 80, 24);
		contentPane.add(lblEmisor);
		
		JLabel lblProceso = new JLabel("Proceso");
		lblProceso.setForeground(Color.YELLOW);
		lblProceso.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblProceso.setBounds(53, 54, 70, 17);
		contentPane.add(lblProceso);
		
		comboBoxEmisor = new JComboBox();
		comboBoxEmisor.setBounds(133, 54, 138, 20);
		contentPane.add(comboBoxEmisor);
		int procesos = 1;
		while (procesos != VentanaConfiguracion.cantidad+1){
			comboBoxEmisor.addItem(procesos);
			procesos ++;
		}
		
		JLabel lblReceptor = new JLabel("Receptor");
		lblReceptor.setForeground(Color.YELLOW);
		lblReceptor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblReceptor.setBounds(32, 92, 80, 24);
		contentPane.add(lblReceptor);
		
		JLabel lblProceso_1 = new JLabel("Proceso");
		lblProceso_1.setForeground(Color.YELLOW);
		lblProceso_1.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblProceso_1.setBounds(53, 127, 70, 24);
		contentPane.add(lblProceso_1);
		
		comboBoxReceptor = new JComboBox();
		comboBoxReceptor.setBounds(133, 131, 138, 20);
		contentPane.add(comboBoxReceptor);
		int cant = 1;
		while(cant != VentanaConfiguracion.cantidad+1){
			comboBoxReceptor.addItem(cant);
			cant ++;
		}
		
		JLabel lblPrioridad = new JLabel("Prioridad");
		lblPrioridad.setForeground(Color.YELLOW);
		lblPrioridad.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrioridad.setBounds(32, 168, 80, 24);
		contentPane.add(lblPrioridad);
		
		txtPrioridad = new JTextField();
		txtPrioridad.setBounds(133, 172, 138, 20);
		contentPane.add(txtPrioridad);
		txtPrioridad.setColumns(10);
		
		JLabel lblMensaje = new JLabel("Mensaje");
		lblMensaje.setForeground(Color.YELLOW);
		lblMensaje.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblMensaje.setBounds(10, 209, 70, 17);
		contentPane.add(lblMensaje);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = txtMensaje.getText();
				int largo = texto.length();
				if(tamaño!=0 && largo>tamaño){
					JOptionPane.showMessageDialog(null, "El tamaño del mensaje excede"+'\n'+"a la cantidad de caracteres permitidos"+'\n'+"para esta la configuracion la cual"+'\n'+"debe de ser de un tamaño de: "+tamaño);
					txtMensaje.setText("");
				}
				else{
					if(comboBoxEmisor.getSelectedItem().equals(comboBoxReceptor.getSelectedItem())){
						JOptionPane.showMessageDialog(null, "No se puede enviar un mensaje hacia sí mismo");
						InicioDirectoPriori otra = new InicioDirectoPriori(tamaño);
						otra.setVisible(true);
						setVisible(false);
					}
					else{
						int pri = Integer.parseInt(txtPrioridad.getText());	
						if (VentanaConfiguracion.emisor.PrimerNodo.contiene.equals("Prueba de llegada")){							
							Principal.PushPrioridad(comboBoxEmisor.getSelectedItem(), "", comboBoxReceptor.getSelectedItem(), txtMensaje.getText(), pri);
							Opciones opci = new Opciones();
							opci.setVisible(true);
							setVisible(false);
						}
						else{						
							Principal.PushPrioridad(comboBoxEmisor.getSelectedItem(), "", comboBoxReceptor.getSelectedItem(), txtMensaje.getText(), pri);
							if (VentanaConfiguracion.emisor.PrimerNodo.contiene == "Blocking"){
								RecibirDirecto.Escribir(comboBoxEmisor.getSelectedItem(), "Proceso Desbloqueado");
								Opciones opci = new Opciones();
								opci.setVisible(true);
								setVisible(false);
							}
							else{
								Opciones opci = new Opciones();
								opci.setVisible(true);
								setVisible(false);
							}
						}
					}
				}
			}
		});
		btnEnviar.setBounds(45, 345, 89, 23);
		contentPane.add(btnEnviar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(175, 345, 89, 23);
		contentPane.add(btnSalir);
		setTitle("Inicio Simulacion");
	}
}
