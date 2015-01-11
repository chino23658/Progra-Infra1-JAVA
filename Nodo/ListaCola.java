package Nodo;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ListaCola {
	
	public static NodosCola Primer;
	public static NodosCola Ultimo;
	
	
	public static boolean VaciaLista () {return Primer == null;}
	
	//Inserta en la cola los datos del mensaje
	public void Push (Object P1, Object Accion,Object P2, String Information, int prioridad)
	{ if ( VaciaLista())
		Primer = Ultimo = new NodosCola (P1, Accion,P2,Information,prioridad);
	  else
	     Ultimo = Ultimo.siguiente = new NodosCola (P1, Accion,P2,Information,prioridad);
	}

	public void EliminaInicio()
	{

	  if  ( VaciaLista()) System.out.println ("No hay elementos");//throw new ExceptionListException (Nombre);
	  // recuperar la información

	 // Restablecer  las referencias de PrimerNodo y UltimoNodo
	 if (Primer.equals (Ultimo))
	    Primer = Ultimo = null;
	 else
	     Primer = Primer.siguiente;

	}
	
	public void ImprimirCola()
	{ if (VaciaLista())
	  {
	    System.out.println( "La Lista se encuentra vacía");
	  }
	  //fin del primer if
	 else
	 {
		  NodosCola Actual = Primer;

	    while (Actual != null)
		 {
	     System.out.println (Actual.emisor +" "+ Actual.grupo + " "+Actual.receptor +" "+Actual.msj+" "+Actual.Prioridad);
	     Actual=Actual.siguiente;
	    }

	    System.out.println();
	    System.out.println();
	  }
	}
}
