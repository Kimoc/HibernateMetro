package vista;

import hibernateutil.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernateutil.HibernateUtil;
import modelo.TEstaciones;
import modelo.TLineaEstacion;
import modelo.TLineaEstacionId;
import modelo.TLineas;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaInsertarLineaEstacion extends JFrame {

	private JPanel contentPane;
	private JTextField tfNumLinea;
	private JTextField tfNumEstacion;
	private JTextField tfNumOrden;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInsertarLineaEstacion frame = new VentanaInsertarLineaEstacion();
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
	public VentanaInsertarLineaEstacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfNumLinea = new JTextField();
		tfNumLinea.setBounds(212, 38, 124, 19);
		contentPane.add(tfNumLinea);
		tfNumLinea.setColumns(10);
		
		tfNumEstacion = new JTextField();
		tfNumEstacion.setBounds(212, 72, 124, 19);
		contentPane.add(tfNumEstacion);
		tfNumEstacion.setColumns(10);
		
		tfNumOrden = new JTextField();
		tfNumOrden.setBounds(212, 98, 124, 19);
		contentPane.add(tfNumOrden);
		tfNumOrden.setColumns(10);
		
		//Metodo para insertar t_linea_estacion
		JButton btInsertar = new JButton("Insertar");
		btInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				Transaction transaction = session.beginTransaction();
				
					
					try {
						TLineas linea = session.load(TLineas.class, Integer.valueOf(tfNumLinea.getText()));
						TEstaciones estaciones = session.load(TEstaciones.class, Integer.valueOf(tfNumEstacion.getText()));
						TLineaEstacionId idLineaEstacion = new TLineaEstacionId(linea.getCodLinea(), estaciones.getCodEstacion());
						TLineaEstacion lineaEstacion = new TLineaEstacion(idLineaEstacion, null, null, Integer.valueOf(tfNumOrden.getText()));
						session.save(lineaEstacion);
						transaction.commit();
						JOptionPane.showMessageDialog(null, "Se ha insertado el registro con exito en la base de datos");
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null,"Error! Solo se admiten numeros");
					}catch (Exception a) {
						a.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error! El registro seguramente ya exista en la base de datos");
					}
				
				
			}
		});
		btInsertar.setBounds(222, 168, 114, 25);
		contentPane.add(btInsertar);
		
		JLabel lblNewLabel = new JLabel("Numero de Linea");
		lblNewLabel.setBounds(37, 40, 144, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNumero = new JLabel("Numero de Estación");
		lblNumero.setBounds(37, 67, 157, 15);
		contentPane.add(lblNumero);
		
		JLabel lblNumeroOrden = new JLabel("Numero Orden");
		lblNumeroOrden.setBounds(37, 100, 124, 15);
		contentPane.add(lblNumeroOrden);
		
		lblNewLabel_1 = new JLabel("Insertar t_linea_estacion");
		lblNewLabel_1.setBackground(Color.BLUE);
		lblNewLabel_1.setBounds(123, 12, 288, 15);
		contentPane.add(lblNewLabel_1);
	}
}
