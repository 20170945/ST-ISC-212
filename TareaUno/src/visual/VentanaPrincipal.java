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
		mnTransacciones.add(mntmRetiro);
		
		JMenuItem mntmDepsito = new JMenuItem("Dep\u00F3sito");
		mnTransacciones.add(mntmDepsito);
		
		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		
		JMenuItem mntmListar = new JMenuItem("Listar");
		mnClientes.add(mntmListar);
		
		JMenuItem mntmBuscar = new JMenuItem("Buscar");
		mnClientes.add(mntmBuscar);
		
		JMenuItem mntmRegistrar = new JMenuItem("Registrar");
		mntmRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCliente regClient = new RegCliente(popular, null);
				regClient.setModal(true);
				regClient.setVisible(true);
			}
		});
		mnClientes.add(mntmRegistrar);
		
		JMenu mnCuentas = new JMenu("Cuentas");
		menuBar.add(mnCuentas);
		
		JMenuItem menuItem_1 = new JMenuItem("Listar");
		mnCuentas.add(menuItem_1);
		
		JMenuItem mntmBuscar_1 = new JMenuItem("Buscar");
		mnCuentas.add(mntmBuscar_1);
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
