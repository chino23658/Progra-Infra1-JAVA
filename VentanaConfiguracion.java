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
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;

import Nodo.ListaConfiguracion;
import Nodo.NodoProceso;
import Nodo.NodosCola;
import Nodo.Tiempo;

import javax.swing.JTextField;


public class VentanaConfiguracion extends JFrame {

	private static JComboBox comboBoxIndirecEmi = null;
	private static JComboBox comboBoxSincroEmi = null;
	private static JComboBox comboBoxDirecEmi = null;
	private static JComboBox comboBoxLargoEmi = null;
	private static JComboBox comboBoxCola = null;
	
	private ImagenFondo contentPane;
	private JTextField txtCantidad;
	private JTextField txtFijoEmi;
	
	static ListaConfiguracion emisor = new ListaConfiguracion();
	static ListaConfiguracion group = new ListaConfiguracion();
	static ListaConfiguracion idVentanas = new ListaConfiguracion();
	
	public static ArrayList Espera= new ArrayList();
	public static int cantidad = 0;
	public static int fijoEmisor = 0;	
	public static int fijoReceptor = 0;
	public static String ventana = "";
	public static String manejoCola = "";


	/**
	 * Create the frame.
	 */
	public VentanaConfiguracion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 303);
		contentPane = new ImagenFondo();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal volver = new Principal();
				volver.setVisible(true);
				setVisible(false);
			}
		});
		btnSalir.setBounds(259, 223, 92, 24);
		contentPane.add(btnSalir);
		
		JLabel lblSincronizacion = new JLabel("Sincronizacion");
		lblSincronizacion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblSincronizacion.setForeground(Color.YELLOW);
		lblSincronizacion.setBounds(45, 31, 129, 24);
		contentPane.add(lblSincronizacion);
		
		comboBoxSincroEmi = new JComboBox();
		comboBoxSincroEmi.setBounds(191, 35, 136, 20);
		comboBoxSincroEmi.addItem("Blocking");
		comboBoxSincroEmi.addItem("NonBlocking");
		comboBoxSincroEmi.addItem("Prueba de llegada");
		contentPane.add(comboBoxSincroEmi);
		
		JLabel lblDireccionamiento = new JLabel("Direccionamiento");
		lblDireccionamiento.setForeground(Color.YELLOW);
		lblDireccionamiento.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblDireccionamiento.setBounds(45, 66, 129, 24);
		contentPane.add(lblDireccionamiento);
		
		comboBoxDirecEmi = new JComboBox();
		comboBoxDirecEmi.setBounds(191, 70, 136, 20);
		comboBoxDirecEmi.addItem("Directo");
		comboBoxDirecEmi.addItem("Indirecto");
		contentPane.add(comboBoxDirecEmi);
		
		JLabel lblLargoDelFormato = new JLabel("Largo del Formato");
		lblLargoDelFormato.setForeground(Color.YELLOW);
		lblLargoDelFormato.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblLargoDelFormato.setBounds(45, 101, 148, 24);
		contentPane.add(lblLargoDelFormato);
		
		txtFijoEmi = new JTextField();
		txtFijoEmi.setEditable(false);
		txtFijoEmi.setColumns(10);
		txtFijoEmi.setBounds(337, 105, 129, 20);
		txtFijoEmi.setEditable(false);
		contentPane.add(txtFijoEmi);		
		
		comboBoxLargoEmi = new JComboBox();
		comboBoxLargoEmi.setBackground(new Color(255, 255, 255));
		comboBoxLargoEmi.setBounds(191, 105, 136, 20);
		comboBoxLargoEmi.addItem("Variable");
		comboBoxLargoEmi.addItem("Fijo");		
		contentPane.add(comboBoxLargoEmi);		
		comboBoxLargoEmi.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent arg0) {
				   if (comboBoxLargoEmi.getSelectedItem() == "Fijo"){
					   txtFijoEmi.setEditable(true);
				   }
				   else{
					   txtFijoEmi.setEditable(false);
				   }
			   }
		});
		
		comboBoxIndirecEmi = new JComboBox();
		comboBoxIndirecEmi.setBounds(337, 70, 129, 20);
		contentPane.add(comboBoxIndirecEmi);
		comboBoxDirecEmi.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent arg0) {
				   comboBoxIndirecEmi.removeAllItems();
				   rellenarEmi((String)comboBoxDirecEmi.getSelectedItem());				   
			   }
		});
		
		comboBoxCola = new JComboBox();
		comboBoxCola.setBounds(69, 164, 124, 20);
		comboBoxCola.addItem("FIFO");
		comboBoxCola.addItem("Prioridad");
		contentPane.add(comboBoxCola);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				cargar();			
				cantidad = Integer.parseInt(txtCantidad.getText());
				NodoProceso aux = emisor.PrimerNodo;
				NodoProceso ulti = emisor.UltimoNodo;
				int contador = 1;
				while(contador!=cantidad+1){
					Proceso crear = new Proceso(contador);
					crear.setVisible(true);
					contador ++;		
				}
				if (comboBoxCola.getSelectedItem().equals("FIFO")){
					manejoCola = "FIFO";
					if (aux.siguiente.contiene == "Directo" && aux.siguiente.siguiente.contiene == "Explicito"){
						ventana = "inicioDirecto";
						if (ulti.contiene == "Fijo"){
							fijoEmisor = Integer.parseInt(txtFijoEmi.getText());
							InicioDirecto inicia = new InicioDirecto(fijoEmisor);
							inicia.setVisible(true);								
							setVisible(false);						
						}
						else{
							InicioDirecto inicia = new InicioDirecto(0);
							inicia.setVisible(true);
							setVisible(false);
						}
					}//Fin del if de directo y explicito
					if (aux.siguiente.contiene == "Directo" && aux.siguiente.siguiente.contiene == "Implicito"){
						ventana = "inicioDirectoImpli";
						if (ulti.contiene == "Fijo"){
							fijoEmisor = Integer.parseInt(txtFijoEmi.getText());
							CrearAlias inicia = new CrearAlias(fijoEmisor);
							inicia.setVisible(true);						
							setVisible(false);						
						}
						else{
							CrearAlias inicia = new CrearAlias(0);
							inicia.setVisible(true);
							setVisible(false);
						}
					}//Fin del if de directo e implicito
					if (aux.siguiente.contiene == "Indirecto" && aux.siguiente.siguiente.contiene == "Estatico"){
						ventana = "InicioIndirecto";
						if (ulti.contiene == "Fijo"){
							fijoEmisor = Integer.parseInt(txtFijoEmi.getText());
							GrupoEstatico grupo = new GrupoEstatico(fijoEmisor);
							grupo.setVisible(true);
							setVisible(false);						
						}
						else{
							GrupoEstatico grupo = new GrupoEstatico(0);
							grupo.setVisible(true);
							setVisible(false);
						}
						
					}//Fin del if de indirecto y estatico
					if(aux.siguiente.contiene == "Indirecto" && aux.siguiente.siguiente.contiene == "Dinamico"){
						ventana = "InicioDinamico";
						if (ulti.contiene == "Fijo"){
							fijoEmisor = Integer.parseInt(txtFijoEmi.getText());
							GrupoDinamico crear = new GrupoDinamico(fijoEmisor);
							crear.setVisible(true);
							setVisible(false);						
						}
						else{
							GrupoDinamico crear = new GrupoDinamico(0);
							crear.setVisible(true);
							setVisible(false);	
						}					
					}//Fin de if de indirecto y dinamico
				}//Fin del if de la prioridad
				else{
					manejoCola = "Prioridad";
					if (aux.siguiente.contiene == "Directo" && aux.siguiente.siguiente.contiene == "Implicito"){
						ventana = "inicioDirectoImpli";
						if (ulti.contiene == "Fijo"){
							fijoEmisor = Integer.parseInt(txtFijoEmi.getText());
							CrearAlias inicia = new CrearAlias(fijoEmisor);
							inicia.setVisible(true);						
							setVisible(false);						
						}
						else{
							CrearAlias inicia = new CrearAlias(0);
							inicia.setVisible(true);
							setVisible(false);
						}
					}//Fin del if de directo e implicito
					if (aux.siguiente.contiene == "Indirecto" && aux.siguiente.siguiente.contiene == "Estatico"){
						ventana = "InicioIndirecto";
						if (ulti.contiene == "Fijo"){
							fijoEmisor = Integer.parseInt(txtFijoEmi.getText());
							GrupoEstatico grupo = new GrupoEstatico(fijoEmisor);
							grupo.setVisible(true);
							setVisible(false);						
						}
						else{
							GrupoEstatico grupo = new GrupoEstatico(0);
							grupo.setVisible(true);
							setVisible(false);
						}
						
					}//Fin del if de indirecto y estatico
					if(aux.siguiente.contiene == "Indirecto" && aux.siguiente.siguiente.contiene == "Dinamico"){
						ventana = "InicioDinamico";
						if (ulti.contiene == "Fijo"){
							fijoEmisor = Integer.parseInt(txtFijoEmi.getText());
							GrupoDinamico crear = new GrupoDinamico(fijoEmisor);
							crear.setVisible(true);
							setVisible(false);						
						}
						else{
							GrupoDinamico crear = new GrupoDinamico(0);
							crear.setVisible(true);
							setVisible(false);	
						}					
					}//Fin de if de indirecto y dinamico
					if (aux.siguiente.contiene == "Directo" && aux.siguiente.siguiente.contiene == "Explicito"){
						ventana = "inicioDirecto";
						if (ulti.contiene == "Fijo"){
							fijoEmisor = Integer.parseInt(txtFijoEmi.getText());
							InicioDirectoPriori inicia = new InicioDirectoPriori(fijoEmisor);
							inicia.setVisible(true);						
							setVisible(false);						
						}
						else{
							InicioDirectoPriori inicia = new InicioDirectoPriori(0);
							inicia.setVisible(true);
							setVisible(false);
						}
					}//Fin del if de directo y explicito
				}
			}
		});
		btnCrear.setBounds(119, 223, 92, 24);
		contentPane.add(btnCrear);
		
		JLabel lblCola = new JLabel("Cola");
		lblCola.setForeground(Color.YELLOW);
		lblCola.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCola.setBounds(25, 160, 53, 24);
		contentPane.add(lblCola);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setForeground(Color.YELLOW);
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCantidad.setBounds(299, 160, 92, 24);
		contentPane.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(390, 164, 86, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);				
		
		setTitle("Configuracion");
	}
	
	public static void cargar(){	
		emisor.InsertaFinalConfiguracion(comboBoxSincroEmi.getSelectedItem());
		emisor.InsertaFinalConfiguracion(comboBoxDirecEmi.getSelectedItem());
		emisor.InsertaFinalConfiguracion(comboBoxIndirecEmi.getSelectedItem());
		emisor.InsertaFinalConfiguracion(comboBoxLargoEmi.getSelectedItem());
	}
	
	public static void rellenarEmi(String seleccionado){
		if (seleccionado.equals("Indirecto")){
			comboBoxIndirecEmi.addItem("Estatico");
			comboBoxIndirecEmi.addItem("Dinamico");
		}
		else{
			comboBoxIndirecEmi.addItem("Explicito");
			comboBoxIndirecEmi.addItem("Implicito");
		}
	}
	
	/*------------------------------------------------------------------------------------------------------------
	 * -----------------------------------------------------------------------------------------------------------
	 */
	

	//Método para un Receive Directo Explícito
	public void R_Directo_Expl(Object proc_Emisor, Object proc_Receptor)
	{	Tiempo time = new Tiempo();
		Object texto= null;
		texto= BuscaCola1((Integer)proc_Emisor);
		String size = (String)texto;
		if ((fijoReceptor==0)||(size.length()<=fijoReceptor))
		{
			time.Contar();
			System.out.println("Mensaje Recibido");
			System.out.println(texto);
			if(emisor.PrimerNodo.contiene=="Blocking")
				System.out.println("Ir a la ventana del proceso emisor y desbloquearlo");
		}
		else
			System.out.println("Largo inválido");
		
		
	}
	//Método para un Receive Directo Implícito
	public void R_Directo_Implo(Object proc_Receptor)
	{	Tiempo timer= new Tiempo();
		Object texto = null;
		texto= Busca_Cola2(proc_Receptor);
		String tam = (String)texto;
		if ((fijoReceptor==0)||(tam.length()<=fijoReceptor))
		{
			timer.Contar();
			System.out.println("El mesaje ha lllegado");
			System.out.println(texto);
			if (emisor.PrimerNodo.contiene=="Blocking")
				System.out.println("Mostrar en el mensaje emisor");
		}
		else
			System.out.println("Largo del mensaje inválido");
	}
	
	public static Object Busca_Cola2 (Object procesoReceptor) //Busca en la cola el primer Mensaje que se encuentre en la cola de mensajes
	{	
			
		Object Contenido = null;
		NodosCola Aux = null;
		NodosCola Actual= Principal.esCola.Primer;
		while((Integer) Actual.receptor!=procesoReceptor &(Actual.siguiente!=null))
		{
			Aux=Actual;
			Actual=Actual.siguiente;
		}
		if ((Integer)Actual.emisor==procesoReceptor)
		{	
			Contenido=Actual.msj;
			if(Aux==null)
			{
				Principal.esCola.Primer=Actual.siguiente;
			}
			else
			{
				Aux.siguiente=Actual.siguiente;
			}
		}
		return Contenido;
	
	}
		public static Object BuscaCola1(Object proceso)// Va a la cola de mensajes y elimina.
	{	
		Object Contenido= null;
		NodosCola Aux = null;
		NodosCola Actual= Principal.esCola.Primer;
		while((Actual.emisor!=proceso)&(Actual.siguiente!=null))
		{
			Aux=Actual;
			Actual=Actual.siguiente;
		}
		if ((Integer)Actual.emisor==proceso)
		{	
			Contenido=Actual.msj;
			if(Aux==null)
			{
				Principal.esCola.Primer = Actual.siguiente;
			}
			else
			{
				Aux.siguiente=Actual.siguiente;
			}
		}
		return Contenido;
	}	
}
