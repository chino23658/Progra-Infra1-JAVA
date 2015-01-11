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
import java.awt.Color;


public class InicioDinamicoPriori extends JFrame {

	private JPanel contentPane;
	private TextArea txtMensaje;
	private JComboBox comboBoxEmisor;
	private JTextField txtPrioridad;

	/**
	 * Create the frame.
	 */
	public InicioDinamicoPriori(final String nombre, final int tamaño, final ListaMiembros dinamico) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 319, 389);
		contentPane = new ImagenFondo();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Inicio Simulacion");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 188, 283, 121);
		contentPane.add(scrollPane);
		
		txtMensaje = new TextArea();
		scrollPane.setViewportView(txtMensaje);
		
		JLabel lblEmisor = new JLabel("Emisor");
		lblEmisor.setForeground(Color.YELLOW);
		lblEmisor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmisor.setBounds(33, 11, 148, 30);
		contentPane.add(lblEmisor);
		
		JLabel lblProceso = new JLabel("Proceso");
		lblProceso.setForeground(Color.YELLOW);
		lblProceso.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblProceso.setBounds(53, 38, 128, 24);
		contentPane.add(lblProceso);
		
		comboBoxEmisor = new JComboBox();
		comboBoxEmisor.setBounds(129, 42, 134, 20);
		contentPane.add(comboBoxEmisor);
		int conta = 1;
		while(conta != VentanaConfiguracion.cantidad+1){
			comboBoxEmisor.addItem(conta);
			conta ++;
		}
		
		JLabel lblGrupo = new JLabel("Grupo");
		lblGrupo.setForeground(Color.YELLOW);
		lblGrupo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGrupo.setBounds(33, 84, 116, 24);
		contentPane.add(lblGrupo);
		
		JLabel lblNombreGrupo = new JLabel(nombre);
		lblNombreGrupo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombreGrupo.setForeground(Color.YELLOW);
		lblNombreGrupo.setBounds(129, 86, 134, 24);
		contentPane.add(lblNombreGrupo);
		
		JLabel lblMensaje = new JLabel("Mensaje");
		lblMensaje.setForeground(Color.YELLOW);
		lblMensaje.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblMensaje.setBounds(10, 169, 171, 20);
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
						InicioDinamicoPriori otra = new InicioDinamicoPriori(nombre, tamaño, dinamico);
						otra.setVisible(true);
						setVisible(false);
					}
					else{
						NodoMiembro c = dinamico.PrimerNodo;
						while (c!=null){
							int priori = Integer.parseInt(txtPrioridad.getText());
							RecibirDirecto.Valida(comboBoxEmisor.getSelectedItem());
							Principal.PushPrioridad(comboBoxEmisor.getSelectedItem(), nombre,c.miembro, txtMensaje.getText(),priori);
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
		btnEnviar.setBounds(30, 320, 89, 23);
		contentPane.add(btnEnviar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(174, 320, 89, 23);
		contentPane.add(btnSalir);
		
		JLabel lblPrioridad = new JLabel("Prioridad");
		lblPrioridad.setForeground(Color.YELLOW);
		lblPrioridad.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrioridad.setBounds(33, 134, 148, 24);
		contentPane.add(lblPrioridad);
		
		txtPrioridad = new JTextField();
		txtPrioridad.setBounds(129, 138, 134, 20);
		contentPane.add(txtPrioridad);
		txtPrioridad.setColumns(10);
	}
}
