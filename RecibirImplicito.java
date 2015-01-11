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

import Nodo.NodosCola;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RecibirImplicito extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBoxReceptor;

	/**
	 * Create the frame.
	 */
	public RecibirImplicito() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 351, 198);
		contentPane = new ImagenFondo();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReceptor = new JLabel("Receptor");
		lblReceptor.setForeground(Color.YELLOW);
		lblReceptor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblReceptor.setBounds(38, 38, 77, 27);
		contentPane.add(lblReceptor);
		
		comboBoxReceptor = new JComboBox();
		comboBoxReceptor.setBounds(155, 43, 140, 20);
		contentPane.add(comboBoxReceptor);
		int i = 1;
		while (i != VentanaConfiguracion.cantidad+1){
			comboBoxReceptor.addItem(i);
			i++;
		}		
		
		JButton btnRecibir = new JButton("Recibir");
		btnRecibir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (VentanaConfiguracion.manejoCola == "FIFO"){
					Object mensa = RecibirDirecto.R_D_Impli(comboBoxReceptor.getSelectedItem());
					RecibirDirecto.Escribir(comboBoxReceptor.getSelectedItem(), (String)mensa);	
					JOptionPane.showMessageDialog(null, "El mensaje ha sido recibido por el proceso " + comboBoxReceptor.getSelectedItem());					
					Opciones vuelta = new Opciones();
					vuelta.setVisible(true);
					setVisible(false);
				}
				else{
					boolean si = Principal.Verifica_Prioridad(comboBoxReceptor.getSelectedItem());
					if (si == true){
						Object mensa = RecibirDirecto.R_D_Impli(comboBoxReceptor.getSelectedItem());
						RecibirDirecto.Escribir(comboBoxReceptor.getSelectedItem(), (String)mensa);	
						JOptionPane.showMessageDialog(null, "El mensaje ha sido recibido por el proceso " + comboBoxReceptor.getSelectedItem());						
						Opciones vuelta = new Opciones();
						vuelta.setVisible(true);
						setVisible(false);
					}
					else{
						NodosCola esEste = Principal.esCola.Primer;
						JOptionPane.showMessageDialog(null, "No se puede recibir el mensaje hasta que " + esEste.receptor + "reciba el mensaje");
					}
				}
			}
		});
		btnRecibir.setBounds(115, 91, 89, 23);
		contentPane.add(btnRecibir);
		setTitle("Recibir");
	}
}
