package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Almacen;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.ScrollPaneConstants;

public class RegVino extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Almacen miAlma = null;
	private JTextField txtCdigo;
	private JTextField txtNombre;

	/**
	 * Create the dialog.
	 */
	public RegVino(Almacen miAlma) {
		int i;
		setTitle("Registrar Vino");
		this.miAlma = miAlma;
		setBounds(100, 100, 450, 331);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Registro de informaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new CardLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, "name_12329448786202");
			panel.setLayout(null);
			
			JLabel lblCdigo = new JLabel("Código:");
			lblCdigo.setBounds(12, 14, 70, 15);
			panel.add(lblCdigo);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(12, 43, 70, 15);
			panel.add(lblNombre);
			
			JLabel lblTipo = new JLabel("Tipo:");
			lblTipo.setBounds(12, 72, 70, 15);
			panel.add(lblTipo);
			
			JLabel lblCosecha = new JLabel("Cosecha:");
			lblCosecha.setBounds(211, 72, 70, 15);
			panel.add(lblCosecha);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "Disponibilidad", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(6, 101, 210, 100);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblReal = new JLabel("Real:");
			lblReal.setBounds(12, 20, 70, 15);
			panel_1.add(lblReal);
			
			JLabel lblMnima = new JLabel("Mínima:");
			lblMnima.setBounds(12, 45, 70, 15);
			panel_1.add(lblMnima);
			
			JLabel lblMxima = new JLabel("Máxima:");
			lblMxima.setBounds(12, 70, 70, 15);
			panel_1.add(lblMxima);
			
			JSpinner spDispMin = new JSpinner();
			spDispMin.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), new Integer(1), new Integer(1)));
			spDispMin.setBounds(86, 42, 112, 20);
			panel_1.add(spDispMin);
			
			JSpinner spDispReal = new JSpinner();
			spDispReal.setBounds(86, 17, 112, 20);
			spDispReal.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), new Integer(1), new Integer(1)));
			panel_1.add(spDispReal);
			
			JSpinner spDispMax = new JSpinner();
			spDispMax.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					int meh;
					meh = Integer.valueOf(spDispReal.getValue().toString())>Integer.valueOf(spDispMax.getValue().toString()) ? Integer.valueOf(spDispMax.getValue().toString()) : Integer.valueOf(spDispReal.getValue().toString());
					spDispReal.setModel(new SpinnerNumberModel(new Integer(meh), new Integer(1), Integer.valueOf(spDispMax.getValue().toString()), new Integer(1)));
					meh = Integer.valueOf(spDispMin.getValue().toString())>Integer.valueOf(spDispMax.getValue().toString()) ? Integer.valueOf(spDispMax.getValue().toString()) : Integer.valueOf(spDispMin.getValue().toString());
					spDispMin.setModel(new SpinnerNumberModel(new Integer(meh), new Integer(0), Integer.valueOf(spDispMax.getValue().toString()), new Integer(1)));
				}
			});
			spDispMax.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spDispMax.setBounds(86, 67, 112, 20);
			panel_1.add(spDispMax);
			
			txtCdigo = new JTextField();
			txtCdigo.setEditable(false);
			txtCdigo.setBounds(79, 12, 114, 19);
			panel.add(txtCdigo);
			txtCdigo.setColumns(10);
			txtCdigo.setText("v-"+miAlma.getGeneradorCodigoVino());
			
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(79, 41, 334, 19);
			panel.add(txtNombre);
			
			JSpinner spCosecha = new JSpinner();
			spCosecha.setModel(new SpinnerNumberModel(new Integer(1950), new Integer(1800), new Integer(2019), new Integer(1)));
			spCosecha.setBounds(290, 69, 123, 20);
			panel.add(spCosecha);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Precios", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
			panel_2.setBounds(222, 101, 210, 100);
			panel.add(panel_2);
			panel_2.setLayout(null);
			
			JLabel lblCompra = new JLabel("Compra:");
			lblCompra.setBounds(12, 23, 70, 15);
			panel_2.add(lblCompra);
			
			JLabel lblVenta = new JLabel("Venta:");
			lblVenta.setBounds(12, 61, 70, 15);
			panel_2.add(lblVenta);
			
			JSpinner spCompra = new JSpinner();
			spCompra.setBounds(77, 20, 112, 20);
			panel_2.add(spCompra);
			
			JSpinner spVenta = new JSpinner();
			spVenta.setBounds(77, 58, 112, 20);
			panel_2.add(spVenta);
			
			JLabel lblSuministrador = new JLabel("Suministrador: ");
			lblSuministrador.setBounds(12, 215, 123, 15);
			panel.add(lblSuministrador);
			
			JComboBox cbSuministrador = new JComboBox();
			String[] sumis = new String[miAlma.getCantSumi()+1];
			sumis[0] = "Selecione";
			for(i=0;i<miAlma.getCantSumi();i++) {
				sumis[i+1] = miAlma.getMisSumis()[i].getNombre();
			}
			cbSuministrador.setModel(new DefaultComboBoxModel(sumis));
			
			cbSuministrador.setBounds(131, 210, 282, 24);
			panel.add(cbSuministrador);
			
			JComboBox cbTipo = new JComboBox();
			cbTipo.setModel(new DefaultComboBoxModel(new String[] {"Selecione", "Tinto", "Blanco", "Rosado"}));
			cbTipo.setBounds(79, 67, 114, 24);
			panel.add(cbTipo);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
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
}
