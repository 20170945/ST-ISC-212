package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.CardLayout;
import javax.swing.border.TitledBorder;

import logico.Almacen;
import logico.Suministrador;

import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class RegSuministrador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox cbPais = new JComboBox();
	private JSpinner spTiempo = new JSpinner();
	private JTextField txtCdigo;
	private JTextField txtNombre;
	private Almacen miAlma = null;

	/**
	 * Launch the application.
	 */
	
	/**
	public static void main(String[] args) {
		try {
			RegSuministrador dialog = new RegSuministrador();
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
	public RegSuministrador(Almacen miAlma, String idSumi) {
		this.miAlma = miAlma;
		Suministrador targetSumi = miAlma.buscarSuministrador(idSumi);
		setResizable(false);
		setTitle("Registrar Suministrador");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Registro de Informaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		contentPanel.add(panel, "name_21618750503024");
		panel.setLayout(null);
		{
			JLabel lblCdigo = new JLabel("Código:");
			lblCdigo.setBounds(12, 53, 54, 15);
			panel.add(lblCdigo);
		}
		
		txtCdigo = new JTextField();
		txtCdigo.setBounds(77, 51, 114, 19);
		panel.add(txtCdigo);
		txtCdigo.setEditable(false);
		txtCdigo.setColumns(10);
		txtCdigo.setText("s-"+miAlma.getGeneradorCodigoSumi());
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 116, 60, 15);
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(77, 114, 334, 19);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblPais = new JLabel("País:");
		lblPais.setBounds(12, 177, 35, 15);
		panel.add(lblPais);
		cbPais.setToolTipText("");


		cbPais.setModel(new DefaultComboBoxModel(new String[] {"Selecione", "Chile", "España", "Francia"}));
		cbPais.setBounds(77, 172, 114, 24);
		panel.add(cbPais);
		
		JLabel lblTiempo = new JLabel("Tiempo:");
		lblTiempo.setBounds(232, 177, 56, 15);
		panel.add(lblTiempo);
		
		spTiempo.setModel(new SpinnerNumberModel(1, 1, 30, 1));
		spTiempo.setBounds(297, 174, 114, 20);
		panel.add(spTiempo);
		
		if(idSumi!=null) {
			txtCdigo.setText(targetSumi.getId());
			txtNombre.setText(targetSumi.getNombre());
			cbPais.setSelectedItem(targetSumi.getPais());
			spTiempo.setValue(targetSumi.getTiempoEntrega());
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(idSumi==null) {
							//System.out.println(miAlma.getCantSumi());
							Suministrador sumi = new Suministrador(txtCdigo.getText(), txtNombre.getText(), cbPais.getSelectedItem().toString(), Integer.valueOf(spTiempo.getValue().toString()));
							miAlma.insertarSuministrador(sumi);
							//System.out.println(miAlma.getCantSumi());
							JOptionPane.showMessageDialog(null, "Operación satisfatoria");
							clean();
						} else {
							//System.out.println(miAlma.getCantSumi());
							targetSumi.setNombre(txtNombre.getText());
							targetSumi.setPais(cbPais.getSelectedItem().toString());
							targetSumi.setTiempoEntrega(Integer.valueOf(spTiempo.getValue().toString()));
							//System.out.println(miAlma.getCantSumi());
							JOptionPane.showMessageDialog(null, "Operación satisfatoria");
							dispose();
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
	}
	private void clean() {
		cbPais.setSelectedIndex(0);
		spTiempo.setValue(Integer.valueOf("1"));
		txtCdigo.setText("s-"+miAlma.getGeneradorCodigoSumi());
		txtNombre.setText("");
		
	}
}
