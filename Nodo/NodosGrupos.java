package Nodo;

import java.util.ArrayList;

public class NodosGrupos {

	public NodosGrupos siguiente;
	public Object Grupo;
	public ArrayList miembros;
	
	NodosGrupos(Object Nombre, ArrayList procesosGrupo)
	{
		Grupo=Nombre;
		miembros= procesosGrupo;
		siguiente= null;
	}

}
