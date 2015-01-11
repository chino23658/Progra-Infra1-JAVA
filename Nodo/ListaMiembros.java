package Nodo;

public class ListaMiembros {
	public static NodoMiembro PrimerNodo;
	public static NodoMiembro UltimoNodo;
	
	public static boolean VaciaLista () {return PrimerNodo == null;}
	
	public void InsertaMiembro(Object miembro)
	{ if ( VaciaLista())
		PrimerNodo = UltimoNodo = new NodoMiembro (miembro);
	  else
	     UltimoNodo=UltimoNodo.siguiente =new NodoMiembro (miembro);
	}
	
	public void ImprimirSimple()
	{ if (VaciaLista())
	  {
	    System.out.println( "La Lista se encuentra vacía");
	  }
	  //fin del primer if
	 else
	 {
		  NodoMiembro Actual = PrimerNodo;

	    while (Actual != null)
		 {
	     System.out.println ("Configuracion " + Actual.miembro);
	     Actual=Actual.siguiente;
	    }

	    System.out.println();
	    System.out.println();
	  }
	}
}
