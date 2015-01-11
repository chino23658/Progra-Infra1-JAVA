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


public class InicioDirectoImplicitoPriori extends JFrame {

	private JPanel contentPane;
	private JTextField txtPriori;
	private JTextArea txtMensaje;
	private JComboBox comboBoxEmi;
	private JComboBox comboBoxRece;

	/**
	 * Create the frame.
	 */
	public InicioDirectoImplicitoPriori(final int tamaño) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 357, 400);
		contentPane = new ImagenFondo();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 207, 321, 110);
		contentPane.add(scrollPane);
		
		txtMensaje = new JTextArea();
		scrollPane.setViewportView(txtMensaje);
		
		JLabel lblEmisor = new JLabel("Emisor");
		lblEmisor.setForeground(Color.YELLOW);
		lblEmisor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmisor.setBounds(41, 33, 83, 19);
		contentPane.add(lblEmisor);
		
		JLabel lblProceso = new JLabel("Proceso");
		lblProceso.setForeground(Color.YELLOW);
		lblProceso.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblProceso.setBounds(62, 63, 83, 19);
		contentPane.add(lblProceso);
		
		comboBoxEmi = new JComboBox();
		comboBoxEmi.setBounds(155, 64, 146, 20);
		contentPane.add(comboBoxEmi);
		int total = 1;
		while (total != VentanaConfiguracion.cantidad+1){
			comboBoxEmi.addItem(total);
			total++;
		}
		
		JLabel lblReceptor = new JLabel("Receptor");
		lblReceptor.setForeground(Color.YELLOW);
		lblReceptor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblReceptor.setBounds(41, 108, 83, 26);
		contentPane.add(lblReceptor);
		
		comboBoxRece = new JComboBox();
		comboBoxRece.setBounds(155, 113, 146, 20);
		contentPane.add(comboBoxRece);
		comboBoxRece.addItem(CrearAlias.Alias.get(0));
		
		JLabel lblPrioridad = new JLabel("Prioridad");
		lblPrioridad.setForeground(Color.YELLOW);
		lblPrioridad.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrioridad.setBounds(41, 149, 83, 20);
		contentPane.add(lblPrioridad);
		
		txtPriori = new JTextField();
		txtPriori.setBounds(155, 151, 146, 20);
		contentPane.add(txtPriori);
		txtPriori.setColumns(10);
		
		JLabel lblMensaje = new JLabel("Mensaje");
		lblMensaje.setForeground(Color.YELLOW);
		lblMensaje.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblMensaje.setBounds(10, 190, 83, 19);
		contentPane.add(lblMensaje);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = txtMensaje.getText();
				int largo = texto.length();
				if(tamaño!=0 && largo>tamaño){
					JOptionPane.showMessageDialog(null, "El tamaño del mensaje excede"+'\n'+"a la cantidad de caracteres permitidos"+'\n'+"para esta la configuracion la cual"+'\n'+"debe de ser de un tamaño de: "+tamaño);
				}
				else{
					if (comboBoxEmi.getSelectedItem().equals(CrearAlias.Alias.get(1))){
						JOptionPane.showMessageDialog(null, "No se puede enviar un mensaje hacia sí mismo");
						InicioDirectoImplicitoPriori otra = new InicioDirectoImplicitoPriori(tamaño);
						otra.setVisible(true);
						setVisible(false);
					}
					else{
						int prio = Integer.parseInt(txtPriori.getText());
						RecibirDirecto.Valida(comboBoxEmi.getSelectedItem());
						Principal.PushPrioridad(comboBoxEmi.getSelectedItem(), comboBoxRece.getSelectedItem(),CrearAlias.Alias.get(0),txtMensaje.getText(),prio);
						if (VentanaConfiguracion.emisor.PrimerNodo.contiene == "Blocking"){
							RecibirDirecto.Escribir(comboBoxEmi.getSelectedItem(), "Proceso Desbloqueado");
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
		});
		btnEnviar.setBounds(62, 327, 89, 23);
		contentPane.add(btnEnviar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(192, 328, 89, 23);
		contentPane.add(btnSalir);
		setTitle("Inicia Simulacion");
	}

}
