package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Banco;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Retiro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCdula;
	private JTextField txtNo;
	private JSpinner spCantidad;

	/**
	 * Launch the application.
	 */
	/**
	 * public static void main(String[] args) { try { Retiro dialog = new
	 * Retiro(null); dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */
	/**
	 * Create the dialog.
	 */
	public Retiro(Banco popular) {
		setTitle("Retiro");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("Cédula*:");
			label.setBounds(12, 48, 95, 14);
			contentPanel.add(label);
		}
		{
			txtCdula = new JTextField();
			txtCdula.setEditable(true);
			txtCdula.setColumns(10);
			txtCdula.setBounds(86, 45, 350, 20);
			contentPanel.add(txtCdula);
		}
		{
			JLabel lblNo = new JLabel("No*:");
			lblNo.setBounds(12, 110, 95, 14);
			contentPanel.add(lblNo);
		}
		{
			txtNo = new JTextField();
			txtNo.setEditable(true);
			txtNo.setColumns(10);
			txtNo.setBounds(86, 107, 350, 20);
			contentPanel.add(txtNo);
		}
		{
			JLabel lblCantidad = new JLabel("Cantidad:");
			lblCantidad.setBounds(12, 172, 95, 14);
			contentPanel.add(lblCantidad);
		}
		{
			spCantidad = new JSpinner();
			spCantidad.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
			spCantidad.setBounds(86, 169, 350, 20);
			contentPanel.add(spCantidad);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Efectuar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (popular.retirarDeCuenta(txtCdula.getText(), txtNo.getText(),
								Float.valueOf(spCantidad.getValue().toString()))) {
							JOptionPane.showMessageDialog(null, "Operaci\u00F3n satisfatoria");
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Operaci\u00F3n no satisfatoria");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
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
