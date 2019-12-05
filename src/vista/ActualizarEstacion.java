package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernateutil.HibernateUtil;
import modelo.TEstaciones;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ActualizarEstacion extends JFrame {

	private JPanel contentPane;
	private JTextField tfNumAccesos;
	private JTextField tfNumLineas;
	private JTextField tfNumViajesDestino;
	private JTextField tfNumViajesProcedencia;
	private JTextField tfCodEstacion;
	private TEstaciones estacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActualizarEstacion frame = new ActualizarEstacion();
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
	public ActualizarEstacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 242);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		SessionFactory sessionF= HibernateUtil.getSessionFactory();
		Session session = sessionF.openSession();
		
		JButton btnNewButton = new JButton("Actualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codEstacion=Integer.parseInt(tfCodEstacion.getText());
				Query q =session.createQuery("from TEstaciones where codEstacion= :codEstacion");
				q.setParameter("codEstacion", codEstacion);
				if(q.list().size()!=0) {
					estacion=(TEstaciones) q.list().get(0);
				}
				if(estacion!= null) {
					Transaction transaction= session.beginTransaction();
					session.update(estacion);
					transaction.commit();
					JOptionPane.showMessageDialog(null,"Se ha actualizado correctamente");

				}else {
					JOptionPane.showMessageDialog(null,"Error no existe esta estacion prueba con otra");

				}
			}
		});
		btnNewButton.setBounds(223, 146, 114, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Nº Accesos");
		lblNewLabel.setBounds(12, 28, 91, 25);
		contentPane.add(lblNewLabel);
		
		tfNumAccesos = new JTextField();
		tfNumAccesos.setBounds(12, 61, 91, 19);
		contentPane.add(tfNumAccesos);
		tfNumAccesos.setColumns(10);
		
		JLabel lblNLineas = new JLabel("Nº Lineas");
		lblNLineas.setBounds(144, 33, 66, 15);
		contentPane.add(lblNLineas);
		
		tfNumLineas = new JTextField();
		tfNumLineas.setBounds(126, 61, 91, 19);
		contentPane.add(tfNumLineas);
		tfNumLineas.setColumns(10);
		
		JLabel N = new JLabel("Nº Viajes Destino");
		N.setBounds(251, 33, 122, 15);
		contentPane.add(N);
		
		tfNumViajesDestino = new JTextField();
		tfNumViajesDestino.setBounds(251, 61, 101, 19);
		contentPane.add(tfNumViajesDestino);
		tfNumViajesDestino.setColumns(10);
		
		JLabel lblNViajesOrigen = new JLabel("Nº Viajes Procedencia");
		lblNViajesOrigen.setBounds(400, 33, 148, 15);
		contentPane.add(lblNViajesOrigen);
		
		tfNumViajesProcedencia = new JTextField();
		tfNumViajesProcedencia.setBounds(410, 61, 124, 19);
		contentPane.add(tfNumViajesProcedencia);
		tfNumViajesProcedencia.setColumns(10);
		
		JLabel lblCodEstacion = new JLabel("COD ESTACION");
		lblCodEstacion.setBounds(144, 92, 114, 15);
		contentPane.add(lblCodEstacion);
		
		tfCodEstacion = new JTextField();
		tfCodEstacion.setBounds(261, 90, 124, 19);
		contentPane.add(tfCodEstacion);
		tfCodEstacion.setColumns(10);
	}
}
