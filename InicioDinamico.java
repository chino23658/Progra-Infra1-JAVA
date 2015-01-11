import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

import Nodo.ListaConfiguracion;
import Nodo.ListaMiembros;
import Nodo.NodoMiembro;
import Nodo.NodoProceso;

import java.awt.TextArea;
import java.awt.TextField;


public class InicioDinamico extends JFrame {

	private JPanel contentPane;
	private TextArea txtMensaje;
	private JComboBox comboBoxEmisor;

	/**
	 * Create the frame.
	 */
	public InicioDinamico(final String nombre, final int tamaño, final ListaMiembros dinamico) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 319, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Inicio Simulacion");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 203, 283, 121);
		contentPane.add(scrollPane);
		
		txtMensaje = new TextArea();
		scrollPane.setViewportView(txtMensaje);
		
		JLabel lblEmisor = new JLabel("Emisor");
		lblEmisor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmisor.setBounds(33, 25, 66, 30);
		contentPane.add(lblEmisor);
		
		JLabel lblProceso = new JLabel("Proceso");
		lblProceso.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblProceso.setBounds(53, 55, 66, 24);
		contentPane.add(lblProceso);
		
		comboBoxEmisor = new JComboBox();
		comboBoxEmisor.setBounds(129, 59, 134, 20);
		contentPane.add(comboBoxEmisor);
		int conta = 1;
		while(conta != VentanaConfiguracion.cantidad+1){
			comboBoxEmisor.addItem(conta);
			conta ++;
		}
		
		JLabel lblGrupo = new JLabel("Grupo");
		lblGrupo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGrupo.setBounds(33, 115, 66, 24);
		contentPane.add(lblGrupo);
		
		JLabel lblNombreGrupo = new JLabel(nombre);
		lblNombreGrupo.setBounds(129, 122, 46, 14);
		contentPane.add(lblNombreGrupo);
		
		JLabel lblMensaje = new JLabel("Mensaje");
		lblMensaje.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblMensaje.setBounds(10, 185, 66, 20);
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
					Object esta = "";
					NodoMiembro buscar = dinamico.PrimerNodo;
					while(buscar!=null){						
						if (comboBoxEmisor.getSelectedItem().equals(buscar.miembro)){
							esta = buscar.miembro;
							buscar = null;
						}
						else{
							buscar = buscar.siguiente;
						}
					}
					if (esta.equals(comboBoxEmisor.getSelectedItem())){
						JOptionPane.showMessageDialog(null, "No se puede enviar un mensaje hacia sí mismo");
						InicioDinamico otra = new InicioDinamico(nombre, tamaño, dinamico);
						otra.setVisible(true);
						setVisible(false);
					}
					else{
						NodoMiembro c = dinamico.PrimerNodo;
						while (c!=null){
							RecibirDirecto.Valida(comboBoxEmisor.getSelectedItem());
							Principal.esCola.Push(comboBoxEmisor.getSelectedItem(), nombre,c.miembro, txtMensaje.getText(),0);
							if (VentanaConfiguracion.emisor.PrimerNodo.contiene.equals("Blocking")){
								RecibirDirecto.Escribir(comboBoxEmisor.getSelectedItem(), "Proceso Desbloqueado");
								c = c.siguiente;
							}
							else{								
								c = c.siguiente;
							}
						}
						Opciones otra = new Opciones();
						otra.setVisible(true);
						setVisible(false);
					}
				}
			}
		});		
		btnEnviar.setBounds(43, 335, 89, 23);
		contentPane.add(btnEnviar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(174, 335, 89, 23);
		contentPane.add(btnSalir);
	}
}
