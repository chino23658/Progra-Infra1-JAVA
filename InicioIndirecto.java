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
import Nodo.ListaGrupo;
import Nodo.NodoMiembro;
import Nodo.NodoProceso;
import Nodo.NodosGrupos;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Scanner;


public class InicioIndirecto extends JFrame {
	private JComboBox comboBoxEmisor;
	private JComboBox comboBoxGrupo;
	
	private JPanel contentPane;
	private TextArea txtMensaje;
	
	private int n = 0;

	/**
	 * Create the frame.
	 */
	public InicioIndirecto(final int tamaño, final ListaGrupo grupo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 337, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 178, 301, 122);
		contentPane.add(scrollPane);
		
		txtMensaje = new TextArea();
		scrollPane.setViewportView(txtMensaje);
		setTitle("Inicio Simulacion");
		
		JLabel lblEmisor = new JLabel("Emisor");
		lblEmisor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmisor.setBounds(34, 11, 54, 24);
		contentPane.add(lblEmisor);
		
		JLabel lblProceso = new JLabel("Proceso");
		lblProceso.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblProceso.setBounds(56, 35, 54, 24);
		contentPane.add(lblProceso);
		
		comboBoxEmisor = new JComboBox();
		comboBoxEmisor.setBounds(144, 35, 146, 20);
		contentPane.add(comboBoxEmisor);
		int agregar = 1;
		while(agregar != VentanaConfiguracion.cantidad+1){
			comboBoxEmisor.addItem(agregar);
			agregar ++;
		}
		
		JLabel lblGrupo = new JLabel("Grupo");
		lblGrupo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGrupo.setBounds(34, 86, 76, 32);
		contentPane.add(lblGrupo);
		
		comboBoxGrupo = new JComboBox();
		comboBoxGrupo.setBounds(144, 94, 146, 20);
		contentPane.add(comboBoxGrupo);
		NodosGrupos aux = GrupoEstatico.grupo.PrimerNodo;
		while(aux!=null){
			comboBoxGrupo.addItem(aux.Grupo);
			aux = aux.siguiente;
		}
		
		JLabel lblMensaje = new JLabel("Mensaje");
		lblMensaje.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblMensaje.setBounds(10, 156, 78, 24);
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
						Object variable=(Object) 10;
						if (comboBoxGrupo.getSelectedItem().equals(buscar.Grupo)){
							ArrayList temporal= new ArrayList();
							temporal= buscar.miembros;
							
							for(int i = 0;i<temporal.size();i++){
								if((Integer)comboBoxEmisor.getSelectedItem()==(temporal.get(i))){
									esta = false;
								}
							}
							buscar=buscar.siguiente;
	
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
									RecibirDirecto.Valida(comboBoxEmisor.getSelectedItem());
									if (VentanaConfiguracion.emisor.PrimerNodo.contiene.equals("Blocking")){
										RecibirDirecto.Escribir(comboBoxEmisor.getSelectedItem(), "Proceso Bloqueado");
										Principal.esCola.Push(comboBoxEmisor.getSelectedItem(), comboBoxGrupo.getSelectedItem(),temporal.get(Q), txtMensaje.getText(),0);						
										RecibirDirecto.Escribir(comboBoxEmisor.getSelectedItem(), "Proceso Desbloqueado");
										Opciones opci = new Opciones();
										opci.setVisible(true);
										setVisible(false);
									}																					
									else{
										Principal.esCola.Push(comboBoxEmisor.getSelectedItem(), comboBoxGrupo.getSelectedItem(),temporal.get(Q), txtMensaje.getText(),0);
										if (VentanaConfiguracion.emisor.PrimerNodo.contiene=="Prueba de llegada")
											{		
												if(InicioDirecto.Verifica((Object) temporal.get(Q))){
													RecibirDirecto.Escribir(temporal.get(Q), "Ha llegado un mensaje para este proceso");													
													Opciones opci = new Opciones();
													opci.setVisible(true);
													setVisible(false);
													n = 1;
												}
												else{
													if (n != 1){
														Opciones vuel = new Opciones();
														vuel.setVisible(true);
														setVisible(false);
														n = 1;
													}													
												}
											}
										else{
											Opciones opci = new Opciones();
											opci.setVisible(true);
											setVisible(false);
										}
									}
								}								
							}
							i = i.siguiente;
						}																	
					}
				}
			}
		});
		btnEnviar.setBounds(39, 311, 89, 23);
		contentPane.add(btnEnviar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(181, 311, 89, 23);
		contentPane.add(btnSalir);
	}
}
