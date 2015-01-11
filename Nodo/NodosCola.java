package Nodo;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class NodosCola {
	public NodosCola siguiente;
	public Object contiene;
	public Object emisor;
	public Object receptor;
	public Object grupo;
	public int Prioridad;
	public Object msj;
	

	NodosCola (Object Informacion)//Lista para emisor y receptor y miembros de los grupos
	{
		contiene= Informacion;
		siguiente= null;
	}
	
	public NodosCola (Object actua, Object Action, Object actua2, String msn,int pri)// Nodo para la cola de mensajes
	{
		
		emisor= actua;//Proceso emisor
		grupo= Action;//Grupo
		receptor= actua2;//Proceso receptor
		msj= msn;//Mensaje a enviar
		Prioridad=pri;
		siguiente=null;
		
	}
}