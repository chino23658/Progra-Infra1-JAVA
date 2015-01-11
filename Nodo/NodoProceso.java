package Nodo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class NodoProceso {
	public NodoProceso siguiente;
	public int Identificador;
	public Object contiene;
	public ListaConfiguracion Miembros;
	public JTextArea Ventana;
	//-------- Cola------
	public Object Accion;
	public Object proc1;
	public Object proc2;
	public Object Mensaje;
	public Object Grupo;
	


	NodoProceso (Object Informacion)//Lista para emisor y receptor y miembros de los grupos
	{
		contiene= Informacion;
		siguiente= null;
	}
	
	NodoProceso (Object actua, Object Action, Object actua2, String msn)// Nodo para la cola de mensajes
	{
		
		proc1= actua;//Proceso emisor
		Accion= Action;//Grupo
		proc2= actua2;//Proceso receptor
		Mensaje= msn;//Mensaje a enviar
		siguiente=null;
		
	}
	NodoProceso(Object grupo, ListaConfiguracion members)
	{
		contiene= grupo;
		Miembros= members;
		siguiente=null;
	}
	NodoProceso(Object emisor, Object gruop, Object proceso,Object msn)
	{
		proc1=emisor;
		Grupo= gruop;
		proc2=proceso;
		Mensaje=msn;
		siguiente=null;
		
	}

	public NodoProceso(JTextArea vent, int i) {
		Ventana= vent;
		Identificador = i;
		siguiente = null;
		// TODO Auto-generated constructor stub
	}

}
