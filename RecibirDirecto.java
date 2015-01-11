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

import Nodo.ListaConfiguracion;
import Nodo.NodoProceso;
import Nodo.NodosCola;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RecibirDirecto extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBoxRece;

	/**
	 * Create the frame.
	 */
	public RecibirDirecto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 303, 192);
		contentPane = new ImagenFondo();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReceptor = new JLabel("Receptor");
		lblReceptor.setForeground(Color.YELLOW);
		lblReceptor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblReceptor.setBounds(27, 11, 96, 30);
		contentPane.add(lblReceptor);
		
		JLabel lblProceso = new JLabel("Proceso");
		lblProceso.setForeground(Color.YELLOW);
		lblProceso.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblProceso.setBounds(54, 40, 75, 23);
		contentPane.add(lblProceso);
		
		comboBoxRece = new JComboBox();
		comboBoxRece.setBounds(123, 43, 130, 20);
		contentPane.add(comboBoxRece);
		int cuantos = 1;
		while(cuantos != VentanaConfiguracion.cantidad+1){
			comboBoxRece.addItem(cuantos);
			cuantos ++;
		}		
		
		JButton btnRecibir = new JButton("Recibir");
		btnRecibir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (VentanaConfiguracion.manejoCola == "FIFO"){
					System.out.println("Ja ja "+ (Integer)comboBoxRece.getSelectedItem());
					Object mensa = R_D_Expli((Integer)comboBoxRece.getSelectedItem());
					if (mensa != null){
						Escribir(comboBoxRece.getSelectedItem(), (String)mensa);	
						JOptionPane.showMessageDialog(null, "El mensaje ha sido recibido por el proceso " + comboBoxRece.getSelectedItem());
						Escribir(comboBoxRece.getSelectedItem(), "El proceso se ha desbloqueado");
						Opciones vuelta = new Opciones();
						vuelta.setVisible(true);
						setVisible(false);
					}
					else{						
						JOptionPane.showMessageDialog(null, "No hay mensaje para este proceso");
						Opciones otra = new Opciones();
						otra.setVisible(true);
						setVisible(false);
					}
				}
				else{// Por Prioidad
					boolean si = Principal.Verifica_Prioridad(comboBoxRece.getSelectedItem());
					if (si == true){
						Object mensa = R_D_Expli((Integer)comboBoxRece.getSelectedItem());
						System.out.println("fksjdhfsabdif" + mensa);
						if (mensa.equals(null)){
							JOptionPane.showMessageDialog(null, "No hay mensaje por recibir");
						}
						else {
							Escribir(comboBoxRece.getSelectedItem(), (String)mensa);	
							JOptionPane.showMessageDialog(null, "El mensaje ha sido recibido por el proceso " + comboBoxRece.getSelectedItem());
							Escribir(comboBoxRece.getSelectedItem(), "El proceso se ha desbloqueado");
							Opciones vuelta = new Opciones();
							vuelta.setVisible(true);
							setVisible(false);							
						}
					}
					else{
						NodosCola esEste = Principal.esCola.Primer;
						JOptionPane.showMessageDialog(null, "No se puede recibir el mensaje hasta que " + esEste.receptor + "reciba el mensaje");
					}
				}
			}
		});
		btnRecibir.setBounds(98, 98, 89, 23);
		contentPane.add(btnRecibir);
		setTitle("Recibir");
	}
	
	public static void Escribir(Object numPro, String mensaje){		
		int t = (Integer)numPro;
		NodoProceso ayuda = VentanaConfiguracion.idVentanas.PrimerNodo;
		while (t != ayuda.Identificador){
			ayuda = ayuda.siguiente;
		}
		if (t == ayuda.Identificador){	
			ayuda.Ventana.append(mensaje + '\n');						
		}
	}
	
	public static void Valida2(Object ProcesoEmisor)
	{
		if (VentanaConfiguracion.emisor.PrimerNodo.contiene.equals("Blocking")){		
			Escribir(ProcesoEmisor, "Proceso Bloqueado");			
		}
		else if (VentanaConfiguracion.emisor.PrimerNodo.contiene.equals("Nonblocking"))
			System.out.println("Imprimiendo,No importa");
		else // Quiere decir que es un prueba de llegada
			{
				if(VentanaConfiguracion.emisor.PrimerNodo.siguiente.siguiente.contiene.equals("Implicito"))
				{
					if((CrearAlias.Alias.get(1).equals(ProcesoEmisor))&(Principal.esCola.VaciaLista()))
					{
						VentanaConfiguracion.Espera.add(ProcesoEmisor);						
					}
				}
				else
				Prueba_Llegada(ProcesoEmisor);
			}
	}
	
	public static void Valida (Object Proceso){
		if (VentanaConfiguracion.emisor.PrimerNodo.contiene.equals("Blocking")){		
			Escribir(Proceso, "Proceso Bloqueado");
			System.out.println("Se debe de bloquear: "+ Proceso);
		}
		else if (VentanaConfiguracion.emisor.PrimerNodo.contiene.equals("Nonblocking"))
			System.out.println("Imprimiendo,No importa");
	}
	
	public static Object R_D_Expli(Object Proceso)
	{	
		System.out.println(Proceso);
		Valida2(Proceso);
		Object mensaje= null;
		mensaje= BuscarCola(Proceso);
		if (mensaje!=null)
		{	
			if ((VentanaConfiguracion.fijoReceptor==0)||(VentanaConfiguracion.fijoReceptor>=mensaje.toString().length())){				
				return mensaje;
			}
			else{				
				JOptionPane.showMessageDialog(null, "El largo del mensaje es invalida");				
				return null;
			}
		}
		else{			
			return null;
		}
	}
	

		public static Object BuscarCola(Object Receptor)//Busca en la cola!
		{   System.out.println(Receptor);
			Object Cambio= (Object)Receptor;
			Object Mensaje = null;
			if  ( Principal.esCola.VaciaLista())
				return null;

	    else{	 
	    	NodosCola Aux =null;
	    	NodosCola Actual = Principal.esCola.Primer;
	 
	    	while (!(Receptor.equals( Actual.receptor)) & (Actual.siguiente != null))
	    	{	 
	    		Aux =Actual;
	    		System.out.println(Actual.grupo);
	    		Actual =Actual.siguiente;
	    	}
	   
	    	if( Receptor==(Actual.receptor))
	    	{	
	    		Mensaje=Actual.msj;
	    		if (Aux == null)
	    		{
	    			Principal.esCola.Primer = Principal.esCola.Primer.siguiente;
	    			return Mensaje;
	    		}
	    		else
	    		{ 
	    			Aux.siguiente = Actual.siguiente;
	    			System.out.println("Cola");
	  
	    			return Mensaje;
	    		}
	    	}
	    }
		return null;
	}
	//Función para Receive directo implicito
	//Si el proceso pertenece al alias si puede recibir el mensaje
	public static Object R_D_Impli(Object Receptor)
	{	System.out.println(Receptor);
		Valida2(Receptor);	
		if (CrearAlias.Alias.get(1).equals(Receptor)){	
			Object mesn = R_D_Expli(CrearAlias.Alias.get(0));
			return mesn;
		}
		else
		{
			Escribir(Receptor,"Acceso denegado");
			JOptionPane.showMessageDialog(null, "El proceso no está relacionado con el Alias");
			return null;
		}
	}
	public static void Prueba_Llegada(Object Proceso)
	{	
		if(!BuscaProceso(Proceso))
		{
			VentanaConfiguracion.Espera.add(Proceso);
			System.out.println("Aun no ha llegado nada a la cola");
		}
	}
	/*lse{
		if ((VentanaConfiguracion.fijoReceptor==0)||(VentanaConfiguracion.fijoReceptor>=Mensaje.toString().length()))
			System.out.println(Mensaje);
		else
			System.out.println("Largo inválido");
		}
	}*/
	public static boolean BuscaProceso(Object Proceso)
	{
		NodosCola Actual= Principal.esCola.Primer;
		while(Actual.siguiente!=null)
		{
			if(Actual.receptor.equals(Proceso))
					return true;
			else
				Actual=Actual.siguiente;
		}
		return false;
	}

	

	
}
