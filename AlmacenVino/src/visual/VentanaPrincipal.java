package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Almacen;

import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private Almacen miAlma = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Almacen alma = new Almacen();
					VentanaPrincipal frame = new VentanaPrincipal(alma);
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
	public VentanaPrincipal(Almacen miAlma) {
		this.miAlma = miAlma;
		setResizable(false);
		setTitle("Almacen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(173, 216, 230));
		setJMenuBar(menuBar);
		
		JMenu mnSuministradores = new JMenu("Suministradores");
		menuBar.add(mnSuministradores);
		
		JMenuItem mntmRegistrar = new JMenuItem("Registrar");
		mntmRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegSuministrador reg = new RegSuministrador(miAlma, null);
				reg.setModal(true);
				reg.setVisible(true);
			}
		});
		mnSuministradores.add(mntmRegistrar);
		
		JMenuItem mntmListar = new JMenuItem("Listar");
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listSuministrador listSumi = new listSuministrador(miAlma);
				listSumi.setModal(true);
				listSumi.setVisible(true);
			}
		});
		mnSuministradores.add(mntmListar);
		
		JMenu mnVinos = new JMenu("Vinos");
		menuBar.add(mnVinos);
		
		JMenuItem mntmRegistrarVino = new JMenuItem("Registrar");
		mntmRegistrarVino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegVino pRVino = new RegVino(miAlma);
				pRVino.setModal(true);
				pRVino.setVisible(true);
			}
		});
		mnVinos.add(mntmRegistrarVino);
		
		JMenuItem mntmListarVino = new JMenuItem("Listar");
		mnVinos.add(mntmListarVino);
		
		JMenu mnAdministracin = new JMenu("Administración");
		menuBar.add(mnAdministracin);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(new Color(173, 216, 230));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(10);
		contentPane.add(panel, BorderLayout.SOUTH);
	}

}
