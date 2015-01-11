import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import Nodo.ListaConfiguracion;

import java.awt.Color;
import java.awt.TextArea;


public class InicioDirecto extends JFrame {

	private JPanel contentPane;
	private TextArea txtMensaje;
	private JComboBox comboBoxReceptor;
	private JComboBox comboBoxEmisor;	

	/**
	 * Create the frame.
	 */
	public InicioDirecto(final int tamaño) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 389, 387);
		contentPane = new ImagenFondo();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Inicio Simulacion");
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 190, 363, 123);
		contentPane.add(scrollPane);
		
		txtMensaje = new TextArea();
		scrollPane.setViewportView(txtMensaje);						
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(227, 324, 89, 23);
		contentPane.add(btnSalir);
		
		JLabel lblEmisor = new JLabel("Emisor");
		lblEmisor.setForeground(Color.YELLOW);
		lblEmisor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmisor.setBounds(23, 11, 75, 23);
		contentPane.add(lblEmisor);
		
		JLabel lblProceso = new JLabel("Proceso");
		lblProceso.setForeground(Color.YELLOW);
		lblProceso.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblProceso.setBounds(52, 45, 84, 23);
		contentPane.add(lblProceso);
		
		comboBoxEmisor = new JComboBox();
		comboBoxEmisor.setBounds(134, 48, 125, 20);			
		contentPane.add(comboBoxEmisor);
		int contador = 1;		
		while (contador != VentanaConfiguracion.cantidad+1){
			comboBoxEmisor.addItem(contador);
			contador++;
		}
		
		JLabel lblReceptor = new JLabel("Receptor");
		lblReceptor.setForeground(Color.YELLOW);
		lblReceptor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblReceptor.setBounds(23, 103, 75, 23);
		contentPane.add(lblReceptor);
		
		JLabel lblProceso_1 = new JLabel("Proceso");
		lblProceso_1.setForeground(Color.YELLOW);
		lblProceso_1.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblProceso_1.setBounds(52, 137, 66, 23);
		contentPane.add(lblProceso_1);
		
		comboBoxReceptor = new JComboBox();
		comboBoxReceptor.setBounds(134, 140, 125, 20);
		contentPane.add(comboBoxReceptor);
		int conta = 1;		
		while (conta != VentanaConfiguracion.cantidad+1){
			comboBoxReceptor.addItem(conta);
			conta++;
		}
		
		JLabel lblMensaje = new JLabel("Mensaje");
		lblMensaje.setForeground(Color.YELLOW);
		lblMensaje.setBounds(10, 171, 88, 14);
		contentPane.add(lblMensaje);			
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String texto = txtMensaje.getText();
				int largo = texto.length();
				if(tamaño!=0 && largo>tamaño){
					JOptionPane.showMessageDialog(null, "El tamaño del mensaje excede"+'\n'+"a la cantidad de caracteres permitidos"+'\n'+"para esta la configuracion la cual"+'\n'+"debe de ser de un tamaño de: "+tamaño);
					txtMensaje.setText("");
				}
				else{//Validar si es al mismo
					if(comboBoxEmisor.getSelectedItem().equals(comboBoxReceptor.getSelectedItem())){
						JOptionPane.showMessageDialog(null, "No se puede enviar un mensaje hacia sí mismo");
						InicioDirecto otra = new InicioDirecto(tamaño);
						otra.setVisible(true);
						setVisible(false);
					}
					else{
						if (VentanaConfiguracion.emisor.PrimerNodo.contiene == "Blocking"){
							RecibirDirecto.Escribir(comboBoxEmisor.getSelectedItem(), "Proceso Bloqueado");
							Principal.esCola.Push(comboBoxEmisor.getSelectedItem(), "", comboBoxReceptor.getSelectedItem(), txtMensaje.getText(),0);
							if (VentanaConfiguracion.emisor.PrimerNodo.contiene.equals("Blocking")){
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
					
						else{							
							Principal.esCola.Push(comboBoxEmisor.getSelectedItem(), "",comboBoxReceptor.getSelectedItem(), txtMensaje.getText(),0);		
							if (VentanaConfiguracion.emisor.PrimerNodo.contiene=="Prueba de llegada")
							{
								if(Verifica(comboBoxReceptor.getSelectedItem())){
									RecibirDirecto.Escribir(comboBoxReceptor.getSelectedItem(), "Ha llegado un mensaje para este proceso");
									System.out.println("Ha llegado un mensaje para: "+comboBoxReceptor.getSelectedItem());
								}
							}
							Opciones opci = new Opciones();
							opci.setVisible(true);
							setVisible(false);
						}
					}
				}
			}
		});
		btnEnviar.setBounds(67, 324, 89, 23);
		contentPane.add(btnEnviar);
	}
	public static boolean Verifica(Object Proceso)
	{
		for(int i = 0;i<VentanaConfiguracion.Espera.size();i++){
            if(VentanaConfiguracion.Espera.get(i).equals(Proceso))
            {
            	return true;
            }
		}
		return false;
	}
	public static void remove (Object Proceso)
	{
		for(int i = 0;i<VentanaConfiguracion.Espera.size();i++){
            if(VentanaConfiguracion.Espera.get(i).equals(Proceso))
            	VentanaConfiguracion.Espera.remove(i);}
	
}
}
