import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;

import Nodo.NodoProceso;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class InicioDirectoImplicito extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBoxReceptor;
	private JComboBox comboBoxEmisor;
	private JTextArea txtMensaje ;

	/**
	 * Create the frame.
	 */
	public InicioDirectoImplicito(final int tamaño) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 356);
		contentPane = new ImagenFondo();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 157, 334, 116);
		contentPane.add(scrollPane);
		
		txtMensaje = new JTextArea();
		scrollPane.setViewportView(txtMensaje);
		
		JLabel lblEmisor = new JLabel("Emisor");
		lblEmisor.setForeground(Color.YELLOW);
		lblEmisor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmisor.setBounds(46, 11, 84, 27);
		contentPane.add(lblEmisor);
		
		JLabel lblProceso = new JLabel("Proceso");
		lblProceso.setForeground(Color.YELLOW);
		lblProceso.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblProceso.setBounds(68, 45, 77, 17);
		contentPane.add(lblProceso);
		
		comboBoxEmisor = new JComboBox();
		comboBoxEmisor.setBounds(155, 45, 141, 20);
		contentPane.add(comboBoxEmisor);
		int i = 1;
		while(i!=VentanaConfiguracion.cantidad+1){
			comboBoxEmisor.addItem(i);
			i++;
		}
		
		JLabel lblReceptor = new JLabel("Receptor");
		lblReceptor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblReceptor.setForeground(Color.YELLOW);
		lblReceptor.setBounds(46, 91, 90, 27);
		contentPane.add(lblReceptor);
		
		comboBoxReceptor = new JComboBox();
		comboBoxReceptor.setBounds(155, 96, 141, 20);
		contentPane.add(comboBoxReceptor);
		comboBoxReceptor.addItem(CrearAlias.Alias.get(0));
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = txtMensaje.getText();
				int largo = texto.length();
				if(tamaño!=0 && largo>tamaño){
					JOptionPane.showMessageDialog(null, "El tamaño del mensaje excede"+'\n'+"a la cantidad de caracteres permitidos"+'\n'+"para esta la configuracion la cual"+'\n'+"debe de ser de un tamaño de: "+tamaño);
				}
				else{
					if (comboBoxEmisor.getSelectedItem().equals(CrearAlias.Alias.get(1))){
						JOptionPane.showMessageDialog(null, "No se puede enviar un mensaje hacia sí mismo");
						InicioDirectoImplicito otra = new InicioDirectoImplicito(tamaño);
						otra.setVisible(true);
						setVisible(false);
					}
					else{
						RecibirDirecto.Valida(comboBoxEmisor.getSelectedItem());
						Principal.esCola.Push(comboBoxEmisor.getSelectedItem(), comboBoxReceptor.getSelectedItem(),CrearAlias.Alias.get(0), txtMensaje.getText(),0);
						if(VentanaConfiguracion.emisor.PrimerNodo.contiene == "Prueba de llegada")
						{	System.out.println("Es aqui");
							System.out.println(CrearAlias.Alias.get(1));
							if(InicioDirecto.Verifica(CrearAlias.Alias.get(1))){								
								RecibirDirecto.Escribir(CrearAlias.Alias.get(1), "Ha llegado un mensaje para este proceso");
								System.out.println("Ha llegado un mensaje para: "+CrearAlias.Alias.get(1));
							}
						
						}
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
		});
		btnEnviar.setBounds(56, 283, 89, 23);
		contentPane.add(btnEnviar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(186, 283, 89, 23);
		contentPane.add(btnSalir);
		
		JLabel lblMensaje = new JLabel("Mensaje");
		lblMensaje.setForeground(Color.YELLOW);
		lblMensaje.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblMensaje.setBounds(10, 140, 84, 17);
		contentPane.add(lblMensaje);
		setTitle("Inicio Simulación");
	}
}
