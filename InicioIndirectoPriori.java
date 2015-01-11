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

import Nodo.ListaConfiguracion;
import Nodo.ListaGrupo;
import Nodo.NodoMiembro;
import Nodo.NodoProceso;
import Nodo.NodosGrupos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class InicioIndirectoPriori extends JFrame {

	private JPanel contentPane;
	private JTextField txtPrioridad;
	private JComboBox comboBoxGrupo;
	private JTextArea txtMensaje;
	private JComboBox comboBoxEmi;

	/**
	 * Create the frame.
	 */
	public InicioIndirectoPriori(final int tamaño, final ListaGrupo grupo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 417);
		contentPane = new ImagenFondo();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 213, 314, 98);
		contentPane.add(scrollPane);
		
		txtMensaje = new JTextArea();
		scrollPane.setViewportView(txtMensaje);
		
		JLabel lblEmisor = new JLabel("Emisor");
		lblEmisor.setForeground(Color.YELLOW);
		lblEmisor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmisor.setBounds(33, 27, 82, 19);
		contentPane.add(lblEmisor);
		
		JLabel lblProceso = new JLabel("Proceso");
		lblProceso.setForeground(Color.YELLOW);
		lblProceso.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblProceso.setBounds(57, 57, 73, 19);
		contentPane.add(lblProceso);
		
		comboBoxEmi = new JComboBox();
		comboBoxEmi.setBounds(159, 58, 133, 20);
		contentPane.add(comboBoxEmi);
		int cuan = 1;
		while (cuan != VentanaConfiguracion.cantidad+1){
			comboBoxEmi.addItem(cuan);
			cuan++;
		}
		
		JLabel lblGrupo = new JLabel("Grupo");
		lblGrupo.setForeground(Color.YELLOW);
		lblGrupo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGrupo.setBounds(33, 107, 82, 25);
		contentPane.add(lblGrupo);
		
		comboBoxGrupo = new JComboBox();
		comboBoxGrupo.setBounds(159, 111, 133, 20);
		contentPane.add(comboBoxGrupo);
		NodosGrupos aux = GrupoEstatico.grupo.PrimerNodo;
		while(aux!=null){
			comboBoxGrupo.addItem(aux.Grupo);
			aux = aux.siguiente;
		}
		
		JLabel lblPrioridad = new JLabel("Prioridad");
		lblPrioridad.setForeground(Color.YELLOW);
		lblPrioridad.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrioridad.setBounds(33, 155, 82, 19);
		contentPane.add(lblPrioridad);
		
		txtPrioridad = new JTextField();
		txtPrioridad.setBounds(159, 156, 133, 20);
		contentPane.add(txtPrioridad);
		txtPrioridad.setColumns(10);
		
		JLabel lblMensaje = new JLabel("Mensaje");
		lblMensaje.setForeground(Color.YELLOW);
		lblMensaje.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblMensaje.setBounds(10, 196, 73, 19);
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
					boolean esta=true;//Bandera que indica si el emisor es miembro del grupo
					NodosGrupos buscar = GrupoEstatico.grupo.PrimerNodo;
					while(buscar!=null){						
						if (comboBoxGrupo.getSelectedItem().equals(buscar.Grupo)){
							ArrayList temporal= new ArrayList();
							temporal= buscar.miembros;
							
							for(int i = 0;i<temporal.size();i++){
					            
								if(comboBoxEmi.getSelectedItem().equals(temporal.get(i))){
									esta = false;
									buscar = null;
								}
							}
							buscar= buscar.siguiente;
							
						}
						else{
							buscar = buscar.siguiente;
						}
					}
					if (esta==false){
						JOptionPane.showMessageDialog(null, "No se puede enviar un mensaje hacia sí mismo");
						InicioIndirecto otra = new InicioIndirecto(tamaño, grupo);
						otra.setVisible(true);
						setVisible(false);
					}
					else{//Mandar el mensaje a cola
						NodosGrupos i = GrupoEstatico.grupo.PrimerNodo;						
						while (i!=null){
							if (comboBoxGrupo.getSelectedItem().equals(i.Grupo)){
								ArrayList temporal= new ArrayList();
								temporal=i.miembros;
								for(int Q = 0;Q<temporal.size();Q++){
									int prioridad = Integer.parseInt(txtPrioridad.getText());
									RecibirDirecto.Valida(comboBoxEmi.getSelectedItem());
									Principal.PushPrioridad(comboBoxEmi.getSelectedItem(), comboBoxGrupo.getSelectedItem(),temporal.get(Q), txtMensaje.getText(),prioridad);
									if (VentanaConfiguracion.emisor.PrimerNodo.contiene == "Blocking"){
										RecibirDirecto.Escribir(comboBoxEmi.getSelectedItem(), "Proceso Desbloqueado");
									}
								}
							}
							i = i.siguiente;
						}																	
						Opciones opci = new Opciones();
						opci.setVisible(true);
						setVisible(false);
					}
				}
			}
		});		btnEnviar.setBounds(51, 322, 89, 23);
		contentPane.add(btnEnviar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(179, 322, 89, 23);
		contentPane.add(btnSalir);
		setTitle("Inicio Simulación");
	}
}
