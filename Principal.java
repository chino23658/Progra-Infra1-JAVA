import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import Nodo.ListaCola;
import Nodo.ListaConfiguracion;
import Nodo.NodoProceso;
import Nodo.NodosCola;


public class Principal extends JFrame {

	private ImagenFondo contentPane;
	public static ListaCola esCola = new ListaCola();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {	
					//VentanaConfiguracion.Espera.add(1);
					//VentanaConfiguracion.Espera.add(4);
					
					Principal frame = new Principal();
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
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 296, 288);		
		contentPane = new ImagenFondo();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle ("Bienvenida");
		setResizable(false);
		
		JButton btnConfigurar = new JButton("Configurar");
		btnConfigurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaConfiguracion crear = new VentanaConfiguracion();
				crear.setVisible(true);
				setVisible(false);
			}
		});
		btnConfigurar.setBounds(77, 54, 132, 43);
		contentPane.add(btnConfigurar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(77, 156, 132, 43);
		contentPane.add(btnSalir);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 290, 21);
		contentPane.add(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Sistema creado por:"+'\n'+"Cinthya Contreras"+'\n'+"Gabriel Fernández"+'\n'+"Jose Hernández"+'\n'+"Josue Masis");
			}
		});
		mnArchivo.add(mntmAcercaDe);
		
		JMenuItem mntmAyuda = new JMenuItem("Ayuda");
		mntmAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manual verManual = new manual();
				verManual.setVisible(true);
			}
		});
		mnArchivo.add(mntmAyuda);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnArchivo.add(mntmSalir);
	}
	
	public static void PushPrioridad(Object Emisor,Object Grupo, Object Receptor,String Mensaje,int pri)
	{
		if(esCola.VaciaLista())
			esCola.Primer = esCola.Ultimo = new NodosCola ( Emisor, Grupo,Receptor, Mensaje, pri);
		else
		{
			NodosCola Actual= esCola.Primer;
			NodosCola Aux= null;
			NodosCola nuevo = new NodosCola ( Emisor, Grupo,Receptor, Mensaje, pri);
			System.out.println(esCola.Primer.Prioridad);
			while((Actual.siguiente!=null)&(Actual.Prioridad<=pri))
			{
					Aux=Actual;
					Actual= Actual.siguiente;
			}	
			
			if (Aux==null)
			{
				if (Actual.Prioridad>pri)
				{
					esCola.Primer=nuevo;
					nuevo.siguiente= Actual;
				}
				
				else
					esCola.Ultimo=esCola.Ultimo.siguiente =nuevo;
					
	       		 
			}
			else if (Actual.Prioridad>pri)
			{
				Aux.siguiente=nuevo;
				nuevo.siguiente= Actual;
			}
		
			else
			
				esCola.Ultimo=esCola.Ultimo.siguiente =nuevo;
				
		}
	}
	public static boolean Verifica_Prioridad(Object Receptor)
	{	
		System.out.println(esCola.Primer.receptor);
		if(esCola.Primer.receptor.equals(Receptor))
			return true;
		else
		{			
			return false;
		}
	}
}
