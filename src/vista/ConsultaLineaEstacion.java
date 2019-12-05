package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernateutil.HibernateUtil;
import modelo.TAccesos;
import modelo.TLineaEstacion;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ConsultaLineaEstacion extends JFrame {

	private JPanel contentPane;
	private JTextField tfAcceso;
	private JTextField tfEstacion;
	private JTextField tfDescripcion;
	private ArrayList<TLineaEstacion> lineaEstaciones;
	private int posicionListaLineaEstacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaAccesos frame = new ConsultaAccesos();
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
	public ConsultaLineaEstacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Rellenamos array con los accesos para poder recorrerlo
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = session.beginTransaction();
		@SuppressWarnings("unchecked")
		Query<TLineaEstacion> query = session.createQuery("from TLineaEstacion");
		lineaEstaciones =(ArrayList<TLineaEstacion>) query.list();
		tr.commit();
		
		JLabel lblConsultaAccesos = new JLabel("CONSULTA LINEA/ESTACION");
		lblConsultaAccesos.setBounds(34, 17, 214, 15);
		contentPane.add(lblConsultaAccesos);
		
		JLabel lblNewLabel = new JLabel("COD.LINEA");
		lblNewLabel.setBounds(34, 65, 117, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblDescripcin = new JLabel("COD.ESTACION");
		lblDescripcin.setBounds(34, 119, 117, 15);
		contentPane.add(lblDescripcin);
		
		JLabel lblCodestacin = new JLabel("ORDEN");
		lblCodestacin.setBounds(34, 92, 117, 15);
		contentPane.add(lblCodestacin);
		
		
		
		tfAcceso = new JTextField();
		tfAcceso.setBounds(157, 63, 124, 19);
		contentPane.add(tfAcceso);
		tfAcceso.setColumns(10);
		
		tfEstacion = new JTextField();
		tfEstacion.setBounds(157, 90, 124, 19);
		contentPane.add(tfEstacion);
		tfEstacion.setColumns(10);
		
		tfDescripcion = new JTextField();
		tfDescripcion.setBounds(157, 117, 133, 25);
		contentPane.add(tfDescripcion);
		tfDescripcion.setColumns(10);
		
		
		
		
		JButton btnNewButton = new JButton("<");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				
				try {
					//Obtenemos la posicion anterior de la lista de accesos
					TLineaEstacion anterior=lineaEstaciones.get((posicionListaLineaEstacion)-1);
					actualizarDatosVentan(anterior);
					posicionListaLineaEstacion--;
					session.close();		
				}catch(Exception a) {
					JOptionPane.showMessageDialog(null,"Error! Ya estas en el primer Registro");

				}
			
			
			}
		});
		btnNewButton.setBounds(157, 233, 58, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(">");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				
				try {
					
					//Obtenemos la posicion siguiente de la lista de accesos
					TLineaEstacion siguiente=lineaEstaciones.get((posicionListaLineaEstacion)+1);
					actualizarDatosVentan(siguiente);
					posicionListaLineaEstacion++;
					session.close();	
				}catch(Exception a) {
					JOptionPane.showMessageDialog(null,"Error! Ya estas en el ultimo Registro");
					
				}
			
			}
		});
		btnNewButton_1.setBounds(223, 233, 58, 25);
		contentPane.add(btnNewButton_1);
		
		//Vamos al primer registro de la tabla
		JButton btnPr = new JButton("PRIMER REG");
		btnPr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TLineaEstacion firstLineaEstacion = lineaEstaciones.get(0);
				actualizarDatosVentan(firstLineaEstacion);	
				posicionListaLineaEstacion=0;
				
			}
		});
		btnPr.setBounds(12, 233, 133, 25);
		contentPane.add(btnPr);
		//vamos al ultimo registro de la tabla
		JButton btnUlitmoReg = new JButton("ULTIMOS REG");
		btnUlitmoReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TLineaEstacion lastLineaEstacion  =lineaEstaciones.get(lineaEstaciones.size()-1);
				actualizarDatosVentan(lastLineaEstacion);	
				posicionListaLineaEstacion=lineaEstaciones.size()-1;
			}
		});
		btnUlitmoReg.setBounds(295, 233, 133, 25);
		contentPane.add(btnUlitmoReg);
		//Metodo para lanzar consulta 
		JButton btnLanzarConsulta = new JButton("LANZAR CONSULTA");
		btnLanzarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				
				try {
					//Reseteamos posicion
					posicionListaLineaEstacion=0;
					//Obtenemos la posicionsiguiente de la lista de accesos
					TLineaEstacion acceso = session.load(TLineaEstacion.class, Integer.parseInt(tfAcceso.getText()));						
					actualizarDatosVentan(acceso);
					//Buscamos posicion correcta
					//while(lineaEstaciones.get(posicionListaLineaEstacion).getTLineas().getCodLinea()!=acceso.getTLineas().getCodLinea() &&
					//		lineaEstaciones.get(posicionListaLineaEstacion).getTEstaciones().getCodEstacion()!=acceso.getTEstaciones().getCodEstacion()){
						
					while(lineaEstaciones.get(posicionListaLineaEstacion).getId().getCodEstacion()!=acceso.getId().getCodEstacion()) {
					posicionListaLineaEstacion++;
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
		btnLanzarConsulta.setBounds(235, 12, 175, 25);
		contentPane.add(btnLanzarConsulta);
		
		
	}
	
	public void actualizarDatosVentan(TLineaEstacion lineaEstacion) {
		try {
			
		tfAcceso.setText(String.valueOf(lineaEstacion.getId().getCodLinea()));
		tfDescripcion.setText(String.valueOf(lineaEstacion.getId().getCodLinea()));
		tfEstacion.setText(String.valueOf(lineaEstacion.getOrden()));
		
		}catch (ObjectNotFoundException onfe) {
			JOptionPane.showMessageDialog(null,"Error! No se encontro la clase ");
		}
	}
	
}