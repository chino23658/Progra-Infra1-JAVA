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

import Nodo.ListaConfiguracion;
import Nodo.ListaGrupo;
import Nodo.ListaMiembros;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class GrupoDinamico extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JComboBox comboBoxProcesos;
	private JTextArea txtProcessGrupo;	
	static ListaMiembros dinamico;

	/**
	 * Create the frame.
	 */
	public GrupoDinamico(final int tamaño) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dinamico = new ListaMiembros();
		
		JScrollPane scrollPane = new JScrollPane(txtProcessGrupo);
		scrollPane.setBounds(10, 98, 414, 118);
		contentPane.add(scrollPane);
		
		txtProcessGrupo = new JTextArea();
		scrollPane.setViewportView(txtProcessGrupo);
		setTitle("Crear Grupo");
		txtProcessGrupo.setLineWrap(true);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(27, 11, 66, 19);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(115, 12, 143, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblProcesos = new JLabel("Procesos");
		lblProcesos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProcesos.setBounds(27, 41, 76, 19);
		contentPane.add(lblProcesos);
		
		comboBoxProcesos = new JComboBox();
		comboBoxProcesos.setBounds(115, 42, 143, 20);
		contentPane.add(comboBoxProcesos);
		int contador = 1;		
		while (contador != VentanaConfiguracion.cantidad+1){
			comboBoxProcesos.addItem(contador);
			contador++;
		}
		
		JLabel lblProcesosEnEl = new JLabel("Procesos en el grupo");
		lblProcesosEnEl.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblProcesosEnEl.setBounds(10, 81, 143, 19);
		contentPane.add(lblProcesosEnEl);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int process = ((Integer)comboBoxProcesos.getSelectedItem());
				txtProcessGrupo.append(process + "\n");
				dinamico.InsertaMiembro(process);
			}
		});		
		btnAgregar.setBounds(295, 41, 89, 23);
		contentPane.add(btnAgregar);				
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (VentanaConfiguracion.manejoCola == "FIFO"){
					InicioDinamico inicia = new InicioDinamico(txtNombre.getText(), tamaño, dinamico);
					inicia.setVisible(true);
					setVisible(false);
				}
				else{
					InicioDinamicoPriori inicia = new InicioDinamicoPriori(txtNombre.getText(), tamaño, dinamico);
					inicia.setVisible(true);
					setVisible(false);
				}
			}
		});
		btnCrear.setBounds(175, 227, 89, 23);
		contentPane.add(btnCrear);
	}

}
