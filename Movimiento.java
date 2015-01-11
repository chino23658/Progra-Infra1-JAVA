

import Nodo.ListaConfiguracion;
import Nodo.NodoProceso;

public class Movimiento {

	public void Verifica_Confg(ListaConfiguracion Cong, int proceso)
	{
		if (Cong.PrimerNodo.contiene=="Blocking")
		{
			
		}
	}
	public void Busca_Proceso (ListaConfiguracion Cola_Mensajes, int proceso)
	{
		NodoProceso ptro_Cola=Cola_Mensajes.PrimerNodo;
		//if (proceso!= ptro_Cola.proc1)
			ptro_Cola= ptro_Cola.siguiente;
	}

}
