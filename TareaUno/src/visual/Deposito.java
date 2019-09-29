package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Banco;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class Deposito extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNo;
	private JSpinner spCantidad;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Deposito(Banco popular) {
		setTitle("Depósito");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("Cantidad:");
		label.setBounds(12, 152, 95, 14);
		contentPanel.add(label);
		
		spCantidad = new JSpinner();
		spCantidad.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		spCantidad.setBounds(86, 149, 350, 20);
		contentPanel.add(spCantidad);
		
		JLabel label_1 = new JLabel("No*:");
		label_1.setBounds(12, 69, 95, 14);
		contentPanel.add(label_1);
		
		txtNo = new JTextField();
		txtNo.setEditable(true);
		txtNo.setColumns(10);
		txtNo.setBounds(86, 66, 350, 20);
		contentPanel.add(txtNo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Efectuar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(popular.ingresarACuenta(txtNo.getText(), Float.valueOf(spCantidad.getValue().toString()))) {
							JOptionPane.showMessageDialog(null, "Operaci\u00F3n satisfatoria");
						} else {
							JOptionPane.showMessageDialog(null, "Operaci\u00F3n no satisfatoria");
						}
						txtNo.setText("");
						spCantidad.setValue(new Float(0));
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
