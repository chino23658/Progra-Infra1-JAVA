package Nodo;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ListaConfiguracion {
	
	public static NodoProceso PrimerNodo;
	public static NodoProceso UltimoNodo;
	
	
	public static boolean VaciaLista () {return PrimerNodo == null;}
	
	//Inserta al Final de la Lista de procesos
	
	public void InsertaFinal_Proceso(int Num_Proceso)
	{ if ( VaciaLista())
		PrimerNodo = UltimoNodo = new NodoProceso (Num_Proceso);
	  else
	     UltimoNodo=UltimoNodo.siguiente =new NodoProceso (Num_Proceso);
	}
	
	public void InsertaVentana(JTextArea txtProceso, int i)
	{ if ( VaciaLista())
		PrimerNodo = UltimoNodo = new NodoProceso (txtProceso, i);
	  else
	     UltimoNodo=UltimoNodo.siguiente =new NodoProceso (txtProceso, i);
	}
	
	public void InsertaCola(Object emisor,Object grupo, Object proceso, Object Msn)
	{ if ( VaciaLista())
		PrimerNodo = UltimoNodo = new NodoProceso (emisor,grupo,proceso,Msn);
	  else
	     UltimoNodo=UltimoNodo.siguiente =new NodoProceso (emisor,grupo,proceso,Msn);
	}
	
	public static void MensajeGrupo(Object emisor,Object grupo, Object proceso, Object Msn)
	{ if ( VaciaLista())
		PrimerNodo = UltimoNodo = new NodoProceso (emisor,grupo,proceso,Msn);
	  else
	     UltimoNodo=UltimoNodo.siguiente =new NodoProceso (emisor,grupo,proceso,Msn);
	}
	
	//Inserta al final de la lista de configuration
	public void InsertaFinalConfiguracion(Object Information)
	{ 	
		if ( VaciaLista())
			PrimerNodo = UltimoNodo = new NodoProceso (Information);
		else
			UltimoNodo=UltimoNodo.siguiente =new NodoProceso (Information);
	}
	
	//Inserta al grupo sus miembros respectivos
	public void InsertaGrupo(Object nombre, ListaConfiguracion Miembros)
	{ if ( VaciaLista())
		PrimerNodo = UltimoNodo = new NodoProceso (nombre,Miembros);
	  else
	     UltimoNodo=UltimoNodo.siguiente =new NodoProceso (nombre, Miembros);
	}
	
	//Inserta en la cola los datos del mensaje
	public void Push (Object P1, Object Accion,Object P2, String Information)
	{ if ( VaciaLista())
		PrimerNodo = UltimoNodo = new NodoProceso (P1, Accion,P2,Information);
	  else
	     UltimoNodo=UltimoNodo.siguiente =new NodoProceso (P1, Accion,P2,Information);
	}
	

	public void EliminaInicio()
	{

	  if  ( VaciaLista()) System.out.println ("No hay elementos");//throw new ExceptionListException (Nombre);
	  // recuperar la información

	 // Restablecer  las referencias de PrimerNodo y UltimoNodo
	 if (PrimerNodo.equals (UltimoNodo))
	    PrimerNodo = UltimoNodo = null;
	 else
	     PrimerNodo = PrimerNodo.siguiente;

	}

	public void Imprimi()
	{ if (VaciaLista())
	  {
	    System.out.println( "La Lista se encuentra vacía");
	  }
	  //fin del primer if
	 else
	 {
		  NodoProceso Actual = PrimerNodo;

	    while (Actual != null)
		 {
	     System.out.println ("Configuracion " + Actual.Ventana);
	     Actual=Actual.siguiente;
	    }
	    System.out.println();	    
	  }
	}
	
	public void ImprimirSimple()
	{ if (VaciaLista())
	  {
	    System.out.println( "La Lista se encuentra vacía");
	  }
	  //fin del primer if
	 else
	 {
		  NodoProceso Actual = PrimerNodo;

	    while (Actual != null)
		 {
	     System.out.println ("Configuracion " + Actual.contiene);
	     Actual=Actual.siguiente;
	    }

	    System.out.println();
	    System.out.println();
	  }
	}
	
	public void ImprimirCola()
	{ if (VaciaLista())
	  {
	    System.out.println( "La Lista se encuentra vacía");
	  }
	  //fin del primer if
	 else
	 {
		  NodoProceso Actual = PrimerNodo;

	    while (Actual != null)
		 {
	     System.out.println (Actual.proc1 +" "+ Actual.Accion + " "+Actual.proc2 +" "+Actual.Mensaje);
	     Actual=Actual.siguiente;
	    }

	    System.out.println();
	    System.out.println();
	  }
	}
	public void ImprimirGrupos()
	{ if (VaciaLista())
	  {
	    System.out.println( "La Lista se encuentra vacía");
	  }
	  //fin del primer if
	 else
	 {
		  NodoProceso Actual = PrimerNodo;

	    while (Actual != null)
		 {
	     System.out.println ("Nombre del Grupo" + Actual.contiene);
	     System.out.println ("Miembros ");
	     Actual.Miembros.ImprimirSimple();

	     Actual=Actual.siguiente;
	    }
	  }
	}
	
}
