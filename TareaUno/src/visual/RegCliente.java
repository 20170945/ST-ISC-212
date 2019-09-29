package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Banco;
import logico.Cliente;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class RegCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCdula;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDireccin;
	private JTextField txtTlefono;
	private JButton okButton;

	/**
	 * Launch the application.
	 */
	/**
	 * public static void main(String[] args) { try { RegCliente dialog = new
	 * RegCliente(); dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */
	/**
	 * Create the dialog.
	 */
	public RegCliente(Banco popular, Cliente aModificar) {
		boolean noHayCliente = (aModificar == null);
		setTitle(noHayCliente ? "Registrar Cliente" : "Modificar Cliente");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				(noHayCliente ? "Introduzca" : "Modifique") + " la informaci\u00F3n", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblCdula = new JLabel("C\u00E9dula*:");
		lblCdula.setBounds(10, 28, 95, 14);
		contentPanel.add(lblCdula);

		txtCdula = new JTextField();
		txtCdula.setEditable(noHayCliente);
		txtCdula.setBounds(84, 25, 350, 20);
		contentPanel.add(txtCdula);
		txtCdula.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 70, 95, 14);
		contentPanel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(84, 67, 350, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(10, 112, 95, 14);
		contentPanel.add(lblApellidos);

		txtApellidos = new JTextField();
		txtApellidos.setBounds(84, 109, 350, 20);
		contentPanel.add(txtApellidos);
		txtApellidos.setColumns(10);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(10, 154, 95, 14);
		contentPanel.add(lblDireccin);

		txtDireccin = new JTextField();
		txtDireccin.setBounds(84, 151, 350, 20);
		contentPanel.add(txtDireccin);
		txtDireccin.setColumns(10);

		JLabel lblTlefono = new JLabel("T\u00E9lefono:");
		lblTlefono.setBounds(10, 196, 95, 14);
		contentPanel.add(lblTlefono);

		txtTlefono = new JTextField();
		txtTlefono.setColumns(10);
		txtTlefono.setBounds(84, 193, 350, 20);
		contentPanel.add(txtTlefono);

		if (!noHayCliente) {
			txtCdula.setText(aModificar.getCedula());
			txtNombre.setText(aModificar.getNombre());
			txtApellidos.setText(aModificar.getApellidos());
			txtDireccin.setText(aModificar.getDireccion());
			txtTlefono.setText(aModificar.getTelefono());
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton(noHayCliente ? "Registrar" : "Guardar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (noHayCliente) {
							Cliente aux = new Cliente(txtCdula.getText(), txtNombre.getText(), txtApellidos.getText(),
									txtDireccin.getText(), txtTlefono.getText());
							if (!txtCdula.getText().equals("")) {
								if (popular.insertarCliente(aux)) {
									JOptionPane.showMessageDialog(null, "Operaci\u00F3n satisfatoria");
									clean();
								} else {
									txtCdula.setForeground(Color.RED);
									JOptionPane.showMessageDialog(null,
											"Ya existe un cliente con la c\u00E9dula:\n" + txtCdula.getText());
								}
							}
						} else {
							aModificar.setNombre(txtNombre.getText());
							aModificar.setApellidos(txtApellidos.getText());
							aModificar.setDireccion(txtDireccin.getText());
							aModificar.setTelefono(txtTlefono.getText());
							JOptionPane.showMessageDialog(null, "Operaci\u00F3n satisfatoria");
							dispose();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				if (noHayCliente) {
					okButton.setEnabled(false);
				}
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		txtCdula.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if (noHayCliente) {
					if (popular.buscarCliente(txtCdula.getText()) == null && !txtCdula.getText().equalsIgnoreCase("")) {
						txtCdula.setForeground(Color.BLACK);
						okButton.setEnabled(true);
					} else {
						txtCdula.setForeground(Color.RED);
						okButton.setEnabled(false);
					}
				}
			}
		});
	}

	private void clean() {
		txtCdula.setText("");
		txtNombre.setText("");
		txtApellidos.setText("");
		txtDireccin.setText("");
		txtTlefono.setText("");
	}
}
