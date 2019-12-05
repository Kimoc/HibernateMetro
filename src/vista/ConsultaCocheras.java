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
import modelo.TAccesos;
import modelo.TCocheras;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ConsultaCocheras extends JFrame {

	private JPanel contentPane;
	private JTextField tfCodCochera;
	private JTextField tfNombre;
	private JTextField tfDireccion;
	private ArrayList<TCocheras> cocheras;
	private int posicionListaCocheras;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaCocheras frame = new ConsultaCocheras();
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
	public ConsultaCocheras() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Rellenamos array con los accesos para poder recorrerlo
				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction tr = session.beginTransaction();
				@SuppressWarnings("unchecked")
				Query<TCocheras> query = session.createQuery("from TCocheras");
				cocheras =(ArrayList<TCocheras>) query.list();
				tr.commit();
		
		JButton btnLanzarConsulta = new JButton("LANZAR CONSULTA");
		btnLanzarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				
				try {
					//Reseteamos posicion
					posicionListaCocheras=0;
					//Obtenemos la posicionsiguiente de la lista de accesos
					TCocheras cochera = session.load(TCocheras.class, Integer.parseInt(tfCodCochera.getText()));						
					actualizarDatosVentan(cochera);
					//Buscamos posicion correcta
					while(cocheras.get(posicionListaCocheras).getCodCochera()!=cochera.getCodCochera()){
						posicionListaCocheras++;
					}
					session.close();
							
				}catch (ObjectNotFoundException onfa) {
					JOptionPane.showMessageDialog(null,"Error! No se encontro la clase ");
				}
				catch(Exception a) {
					JOptionPane.showMessageDialog(null,"Error! El registro no existe");
				}
				
			}
		});
		btnLanzarConsulta.setBounds(230, 0, 190, 25);
		contentPane.add(btnLanzarConsulta);
		
		JLabel lblCodcochera = new JLabel("COD.COCHERA");
		lblCodcochera.setBounds(32, 50, 122, 15);
		contentPane.add(lblCodcochera);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(32, 77, 66, 15);
		contentPane.add(lblNombre);
		
		JLabel lblDireccin = new JLabel("DIRECCIÓN");
		lblDireccin.setBounds(32, 104, 122, 15);
		contentPane.add(lblDireccin);
		
		tfCodCochera = new JTextField();
		tfCodCochera.setBounds(146, 48, 124, 19);
		contentPane.add(tfCodCochera);
		tfCodCochera.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(146, 75, 124, 19);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfDireccion = new JTextField();
		tfDireccion.setBounds(146, 102, 222, 50);
		contentPane.add(tfDireccion);
		tfDireccion.setColumns(10);
		
		JButton btnNewButton = new JButton("PRIMER REG");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TCocheras fisrtCochera=cocheras.get(0);
				actualizarDatosVentan(fisrtCochera);
				posicionListaCocheras=0;
			}
		});
		btnNewButton.setBounds(12, 226, 142, 25);
		contentPane.add(btnNewButton);
		
		JButton btnUltimoReg = new JButton("ULTIMO REG");
		btnUltimoReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TCocheras lastCOchera = cocheras.get(cocheras.size()-1);
				actualizarDatosVentan(lastCOchera);	
				posicionListaCocheras=cocheras.size()-1;
			}
		});
		btnUltimoReg.setBounds(296, 226, 142, 25);
		contentPane.add(btnUltimoReg);
		
		JButton button = new JButton("<");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				
				try {
					//Obtenemos la posicion anterior de la lista de cocheras
					TCocheras anterior=cocheras.get((posicionListaCocheras)-1);
					actualizarDatosVentan(anterior);
					posicionListaCocheras--;
					session.close();		
				}catch(Exception a) {
					JOptionPane.showMessageDialog(null,"Error! Ya estas en el primer Registro");

				}
			}
		});
		button.setBounds(164, 226, 59, 25);
		contentPane.add(button);
		
		JButton button_1 = new JButton(">");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				
				try {
					TCocheras siguiente=cocheras.get((posicionListaCocheras)+1);
					actualizarDatosVentan(siguiente);
					posicionListaCocheras++;
					session.close();
				}catch(Exception a) {
					JOptionPane.showMessageDialog(null,"Error! Ya estas en el ultimo Registro");

				}
			}
		});
		button_1.setBounds(230, 226, 59, 25);
		contentPane.add(button_1);
	}
	public void actualizarDatosVentan(TCocheras cochera) {
		try {
			
		tfCodCochera.setText(String.valueOf(cochera.getCodCochera()));
		tfNombre.setText(cochera.getNombre());
		tfDireccion.setText(cochera.getDireccion());
		
		}catch (ObjectNotFoundException onfe) {
			JOptionPane.showMessageDialog(null,"Error! No se encontro la clase ");
		}
	}
}
