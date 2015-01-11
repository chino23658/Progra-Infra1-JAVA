package Nodo;

import java.util.ArrayList;

public class ListaGrupo {

	public static NodosGrupos PrimerNodo;
	public static NodosGrupos UltimoNodo;
	
	public static boolean VaciaLista () {return PrimerNodo == null;}
	
	public void InsertaGrupo(Object nombre, ArrayList procesosGrupo)
	{ if ( VaciaLista())
		PrimerNodo = UltimoNodo = new NodosGrupos (nombre,procesosGrupo);
	  else
	     UltimoNodo=UltimoNodo.siguiente =new NodosGrupos (nombre, procesosGrupo);
	}
	
	public void ImprimirGrupos()
	{ if (VaciaLista())
	  {
	    System.out.println( "La Lista se encuentra vacía");
	  }
	  //fin del primer if
	 else
	 {
		  NodosGrupos Actual = PrimerNodo;

	    while (Actual != null)
	    {
	     System.out.println ("Nombre del Grupo" + Actual.Grupo);
	     for(int i = 0;i<Actual.miembros.size();i++)
	     {
	            System.out.println(Actual.miembros.get(i));
	     }
	     Actual=Actual.siguiente;
	     
	    }
	     
	  }
	}

}
