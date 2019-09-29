package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Banco;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.border.BevelBorder;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private Banco popular = null;

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
		this.popular = new Banco();
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/visual/favicon.ico")));
		setTitle("Banco Popular");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnTransacciones = new JMenu("Transacciones");
		menuBar.add(mnTransacciones);

		JMenuItem mntmRetiro = new JMenuItem("Retiro");
		mntmRetiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Retiro retiro = new Retiro(popular);
				retiro.setModal(true);
				retiro.setVisible(true);
			}
		});
		mnTransacciones.add(mntmRetiro);

		JMenuItem mntmDepsito = new JMenuItem("Dep\u00F3sito");
		mntmDepsito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Deposito nuevoDeposivo = new Deposito(popular);
				nuevoDeposivo.setModal(true);
				nuevoDeposivo.setVisible(true);
			}
		});
		mnTransacciones.add(mntmDepsito);

		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);

		JMenuItem mntmListar = new JMenuItem("Listar");
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarCliente listClient = new ListarCliente(popular);
				listClient.setModal(true);
				listClient.setVisible(true);
			}
		});
		mnClientes.add(mntmListar);

		JMenuItem mntmRegistrar = new JMenuItem("Registrar");
		mntmRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCliente regClient = new RegCliente(popular, null);
				regClient.setModal(true);
				regClient.setVisible(true);
			}
		});
		mnClientes.add(mntmRegistrar);
		
		JMenuItem mntmConsultas = new JMenuItem("Consultas");
		mntmConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consulta consulta = new Consulta(popular);
				consulta.setModal(true);
				consulta.setVisible(true);
			}
		});
		mnClientes.add(mntmConsultas);

		JMenu mnCuentas = new JMenu("Cuentas");
		menuBar.add(mnCuentas);

		JMenuItem menuItem_1 = new JMenuItem("Listar");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarCuenta listaCuenta = new ListarCuenta(popular, null);
				listaCuenta.setModal(true);
				listaCuenta.setVisible(true);
			}
		});
		mnCuentas.add(menuItem_1);

		JMenuItem mntmRegistrar_1 = new JMenuItem("Abrir una");
		mntmRegistrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(popular.getCantClientes()>0) {
					RegCuenta nuevaCuenta = new RegCuenta(popular, null);
					nuevaCuenta.setModal(true);
					nuevaCuenta.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "No hay clientes para poder abrir una cuenta.");
				}
			}
		});
		mnCuentas.add(mntmRegistrar_1);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(5, 202, 435, 42);
		contentPane.add(panel);
	}
}
