
package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {

		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Abrir ventana para isnertar linea/Estacion
		JButton btnInsertarNuevaLineaestacion = new JButton("Insertar nueva Linea/Estacion");
		btnInsertarNuevaLineaestacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   VentanaInsertarLineaEstacion ventainsert= new VentanaInsertarLineaEstacion();
			   ventainsert.setVisible(true);
			   ventainsert.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				
			}
		});
		btnInsertarNuevaLineaestacion.setBounds(84, 29, 239, 32);
		contentPane.add(btnInsertarNuevaLineaestacion);
		
		JButton btnNewButton = new JButton("Ver tablas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VistaTablas ventaListaTablas= new VistaTablas();
				ventaListaTablas.setVisible(true);
				ventaListaTablas.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			}
		});
		btnNewButton.setBounds(84, 89, 240, 32);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modificar Estacion");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualizarEstacion ventaActualizar= new ActualizarEstacion();
				ventaActualizar.setVisible(true);
				ventaActualizar.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				
			}
		});
		btnNewButton_1.setBounds(84, 155, 239, 32);
		contentPane.add(btnNewButton_1);
	}
}
