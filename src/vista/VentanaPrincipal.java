
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		btnInsertarNuevaLineaestacion.setBounds(189, 29, 239, 32);
		contentPane.add(btnInsertarNuevaLineaestacion);
		
		JButton btnNewButton = new JButton("Ver tablas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VistaTablas ventaListaTablas= new VistaTablas();
				ventaListaTablas.setVisible(true);
				ventaListaTablas.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			}
		});
		btnNewButton.setBounds(188, 73, 240, 25);
		contentPane.add(btnNewButton);
	}

}
