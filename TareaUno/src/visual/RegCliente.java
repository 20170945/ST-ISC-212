package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Banco;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

public class RegCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	/**
	public static void main(String[] args) {
		try {
			RegCliente dialog = new RegCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	/**
	 * Create the dialog.
	 */
	public RegCliente(Banco popular) {
		if(popular==null) {
			setTitle("Registrar Cliente");
		}
		else {
			setTitle("Modificar Cliente");
		}
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Registrar informaci\u00F3n del cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCdula = new JLabel("C\u00E9dula:");
		lblCdula.setBounds(10, 28, 46, 14);
		contentPanel.add(lblCdula);
		
		textField = new JTextField();
		textField.setBounds(60, 25, 374, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 70, 46, 14);
		contentPanel.add(lblNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(10, 112, 46, 14);
		contentPanel.add(lblApellidos);
		
		textField_1 = new JTextField();
		textField_1.setBounds(60, 67, 374, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(60, 109, 374, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(10, 154, 57, 14);
		contentPanel.add(lblDireccin);
		
		textField_3 = new JTextField();
		textField_3.setBounds(60, 151, 374, 20);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblTlefono = new JLabel("T\u00E9lefono:");
		lblTlefono.setBounds(10, 196, 46, 14);
		contentPanel.add(lblTlefono);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(60, 193, 374, 20);
		contentPanel.add(formattedTextField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
