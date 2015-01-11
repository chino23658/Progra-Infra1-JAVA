


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;

public class manual extends JFrame {

	private JPanel contentPane;
	public JTextPane textPane;
	int bandera1=0;	
	int  bandera2=0;	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manual frame = new manual();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public manual() {
		

		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 400, 660, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 477, 330);
		contentPane.add(scrollPane);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
		textPane.setText(       
				
				"Manual de Usuario \n\n\n Descripcion de la operación del del Programa \n\n Sistema de  Message Passing \n\n "
				+ "La  funcionalidad del  programa consiste en  un sistema de  administracion de envio de datos "
				+ "mediante una cola de mensajes que va  a manejar la distribucion de los mensajes a enviar "
				+ "atraves una  configuracion pre establecida que permite el sistema.\n\n"
				+ "Esta  configuracion se  basa en dos tipos de operaciones:\n\n"
				+ "Sincronización \n:"
				+ "Accion de Send : Permite el Blocking y  el nonBloking  de los mensajes\n"
				+ "Accion de recive: Permite  Blocking , el nonBloking de  los mensajes"
				+ "y en caso de  completarse la transmision una prueba de llegada.\n\n"
				+ "Direccionamiento:\n"
				+ "Directo\n"
				+ "Accion de Send : Puede ser  explicito o implicito\n"
				+ "Accion de recive: Puede ser  explicito o implicito\n"
				+ "Indirecto\n"
				+ "Accion de  Send y de Recive  pueden ser  estaticas o  dinamicas.\n\n"
				+"Como operar  el programa\n"
				+ "Primer paso: Seleccione el boton de configuración para "
				+ "establecer los parametros\n\n"
				);
		textPane.insertIcon(new ImageIcon ( "/Imagen/im.PNG"));
		JButton btnNewButton = new JButton("Siguiente ");
		btnNewButton.setBounds(352, 331, 104, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println(bandera1);
				if (bandera1==0){
					textPane.setText("El segundo paso es donde  se puede  configurar "
					  		+ "las opciones descritas en el recuadro pasado "
					  		+ " tanto del proceso emisor como del proceso receptor y bajo"
					  		+ " que parametros va operar el sistema encargado de manejar la cola  "
					  		+ "\n\n"     );  
					//textPane.insertIcon(new ImageIcon ( "C:/pm.PNG"));
					
				}
				
				System.out.println(bandera1);
			
				if (bandera1==2){
					textPane.setText("El cuarto paso  es donde se muestra  el  funcionamiento del programa"
							+ "se  debe dar click al  boton de ver cola para  observar el "
							+ "comportamiento de los mensajes administrados por el programa\n\n"     );  
					textPane.insertIcon(new ImageIcon ( "C:/im4.PNG"));
					
				}
				
				
				if (bandera1==1){
					textPane.setText("El tecer paso  es donde se muestra  el  funcionamiento del programa"
							+ " se  debe ingresar  el mensaje a enviar o recibir y escoger  "
							+ "entre que procesos  deben ser diferentes porque no se"
							+ " puede enviar un mensaje al mismo  proceso  \n\n"     );  
					textPane.insertIcon(new ImageIcon ( "C:/im2.PNG"));
					
				}
				
				if (bandera1==3){
					textPane.setText("El quinto paso  es donde se muestra los mensajes "
							+ "enviados y el comportamiento  del proceso \n\n"     );  
					textPane.insertIcon(new ImageIcon ( "C:/im3.PNG"));
					
				}
				
				
			
				bandera1++;
			
			}
			
			
			
			
		});
		
		
		
		JButton btnNewButton_2 = new JButton("Anterior");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				System.out.println(bandera2);
				if (bandera1==0){
					textPane.setText("El segundo paso es donde  se puede  configurar "
					  		+ "las opciones descritas en el recuadro pasado "
					  		+ " tanto del proceso emisor como del proceso receptor y bajo"
					  		+ " que parametros va operar el sistema encargado de manejar la cola  "
					  		+ "\n\n"     );  
					textPane.insertIcon(new ImageIcon ( "C:/pm.PNG"));
					
				}
				
				System.out.println(bandera2);
			
				if (bandera2==0){
					textPane.setText("El cuarto paso  es donde se muestra  el  funcionamiento del programa"
							+ "se  debe dar click al  boton de ver cola para  observar el "
							+ "comportamiento de los mensajes administrados por el programa\n\n"     );  
					textPane.insertIcon(new ImageIcon ( "C:/im4.PNG"));
					
				}
				
				
				if (bandera2==1){
					textPane.setText("El tecer paso  es donde se muestra  el  funcionamiento del programa"
							+ "se  debe ingresar  el mensaje a enviar o recibir y escoger  "
							+ "entre que procesos  deben ser diferentes porque no se"
							+ " puede enviar un mensaje al mismo  proceso  \n\n"     );  
					textPane.insertIcon(new ImageIcon ( "C:/im2.PNG"));
					
				}
				
				if (bandera2==2){
					textPane.setText("El quinto paso  es donde se muestra  el  funcionamiento del programa"
							+ "se  debe dar click al  boton de ver cola para  observar el "
							+ "comportamiento de los mensajes administrados por el programa\n\n"     );  
					textPane.insertIcon(new ImageIcon ( "C:/im3.PNG"));
					
				}
				
				if (bandera2==3){
					textPane.setText(       
							
							"Manual de Usuario \n\n\n Descripcion de la operación del del Programa \n\n Sistema de  Message Passing \n\n "
							+ "La  funcionalidad del  programa consiste en  un sistema de  administracion de mensajes "
							+ "mediante una cola de mensajes que va  a manejar la distribucion de los mensajes a enviar "
							+ "atraves una  configuracion pre establecida que permite el sistema.\n\n"
							+ "Esta  configuracion se  basa en dos tipos de operaciones:\n\n"
							+ "Sincronización \n:"
							+ "Accion de Send : Permite el Blocking y  el nonBloking  de los mensajes\n"
							+ "Accion de recive: Permite  Blocking , el nonBloking de  los mensajes"
							+ "y en caso de  completarse la transmision una prueba de llegada.\n\n"
							+ "Direccionamiento:\n"
							+ "Directo\n"
							+ "Accion de Send : Puede ser  explicito o implicito\n"
							+ "Accion de recive: Puede ser  explicito o implicito\n"
							+ "Indirecto\n"
							+ "Accion de  Send y de Recive  pueden ser  estaticas o  dinamicas.\n\n"
							+"Como operar  el programa\n"
							+ "Primer paso: Seleccione el boton de configuración para "
							+ "establecer los parametros\n\n"
							);
					textPane.insertIcon(new ImageIcon ( "C:/im.PNG"));
					
					bandera1=0;
				}
				bandera2++;
				
			}
		});
		btnNewButton_2.setBounds(253, 331, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblCasos = new JLabel("CASOS FIFO");
		lblCasos.setBounds(487, 11, 74, 14);
		contentPane.add(lblCasos);
		
		JButton btnNewButton_1 = new JButton("Directo Implicito");
		btnNewButton_1.setBounds(487, 66, 137, 23);
		btnNewButton_1 .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textPane.setText("Se  crea un Alias como tipo buzon "
						+ "para ver si se pertenece o no \n \n");
				textPane.insertIcon(new ImageIcon ( "C:/impli.PNG"));
				textPane.insertIcon(new ImageIcon ( "C:/ali.PNG"));
				textPane.insertIcon(new ImageIcon ( "C:/resibido.PNG"));
				textPane.insertIcon(new ImageIcon ( "C:/im3.PNG"));	
				}
			});
		btnNewButton_1.setBounds(487, 36, 137, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Directo Explicito");
		btnNewButton_3.setBounds(487, 66, 137, 23);
		btnNewButton_3 .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPane.setText("");
				textPane.insertIcon(new ImageIcon ( "C:/direcimpli.PNG"));
				textPane.insertIcon(new ImageIcon ( "C:/im3.PNG"));	
				}
			});
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Indirecto Estatico");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPane.setText("Se establecen grupos "
						+ "para la distribucion del progrma\n\n");
				textPane.insertIcon(new ImageIcon ( "C:/estatic.PNG"));
				textPane.insertIcon(new ImageIcon ( "C:/estatic2.PNG"));	
				textPane.insertIcon(new ImageIcon ( "C:/estatic3.PNG"));
				
				
				
				
			}
		});
		btnNewButton_4.setBounds(487, 100, 137, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Indirecto Dinamico");
		btnNewButton_5.setBounds(487, 134, 147, 23);
		contentPane.add(btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPane.setText("Se establecen grupos "
						+ "para la distribucion del mensaje\n\n");
				textPane.insertIcon(new ImageIcon ( "C:/dina.PNG"));
				textPane.insertIcon(new ImageIcon ( "C:/estatic2.PNG"));	
				textPane.insertIcon(new ImageIcon ( "C:/estatic3.PNG"));
				
				
				
				
			}
		});
		
		
		
		
		
		
		
		JLabel lblCasosPioridad = new JLabel("CASOS PIORIDAD");
		lblCasosPioridad.setBounds(487, 179, 109, 14);
		contentPane.add(lblCasosPioridad);
		
		JButton button = new JButton("Directo Implicito");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPane.setText("");
				textPane.insertIcon(new ImageIcon ( "C:/direcimpli1.PNG"));
				textPane.insertIcon(new ImageIcon ( "C:/bola.PNG"));
				textPane.insertIcon(new ImageIcon ( "C:/simu.PNG"));	
				textPane.insertIcon(new ImageIcon ( "C:/direcestatic.PNG"));
				
			}
		});
		button.setBounds(487, 201, 137, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Directo Explicito");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textPane.setText("");
				textPane.insertIcon(new ImageIcon ( "C:/direcexplit.PNG"));
				textPane.insertIcon(new ImageIcon ( "C:/simu.PNG"));	
				textPane.insertIcon(new ImageIcon ( "C:/direcestatic.PNG"));
				
				
				
			}
		});
		button_1.setBounds(487, 231, 137, 23);
		contentPane.add(button_1);
		//     C:/
		JButton button_2 = new JButton("Indirecto Estatico");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPane.setText("");
				textPane.insertIcon(new ImageIcon ( "C:/indirecesta.PNG"));
				textPane.insertIcon(new ImageIcon ( "C:/estatic2.PNG"));	
				textPane.insertIcon(new ImageIcon ( "C:/estatic3.PNG"));
				textPane.insertIcon(new ImageIcon ( "C:/direcestatic.PNG"));
				
				
				
			}
		});
		button_2.setBounds(487, 265, 137, 23);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("Indirecto Dinamico");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textPane.setText("Se establecen grupos "
						+ "para la distribucion del mensaje y la "
						+ "pioridad que tienen los proceso\n");
				textPane.insertIcon(new ImageIcon ( "C:/priinddi.PNG"));
				textPane.insertIcon(new ImageIcon ( "C:/prindi2.PNG"));	
				textPane.insertIcon(new ImageIcon ( "C:/prindi3.PNG"));
				textPane.insertIcon(new ImageIcon ( "C:/direcestatic.PNG"));
				
				
			}
		});
		button_3.setBounds(487, 299, 147, 23);
		contentPane.add(button_3);
		
		JLabel lblEjecuteLosPasos = new JLabel("Ejecute los pasos antes de los casos");
		lblEjecuteLosPasos.setBounds(10, 341, 253, 14);
		contentPane.add(lblEjecuteLosPasos);
	}
	}



