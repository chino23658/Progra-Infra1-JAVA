import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import Nodo.NodoProceso;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Opciones extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Opciones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 230, 280);
		contentPane = new ImagenFondo();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (VentanaConfiguracion.manejoCola == "FIFO"){
					if (VentanaConfiguracion.ventana == "inicioDirecto"){
						InicioDirecto inicia = new InicioDirecto(VentanaConfiguracion.fijoEmisor);
						inicia.setVisible(true);
						setVisible(false);
					}
					if (VentanaConfiguracion.ventana == "InicioIndirecto"){
						InicioIndirecto inicia = new InicioIndirecto(VentanaConfiguracion.fijoEmisor, GrupoEstatico.grupo);
						inicia.setVisible(true);
						setVisible(false);
					}
					if (VentanaConfiguracion.ventana == "InicioDinamico"){
						GrupoDinamico crear = new GrupoDinamico(VentanaConfiguracion.fijoEmisor);
						crear.setVisible(true);
						setVisible(false);
					}
					if (VentanaConfiguracion.ventana == "inicioDirectoImpli"){
						InicioDirectoImplicito crear = new InicioDirectoImplicito(VentanaConfiguracion.fijoEmisor);
						crear.setVisible(true);
						setVisible(false);
					}
				}//Fin del if de FIFO
				else{
					if (VentanaConfiguracion.ventana == "inicioDirecto"){
						InicioDirectoPriori inicia = new InicioDirectoPriori(VentanaConfiguracion.fijoEmisor);
						inicia.setVisible(true);
						setVisible(false);
					}
					//Modificar
					if (VentanaConfiguracion.ventana == "InicioIndirecto"){
						InicioIndirectoPriori inicia = new InicioIndirectoPriori(VentanaConfiguracion.fijoEmisor, GrupoEstatico.grupo);
						inicia.setVisible(true);
						setVisible(false);
					}
					if (VentanaConfiguracion.ventana == "InicioDinamico"){
						GrupoDinamico crear = new GrupoDinamico(VentanaConfiguracion.fijoEmisor);
						crear.setVisible(true);
						setVisible(false);
					}
					if (VentanaConfiguracion.ventana == "inicioDirectoImpli"){
						InicioDirectoImplicitoPriori crear = new InicioDirectoImplicitoPriori(VentanaConfiguracion.fijoEmisor);
						crear.setVisible(true);
						setVisible(false);
					}
				}
			}
		});
		btnEnviar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEnviar.setBounds(56, 48, 106, 53);
		contentPane.add(btnEnviar);
		
		final NodoProceso aux = VentanaConfiguracion.emisor.PrimerNodo.siguiente;		
		final String saber = (String)aux.contiene;		
		
		JButton btnRecibir = new JButton("Recibir");
		btnRecibir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (saber == "Directo" && (String)aux.siguiente.contiene == "Explicito"){					
					RecibirDirecto otra = new RecibirDirecto();
					otra.setVisible(true);
					setVisible(false);
				}
				else{
					if (saber == "Directo" && (String)aux.siguiente.contiene == "Implicito"){				
						RecibirImplicito reci = new RecibirImplicito();
						reci.setVisible(true);
						setVisible(false);
					}
					else{
						RecibirDirecto reci = new RecibirDirecto();
						reci.setVisible(true);
						setVisible(false);
					}
				}			
			}
		});
		btnRecibir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRecibir.setBounds(56, 132, 106, 53);
		contentPane.add(btnRecibir);
		setTitle("Opciones");
	}
}
