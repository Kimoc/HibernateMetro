package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernateutil.HibernateUtil;
import modelo.TCocheras;
import modelo.TEstaciones;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ConsultaEstaciones extends JFrame {

	private JPanel contentPane;
	private JTextField tfCodEstacion;
	private JTextField tfNombre;
	private JTextField tfDireccion;
	private JTextField tfNumAccesos;
	private JTextField tfNumLineas;
	private JTextField tfNumViajeDestino;
	private JTextField tfNumViajeProcedencia;
	private ArrayList<TEstaciones> estaciones;
	private int posicionListaEstaciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaEstaciones frame = new ConsultaEstaciones();
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
	public ConsultaEstaciones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Rellenamos array con los accesos para poder recorrerlo
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = session.beginTransaction();
		@SuppressWarnings("unchecked")
		Query<TEstaciones> query = session.createQuery("from TEstaciones");
		estaciones=(ArrayList<TEstaciones>) query.list();
		tr.commit();
		
		JButton btnHacerConsulta = new JButton("LANZAR CONSULTA");
		btnHacerConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				
				try {
					//Reseteamos posicion
					posicionListaEstaciones=0;
					//Obtenemos la posicionsiguiente de la lista de accesos
					TEstaciones estacion= session.load(TEstaciones.class, Integer.parseInt(tfCodEstacion.getText()));						
					actualizarDatosVentan(estacion);
					//Buscamos posicion correcta
					while(estaciones.get(posicionListaEstaciones).getCodEstacion()!=estacion.getCodEstacion()){
						posicionListaEstaciones++;
					}
							
				}catch (ObjectNotFoundException onfa) {
					JOptionPane.showMessageDialog(null,"Error! No se encontro la clase ");
				}
				catch(Exception a) {
					JOptionPane.showMessageDialog(null,"Error! El registro no existe");
				}
				
			}
		});
		btnHacerConsulta.setBounds(327, 12, 176, 25);
		contentPane.add(btnHacerConsulta);
		
		JLabel lblNewLabel = new JLabel("COD.ESTACIÓN");
		lblNewLabel.setBounds(12, 59, 139, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(12, 86, 66, 15);
		contentPane.add(lblNombre);
		
		JLabel lblDireccin = new JLabel("DIRECCIÓN");
		lblDireccin.setBounds(12, 113, 119, 15);
		contentPane.add(lblDireccin);
		
		JLabel lblNAccesos = new JLabel("Nº ACCESOS");
		lblNAccesos.setBounds(12, 171, 118, 15);
		contentPane.add(lblNAccesos);
		
		JLabel lblN = new JLabel("Nº LINEAS");
		lblN.setBounds(12, 198, 100, 15);
		contentPane.add(lblN);
		
		JLabel lblNViajeDestino = new JLabel("Nº VIAJE DESTINO");
		lblNViajeDestino.setBounds(12, 225, 139, 15);
		contentPane.add(lblNViajeDestino);
		
		JLabel lblNewLabel_1 = new JLabel("Nº VIAJE PROCEDENCIA");
		lblNewLabel_1.setBounds(12, 252, 231, 15);
		contentPane.add(lblNewLabel_1);
		
		tfCodEstacion = new JTextField();
		tfCodEstacion.setBounds(143, 57, 170, 19);
		contentPane.add(tfCodEstacion);
		tfCodEstacion.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(143, 84, 170, 19);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfDireccion = new JTextField();
		tfDireccion.setBounds(143, 113, 170, 50);
		contentPane.add(tfDireccion);
		tfDireccion.setColumns(10);
		
		tfNumAccesos = new JTextField();
		tfNumAccesos.setBounds(143, 169, 81, 19);
		contentPane.add(tfNumAccesos);
		tfNumAccesos.setColumns(10);
		
		tfNumLineas = new JTextField();
		tfNumLineas.setBounds(143, 196, 81, 19);
		contentPane.add(tfNumLineas);
		tfNumLineas.setColumns(10);
		
		tfNumViajeDestino = new JTextField();
		tfNumViajeDestino.setColumns(10);
		tfNumViajeDestino.setBounds(143, 223, 81, 19);
		contentPane.add(tfNumViajeDestino);
		
		tfNumViajeProcedencia = new JTextField();
		tfNumViajeProcedencia.setColumns(10);
		tfNumViajeProcedencia.setBounds(173, 250, 81, 19);
		contentPane.add(tfNumViajeProcedencia);
		
		JButton btnNewButton = new JButton("PRIMER REG");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TEstaciones fisrtEstacion=estaciones.get(0);
				actualizarDatosVentan(fisrtEstacion);
				posicionListaEstaciones=0;
			}
		});
		btnNewButton.setBounds(12, 339, 139, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ULTIMO REG");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TEstaciones lastEstacion = estaciones.get(estaciones.size()-1);
				actualizarDatosVentan(lastEstacion);	
				posicionListaEstaciones=estaciones.size()-1;
			}
		});
		btnNewButton_1.setBounds(347, 339, 156, 25);
		contentPane.add(btnNewButton_1);
		
		JButton button = new JButton("<");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				
				try {
					TEstaciones anterior=estaciones.get((posicionListaEstaciones)-1);
					actualizarDatosVentan(anterior);
					posicionListaEstaciones--;
							
				}catch(Exception a) {
					JOptionPane.showMessageDialog(null,"Error! Ya estas en el primer Registro");

				}
			}
		});
		button.setBounds(173, 339, 66, 25);
		contentPane.add(button);
		
		JButton button_1 = new JButton(">");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				
				try {
					TEstaciones siguiente=estaciones.get((posicionListaEstaciones)+1);
					actualizarDatosVentan(siguiente);
					posicionListaEstaciones++;
							
				}catch(Exception a) {
					JOptionPane.showMessageDialog(null,"Error! Ya estas en el ultimo Registro");

				}
			}
		});
		button_1.setBounds(251, 339, 66, 25);
		contentPane.add(button_1);
	}
	public void actualizarDatosVentan(TEstaciones estacion) {
		try {
			
		tfCodEstacion.setText(String.valueOf(estacion.getCodEstacion()));
		tfNombre.setText(estacion.getNombre());
		tfDireccion.setText(estacion.getDireccion());
		tfNumAccesos.setText(String.valueOf(estacion.getNumaccesos()));
		tfNumLineas.setText(String.valueOf(estacion.getNumlineas()));
		tfNumViajeDestino.setText(String.valueOf(estacion.getNumviajesdestino()));
		tfNumViajeProcedencia.setText(String.valueOf(estacion.getNumviajesprocedencia()));

		
		
		}catch (ObjectNotFoundException onfe) {
			JOptionPane.showMessageDialog(null,"Error! No se encontro la clase ");
		}
	}

}
