package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;

import logico.Banco;
import logico.Cliente;
import logico.Cuenta;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JEditorPane;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class RegCuenta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtInteres;
	private JTextField txtNo;
	private Banco popular = null;
	private JTextField txtCdula;
	private JComboBox cbTipo;
	private JSpinner spDia;
	private JButton okButton;
	private JSpinner spAno;
	private JComboBox cbMes;
	private JSpinner spDiaCorte;

	/**
	 * Launch the application.
	 */
	/**
	 * public static void main(String[] args) { try { RegCuenta dialog = new
	 * RegCuenta(); dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */
	/**
	 * Create the dialog.
	 */
	public RegCuenta(Banco popular, Cliente cliente) {
		setResizable(false);
		this.popular = popular;
		setTitle("Abrir una cuenta");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),
				"Introduzca la informaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblCdula = new JLabel("C\u00E9dula*:");
		lblCdula.setBounds(12, 19, 70, 15);
		contentPanel.add(lblCdula);

		JLabel lblNo = new JLabel("No. :");
		lblNo.setBounds(12, 53, 70, 15);
		contentPanel.add(lblNo);

		txtNo = new JTextField();
		txtNo.setEditable(false);
		txtNo.setBounds(54, 51, 114, 19);
		contentPanel.add(txtNo);
		txtNo.setColumns(10);
		txtNo.setText(Integer.toString(popular.getGeneradorCodigoCuenta()));

		JLabel lblTipo = new JLabel("Tipo*:");
		lblTipo.setBounds(12, 87, 70, 15);
		contentPanel.add(lblTipo);

		cbTipo = new JComboBox();
		cbTipo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				okButton.setEnabled(check());
				switch (cbTipo.getSelectedIndex()) {
				case 1:
					txtInteres.setText("0.10");
					break;
				case 2:
					txtInteres.setText("0.20");
					break;
				case 3:
					txtInteres.setText("0.34");
					break;
				default:
					txtInteres.setText("0.00");
					break;
				}
			}
		});
		cbTipo.setModel(new DefaultComboBoxModel(
				new String[] { "Seleccione", "Cuenta Corriente", "Cuenta de Vivienda", "Fondo de Inversi\u00F3n" }));
		cbTipo.setBounds(62, 82, 179, 24);
		contentPanel.add(cbTipo);

		JLabel lblInters = new JLabel("Inter\u00E9s:");
		lblInters.setBounds(275, 87, 70, 15);
		contentPanel.add(lblInters);

		txtInteres = new JTextField();
		txtInteres.setText("0.00");
		txtInteres.setEditable(false);
		txtInteres.setBounds(347, 85, 90, 19);
		contentPanel.add(txtInteres);
		txtInteres.setColumns(10);

		JLabel lblDaDeCorte = new JLabel("D\u00EDa de corte mensual:");
		lblDaDeCorte.setBounds(12, 198, 167, 15);
		contentPanel.add(lblDaDeCorte);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Fecha de apertura:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 121, 425, 58);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("D\u00EDa:");
		label.setBounds(12, 22, 51, 15);
		panel.add(label);

		spDia = new JSpinner();
		spDia.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		spDia.setBounds(49, 19, 57, 20);
		panel.add(spDia);

		JLabel lblMes = new JLabel("Mes:");
		lblMes.setBounds(122, 22, 51, 15);
		panel.add(lblMes);

		cbMes = new JComboBox();
		cbMes.setModel(new DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
				"Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
		cbMes.setBounds(166, 17, 142, 24);
		panel.add(cbMes);

		JLabel lblAo = new JLabel("A\u00F1o:");
		lblAo.setBounds(317, 22, 51, 15);
		panel.add(lblAo);

		spAno = new JSpinner();
		spAno.setModel(new SpinnerNumberModel(2019, 2019, 2099, 1));
		spAno.setBounds(357, 19, 56, 20);
		panel.add(spAno);

		txtCdula = new JTextField();
		txtCdula.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				okButton.setEnabled(check());
			}
		});
		txtCdula.setBounds(76, 17, 331, 19);
		contentPanel.add(txtCdula);
		txtCdula.setColumns(10);

		spDiaCorte = new JSpinner();
		spDiaCorte.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		spDiaCorte.setBounds(178, 195, 63, 20);
		contentPanel.add(spDiaCorte);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Abrir");
				okButton.setEnabled(false);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int[] fechaApertura = new int[3];
						fechaApertura[0] = Integer.valueOf(spDia.getValue().toString());
						fechaApertura[1] = cbMes.getSelectedIndex() + 1;
						fechaApertura[2] = Integer.valueOf(spAno.getValue().toString());
						Cuenta nuevaCuenta = new Cuenta(txtCdula.getText(), txtNo.getText(),
								cbTipo.getSelectedItem().toString(), Float.valueOf(txtInteres.getText()), fechaApertura,
								Integer.valueOf(spDiaCorte.getValue().toString()));
						if (popular.buscarCliente(txtCdula.getText()) == null) {
							JOptionPane.showMessageDialog(null, "No hay cliente con esa c\u00E9dula.");
						} else {
							if (popular.abrirCuenta(popular.buscarCliente(txtCdula.getText()), nuevaCuenta)) {
								JOptionPane.showMessageDialog(null, "Operaci\u00F3n satisfatoria");
								if(cliente==null) {
									txtCdula.setText("");
								}
								clean();
							} else {
								JOptionPane.showMessageDialog(null, "Ya existe una "+cbTipo.getSelectedItem().toString()+" habilitada.");
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
		if (!(cliente == null)) {
			txtCdula.setEditable(false);
			txtCdula.setText(cliente.getCedula());
		}
	}

	private void clean() {
		txtNo.setText(Integer.toString(popular.getGeneradorCodigoCuenta()));
		cbTipo.setSelectedIndex(0);
		cbMes.setSelectedIndex(0);
		txtInteres.setText("0.00");
		spAno.setValue(new Integer(2019));
		spDia.setValue(new Integer(1));
		spDiaCorte.setValue(new Integer(1));
	}

	private boolean check() {
		boolean paso = false;
		if (cbTipo.getSelectedIndex() > 0 && !txtCdula.getText().equalsIgnoreCase("")) {
			paso = true;
		}
		return paso;
	}
}
