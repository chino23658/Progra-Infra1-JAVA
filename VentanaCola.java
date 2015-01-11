import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.AbstractListModel;

import java.awt.Font;

import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import Nodo.NodoProceso;
import Nodo.NodosCola;


public class VentanaCola extends JFrame {

	private JPanel contentPane;
	private JTable TablaCola;

	/**
	 * Create the frame.
	 */
	public VentanaCola() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("Hola");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Ver Cola");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 434, 218);
		contentPane.add(scrollPane);
		
		TablaCola = new JTable();
		if(VentanaConfiguracion.manejoCola == "FIFO"){
			if (VentanaConfiguracion.ventana == "inicioDirecto"){
				TablaCola.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Emisor", "Receptor", "Mensaje"
				}
				));
			}
			else{
				TablaCola.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Emisor", "Grupo Receptor", "Mensaje"
				}
				));
			}
		}
		else{
			if (VentanaConfiguracion.ventana == "inicioDirecto"){
				TablaCola.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Emisor", "Receptor", "Mensaje", "Prioridad"
						}
					));
			}
			else{
				TablaCola.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Emisor", "Grupo Receptor", "Mensaje", "Prioridad"
						}
					));
			}
		}
		scrollPane.setViewportView(TablaCola);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnCerrar.setBounds(173, 229, 89, 23);
		contentPane.add(btnCerrar);
		
		NodosCola Actual = Principal.esCola.Primer;		
		while(Actual!= null){
			if (VentanaConfiguracion.manejoCola == "FIFO"){
				if (VentanaConfiguracion.ventana == "inicioDirecto"){
					Object[] datos = {
						Actual.emisor,
						Actual.receptor,						
						Actual.msj
					};
					DefaultTableModel MostrarCola =(DefaultTableModel)TablaCola.getModel();
					MostrarCola.addRow(datos);
					TablaCola.setModel(MostrarCola);
					Actual = Actual.siguiente;
				}
				else{
					Object[] datos = {
						Actual.emisor,
						Actual.grupo,						
						Actual.msj, 							
					};
					DefaultTableModel MostrarCola =(DefaultTableModel)TablaCola.getModel();
					MostrarCola.addRow(datos);
					TablaCola.setModel(MostrarCola);
					Actual = Actual.siguiente;
				}
			}
			else{
				if(VentanaConfiguracion.ventana == "inicioDirecto"){
					Object[] datos = {
							Actual.emisor,
							Actual.receptor,							
							Actual.msj, 
							Actual.Prioridad
						};			
					DefaultTableModel MostrarCola =(DefaultTableModel)TablaCola.getModel();
					MostrarCola.addRow(datos);
					TablaCola.setModel(MostrarCola);
					Actual = Actual.siguiente;
				}
				else{
					Object[] datos = {
							Actual.emisor,
							Actual.grupo,							
							Actual.msj, 
							Actual.Prioridad
						};			
					DefaultTableModel MostrarCola =(DefaultTableModel)TablaCola.getModel();
					MostrarCola.addRow(datos);
					TablaCola.setModel(MostrarCola);
					Actual = Actual.siguiente;
				}
			}
		}
	}
}




