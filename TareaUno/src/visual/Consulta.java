package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

import logico.Banco;
import logico.Cliente;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Consulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCdula;
	private JTextField txtPuntos;
	private JTextField txtRevTotal;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Consulta(Banco popular) {
		setTitle("Consultas");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCdula = new JLabel("Cédula:");
			lblCdula.setBounds(12, 23, 95, 14);
			contentPanel.add(lblCdula);
		}
		{
			txtCdula = new JTextField();
			txtCdula.setEditable(true);
			txtCdula.setColumns(10);
			txtCdula.setBounds(88, 20, 350, 20);
			contentPanel.add(txtCdula);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Puntos acumulados:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(12, 103, 426, 41);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			
			txtPuntos = new JTextField();
			txtPuntos.setEditable(false);
			panel.add(txtPuntos, BorderLayout.CENTER);
			txtPuntos.setColumns(10);
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Estimaci\u00F3n total de las revisiones mesuales:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel.setBounds(12, 167, 426, 41);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		txtRevTotal = new JTextField();
		txtRevTotal.setEditable(false);
		panel.add(txtRevTotal, BorderLayout.CENTER);
		txtRevTotal.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = popular.buscarCliente(txtCdula.getText());
				if(cliente==null) {
					JOptionPane.showMessageDialog(null, "No se encontro cliente con la cédula introducida.");
					txtPuntos.setText("");
					txtRevTotal.setText("");
					txtCdula.setText("");
				} else {
					txtPuntos.setText(Integer.toString(popular.totalPuntos(cliente)));
					txtRevTotal.setText(Float.toString(popular.revTotal(cliente)));
				}
			}
		});
		btnBuscar.setBounds(321, 39, 117, 25);
		contentPanel.add(btnBuscar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
