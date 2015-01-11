package Nodo;

public class Alias {

	public static NodoAlias PrimerNodo;
	public static NodoAlias UltimoNodo;
	
	public static boolean VaciaLista () {return PrimerNodo == null;}
	
	public void InsertaFinal_Proceso(Object Proceso)
	{ if ( VaciaLista())
		PrimerNodo = UltimoNodo = new NodoAlias (Proceso);
	  else
	     UltimoNodo=UltimoNodo.siguiente =new NodoAlias (Proceso);
	}
	
	public void ImprimirSimple()
	{ if (VaciaLista())
	  {
	    System.out.println( "La Lista se encuentra vacía");
	  }
	  //fin del primer if
	 else
	 {
		  NodoAlias Actual = PrimerNodo;

	    while (Actual != null)
		 {
	     System.out.println ("Configuracion " + Actual.contiene);
	     Actual=Actual.siguiente;
	    }

	    System.out.println();
	    System.out.println();
	  }
	}

}
