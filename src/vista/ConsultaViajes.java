package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernateutil.HibernateUtil;
import modelo.TCocheras;
import modelo.TViajes;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ConsultaViajes extends JFrame {

	private JPanel contentPane;
	private JTextField tfCOdViaje;
	private JTextField tfNombre;
	private JTextField tfEstacionOrigen;
	private JTextField tfEstacionDestino;
	private ArrayList<TViajes> viajes;
	private int posicionListaViajes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaViajes frame = new ConsultaViajes();
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
	public ConsultaViajes() {
		//Rellenamos array con los accesos para poder recorrerlo
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = session.beginTransaction();
		@SuppressWarnings("unchecked")
		Query<TViajes> query = session.createQuery("from TViajes");
		viajes =(ArrayList<TViajes>) query.list();
		tr.commit();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("REALIZA CONSULTA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(223, 12, 193, 25);
		contentPane.add(button);
		
		JButton button_1 = new JButton("PRIMER REG");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(12, 226, 148, 25);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("ULTIMO REG");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_2.setBounds(290, 226, 148, 25);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("<");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_3.setBounds(181, 226, 44, 25);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton(">");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_4.setBounds(237, 226, 44, 25);
		contentPane.add(button_4);
		
		JLabel lblCodviaje = new JLabel("COD.VIAJE");
		lblCodviaje.setBounds(30, 50, 114, 15);
		contentPane.add(lblCodviaje);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(30, 89, 66, 15);
		contentPane.add(lblNombre);
		
		JLabel lblEstacionOrigen = new JLabel("ESTACION ORIGEN");
		lblEstacionOrigen.setBounds(30, 117, 130, 15);
		contentPane.add(lblEstacionOrigen);
		
		JLabel lblEstacionDestino = new JLabel("ESTACION DESTINO");
		lblEstacionDestino.setBounds(30, 144, 130, 15);
		contentPane.add(lblEstacionDestino);
		
		tfCOdViaje = new JTextField();
		tfCOdViaje.setBounds(101, 48, 124, 19);
		contentPane.add(tfCOdViaje);
		tfCOdViaje.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(101, 87, 124, 19);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfEstacionOrigen = new JTextField();
		tfEstacionOrigen.setBounds(171, 115, 124, 19);
		contentPane.add(tfEstacionOrigen);
		tfEstacionOrigen.setColumns(10);
		
		tfEstacionDestino = new JTextField();
		tfEstacionDestino.setBounds(181, 142, 124, 19);
		contentPane.add(tfEstacionDestino);
		tfEstacionDestino.setColumns(10);
	}
	public void actualizarDatosVentan(TViajes viaje) {
		try {
			tfCOdViaje.setText(String.valueOf(viaje.getCodViaje()));
			tfNombre.setText(viaje.getNombre());
			tfEstacionOrigen.setText(String.valueOf(viaje.getEstacionorigen()));
			tfEstacionDestino.setText(String.valueOf(viaje.getEstaciondestino()));

			
		
		}catch (ObjectNotFoundException onfe) {
			JOptionPane.showMessageDialog(null,"Error! No se encontro la clase ");
		}
	}

}
