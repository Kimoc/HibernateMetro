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
import modelo.TTrenes;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ConsultaTrenes extends JFrame {

	private JPanel contentPane;
	private JTextField tfCodTren;
	private JTextField tfNombre;
	private JTextField tfTipo;
	private JTextField tfCodLinea;
	private JTextField tfCodCOchera;
	private ArrayList<TTrenes> listaTrenes;
	private int posicionListaTrenes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaTrenes frame = new ConsultaTrenes();
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
	public ConsultaTrenes() {
		//Rellenamos array con los accesos para poder recorrerlo
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = session.beginTransaction();
		@SuppressWarnings("unchecked")
		Query<TTrenes> query = session.createQuery("from TTrenes");
		listaTrenes =(ArrayList<TTrenes>) query.list();
		tr.commit();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRealizara = new JButton("REALIZA CONSULTA");
		btnRealizara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				
				try {
					//Reseteamos posicion
					posicionListaTrenes=0;
					//Obtenemos la posicionsiguiente de la lista de accesos
					TTrenes tren = session.load(TTrenes.class, Integer.parseInt(tfCodTren.getText()));						
					actualizarDatosVentan(tren);
					//Buscamos posicion correcta
					while(listaTrenes.get(posicionListaTrenes).getCodTren()!=tren.getCodTren()){
						posicionListaTrenes++;
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
		btnRealizara.setBounds(223, 12, 193, 25);
		contentPane.add(btnRealizara);
		
		JLabel lblCodtren = new JLabel("COD.TREN");
		lblCodtren.setBounds(24, 48, 109, 15);
		contentPane.add(lblCodtren);
		
		JLabel lblNewLabel = new JLabel("NOMBRE");
		lblNewLabel.setBounds(24, 75, 66, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblTipo = new JLabel("TIPO");
		lblTipo.setBounds(26, 102, 66, 15);
		contentPane.add(lblTipo);
		
		JLabel lblCodlinea = new JLabel("COD.LINEA");
		lblCodlinea.setBounds(26, 129, 87, 15);
		contentPane.add(lblCodlinea);
		
		JLabel lblCod = new JLabel("COD.COCHERA");
		lblCod.setBounds(26, 156, 109, 15);
		contentPane.add(lblCod);
		
		tfCodTren = new JTextField();
		tfCodTren.setBounds(101, 49, 124, 19);
		contentPane.add(tfCodTren);
		tfCodTren.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(101, 75, 124, 19);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfTipo = new JTextField();
		tfTipo.setBounds(101, 100, 124, 19);
		contentPane.add(tfTipo);
		tfTipo.setColumns(10);
		
		tfCodLinea = new JTextField();
		tfCodLinea.setBounds(111, 127, 124, 19);
		contentPane.add(tfCodLinea);
		tfCodLinea.setColumns(10);
		
		tfCodCOchera = new JTextField();
		tfCodCOchera.setBounds(131, 154, 124, 19);
		contentPane.add(tfCodCOchera);
		tfCodCOchera.setColumns(10);
		
		JButton btnUltimoReg = new JButton("ULTIMO REG");
		btnUltimoReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TTrenes lastTren = listaTrenes.get(listaTrenes.size()-1);
				actualizarDatosVentan(lastTren);	
				posicionListaTrenes=listaTrenes.size()-1;
			}
		});
		
		JButton btnNewButton = new JButton("PRIMER REG");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TTrenes fisrtCochera=listaTrenes.get(0);
				actualizarDatosVentan(fisrtCochera);
				posicionListaTrenes=0;
			}
		});
		btnNewButton.setBounds(12, 226, 148, 25);
		contentPane.add(btnNewButton);
		btnUltimoReg.setBounds(290, 226, 148, 25);
		contentPane.add(btnUltimoReg);
		
		JButton button = new JButton("<");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				
				try {
					TTrenes siguiente=listaTrenes.get((posicionListaTrenes)-1);
					actualizarDatosVentan(siguiente);
					posicionListaTrenes--;
					session.close();
				}catch(Exception a) {
					JOptionPane.showMessageDialog(null,"Error! Ya estas en el primer Registro");

				}
			}
		});
		button.setBounds(181, 226, 44, 25);
		contentPane.add(button);
		
		JButton button_1 = new JButton(">");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				
				try {
					TTrenes siguiente=listaTrenes.get((posicionListaTrenes)+1);
					actualizarDatosVentan(siguiente);
					posicionListaTrenes++;
					session.close();
				}catch(Exception a) {
					JOptionPane.showMessageDialog(null,"Error! Ya estas en el ultimo Registro");

				}
			}
		});
		button_1.setBounds(237, 226, 44, 25);
		contentPane.add(button_1);
	}
	public void actualizarDatosVentan(TTrenes trenes) {
		try {
		tfCodTren.setText(String.valueOf(trenes.getCodTren()));
		tfNombre.setText(trenes.getNombre());
		tfTipo.setText(trenes.getTipo());
		tfCodLinea.setText(String.valueOf(trenes.getTLineas().getCodLinea()));
		tfCodCOchera.setText(String.valueOf(trenes.getTCocheras().getCodCochera()));
		
		}catch (ObjectNotFoundException onfe) {
			JOptionPane.showMessageDialog(null,"Error! No se encontro la clase ");
		}
	}
}
