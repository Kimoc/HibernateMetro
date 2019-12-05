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
import modelo.TLineas;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ConsultaLineas extends JFrame {

	private JPanel contentPane;
	private JTextField tfCodLinea;
	private JTextField tfNombre;
	private ArrayList<TLineas> lineas;
	private int posicionListaLineas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaLineas frame = new ConsultaLineas();
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
	public ConsultaLineas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Rellenamos array con los accesos para poder recorrerlo
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = session.beginTransaction();
		@SuppressWarnings("unchecked")
		Query<TLineas> query = session.createQuery("from TLineas");
		lineas=(ArrayList<TLineas>) query.list();
		tr.commit();
		
		JButton btnNewButton = new JButton("REALIZAR CONSULTA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				
				try {
					//Reseteamos posicion
					posicionListaLineas=0;
					//Obtenemos la posicionsiguiente de la lista de accesos
					TLineas linea = session.load(TLineas.class, Integer.parseInt(tfCodLinea.getText()));						
					actualizarDatosVentan(linea);
					//Buscamos posicion correcta
					while(lineas.get(posicionListaLineas).getCodLinea()!=linea.getCodLinea()){
						posicionListaLineas++;
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
		btnNewButton.setBounds(233, 12, 176, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblCodlinea = new JLabel("COD.LINEA");
		lblCodlinea.setBounds(12, 63, 99, 15);
		contentPane.add(lblCodlinea);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(12, 125, 66, 15);
		contentPane.add(lblNombre);
		
		tfCodLinea = new JTextField();
		tfCodLinea.setBounds(102, 61, 124, 19);
		contentPane.add(tfCodLinea);
		tfCodLinea.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(102, 123, 124, 19);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);
		
		JButton button = new JButton("PRIMER REG");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TLineas fisrtCochera=lineas.get(0);
				actualizarDatosVentan(fisrtCochera);
				posicionListaLineas=0;
			}
		});
		button.setBounds(12, 221, 148, 25);
		contentPane.add(button);
		
		JButton button_1 = new JButton("ULTIMO REG");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TLineas lastCOchera = lineas.get(lineas.size()-1);
				actualizarDatosVentan(lastCOchera);	
				posicionListaLineas=lineas.size()-1;
			}
		});
		button_1.setBounds(290, 221, 148, 25);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("<");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				
				try {
					//Obtenemos la posicion anterior de la lista de cocheras
					TLineas anterior=lineas.get((posicionListaLineas)-1);
					actualizarDatosVentan(anterior);
					posicionListaLineas--;
					session.close();		
				}catch(Exception a) {
					JOptionPane.showMessageDialog(null,"Error! Ya estas en el primer Registro");

				}
			}
		});
		button_2.setBounds(181, 221, 44, 25);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton(">");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				
				try {
					//Obtenemos la posicion anterior de la lista de cocheras
					TLineas anterior=lineas.get((posicionListaLineas)+1);
					actualizarDatosVentan(anterior);
					posicionListaLineas++;
					session.close();		
				}catch(Exception a) {
					JOptionPane.showMessageDialog(null,"Error! Ya estas en el utlimo Registro");

				}
			}
		});
		button_3.setBounds(237, 221, 44, 25);
		contentPane.add(button_3);
	}
	public void actualizarDatosVentan(TLineas linea) {
		try {
			tfCodLinea.setText(String.valueOf(linea.getCodLinea()));
			tfNombre.setText(linea.getNombre());
	
		
		}catch (ObjectNotFoundException onfe) {
			JOptionPane.showMessageDialog(null,"Error! No se encontro la clase ");
		}
	}

}
