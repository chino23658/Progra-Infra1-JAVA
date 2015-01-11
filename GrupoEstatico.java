import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Nodo.ListaConfiguracion;
import Nodo.ListaGrupo;
import Nodo.ListaMiembros;
import Nodo.NodosGrupos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Scanner;


public class GrupoEstatico extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextArea txtProcesosGrupo;
	private JComboBox comboBoxProcess;	
	public static ListaGrupo grupo = new ListaGrupo();
	
	private int can = 0;

	/**
	 * Create the frame.
	 */
	public GrupoEstatico(final int tamaño) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 142, 473, 117);
		contentPane.add(scrollPane);
		
		txtProcesosGrupo = new JTextArea();
		scrollPane.setViewportView(txtProcesosGrupo);
		
		JLabel lblNombreDelGrupo = new JLabel("Nombre del grupo");
		lblNombreDelGrupo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombreDelGrupo.setBounds(38, 11, 221, 38);
		contentPane.add(lblNombreDelGrupo);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(202, 22, 175, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblProcesos = new JLabel("Procesos");
		lblProcesos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProcesos.setBounds(38, 60, 113, 29);
		contentPane.add(lblProcesos);
		
		comboBoxProcess = new JComboBox();
		comboBoxProcess.setBounds(202, 66, 175, 20);
		contentPane.add(comboBoxProcess);
		int cant = 1;
		while (cant != VentanaConfiguracion.cantidad+1){
			comboBoxProcess.addItem(cant);
			cant ++;
		}
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String process = comboBoxProcess.getSelectedItem().toString();
				txtProcesosGrupo.append(process + "\n");				
				can ++;
			}
		});
		btnAgregar.setBounds(394, 65, 89, 23);
		contentPane.add(btnAgregar);
		
		JLabel lblProcesosEnEl = new JLabel("Procesos en el grupo");
		lblProcesosEnEl.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblProcesosEnEl.setBounds(10, 115, 175, 29);
		contentPane.add(lblProcesosEnEl);
		
		JButton btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (VentanaConfiguracion.manejoCola == "FIFO"){
					AgregarGrupo(txtNombre.getText());
					InicioIndirecto inicia = new InicioIndirecto(tamaño, grupo);
					inicia.setVisible(true);
					setVisible(false);
				}
				else{
					AgregarGrupo(txtNombre.getText());
					InicioIndirectoPriori inicia = new InicioIndirectoPriori(tamaño, grupo);
					inicia.setVisible(true);
					setVisible(false);
				}
			}
		});
		btnEjecutar.setBounds(201, 270, 89, 23);
		contentPane.add(btnEjecutar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(327, 270, 89, 23);
		contentPane.add(btnSalir);
		
		JButton btnMasGrupos = new JButton("Mas Grupos");
		btnMasGrupos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarGrupo(txtNombre.getText());
				setVisible(false);
				GrupoEstatico otro = new GrupoEstatico(tamaño);
				otro.setVisible(true);
			}
		});
		btnMasGrupos.setBounds(62, 270, 89, 23);
		contentPane.add(btnMasGrupos);
		setTitle("Crear Grupos");
	}
	
	/*-------------------------------------------------------------------------------------------------------------------*/
	//---------------------------------------------------- Metodos -----------------------------------------------------//
	/*------------------------------------------------------------------------------------------------------------------*/
	
	//Método que crea los grupos con su debidos grupos miembros
	public void AgregarGrupo(Object Nom_Grupo)
	{	
		ArrayList procesos= new ArrayList();;//Lista en blanco que almacena los procesos
		procesos = AgregarMiembros();//LLama la funcion que agrega los procesos
		grupo.InsertaGrupo(Nom_Grupo, procesos);//group es la lista global q almacena los grupos estaticos		
	}
	
	public ArrayList AgregarMiembros ()
	{
		ArrayList <Object> Lista_Miembros = new ArrayList();//Lista en blanco que almacena temporalmente los procesos
		
	
		int n=0;//Tiene q quitar eso... Lo hice solo para probar el método
		int miembro;//Nombre del proceso
		Scanner in= new Scanner (txtProcesosGrupo.getText());//Tipo de input que pregunta por el nombre del procesi		
		while (n != can)//Ciclo que solo lo hice para probar... Se supone que en el interfaz conforme se seleciiona alguno del comboBox
			//se agregan a la lista y cuando termine se retorna la lista
		{
			
			miembro=Integer.parseInt(in.nextLine());//Input
			Lista_Miembros.add(miembro);
			n++;
		}		

		return Lista_Miembros;
	}
	public void Print(ListaGrupo arreglo)
	{
		NodosGrupos actual= grupo.PrimerNodo;
		while(actual!=null)
		{	
			System.out.println(actual.Grupo);
			for(int i = 0;i<actual.miembros.size();i++){
	            System.out.println(actual.miembros.get(i));
			}
			actual=actual.siguiente;
		}
	}
}
