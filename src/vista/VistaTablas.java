package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaTablas extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaTablas frame = new VistaTablas();
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
	public VistaTablas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAccesos = new JButton("Accesos");
		btnAccesos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				ConsultaAccesos ventaAccesos=new ConsultaAccesos();
				ventaAccesos.setVisible(true);
				ventaAccesos.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			}
		});
		btnAccesos.setBounds(43, 72, 147, 25);
		contentPane.add(btnAccesos);
		
		JButton btnCocheras = new JButton("Cocheras");
		btnCocheras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaCocheras ventaCochera= new ConsultaCocheras();
				ventaCochera.setVisible(true);
				ventaCochera.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			}
		});
		btnCocheras.setBounds(43, 108, 147, 25);
		contentPane.add(btnCocheras);
		
		JButton btnEstaciones = new JButton("Estaciones");
		btnEstaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaEstaciones ventaEstaciones= new ConsultaEstaciones();
				ventaEstaciones.setVisible(true);
				ventaEstaciones.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				
			}
		});
		btnEstaciones.setBounds(43, 149, 147, 25);
		contentPane.add(btnEstaciones);
		
		JButton btnLineaestacin = new JButton("Linea/Estación");
		btnLineaestacin.setBounds(43, 186, 147, 25);
		contentPane.add(btnLineaestacin);
		
		JButton btnTrenes = new JButton("Trenes");
		btnTrenes.setBounds(259, 72, 147, 25);
		contentPane.add(btnTrenes);
		
		JButton btnLineas = new JButton("Lineas");
		btnLineas.setBounds(259, 108, 147, 25);
		contentPane.add(btnLineas);
		
		JButton btnViajes = new JButton("Viajes");
		btnViajes.setBounds(259, 149, 147, 25);
		contentPane.add(btnViajes);
		
		JLabel lblSeleccionaLaTabla = new JLabel("Selecciona la tabla que quieras ver");
		lblSeleccionaLaTabla.setBounds(43, 12, 363, 15);
		contentPane.add(lblSeleccionaLaTabla);
	}
}
