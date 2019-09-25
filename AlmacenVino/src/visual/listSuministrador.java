package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Almacen;

import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;

public class listSuministrador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private Object[] row;
	private Almacen miAlma;
	private String idSupli = "";
	private JButton okButton;
	private JButton btnEliminar;

	/**
	 * Launch the application.
	 */
	/**
	public static void main(String[] args) {
		try {
			listSuministrador dialog = new listSuministrador();
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
	public listSuministrador(Almacen miAlma) {
		setResizable(false);
		setTitle("Listado de Suministradores");
		this.miAlma = miAlma;
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel.add(scrollPane);
			
			model = new DefaultTableModel();
			String[] headers = {"Código","Nombre","País","Tiempo"};
			model.setColumnIdentifiers(headers);
			
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(table.getSelectedRow()>-1) {
						int index = table.getSelectedRow();
						okButton.setEnabled(true);
						btnEliminar.setEnabled(true);
						idSupli = String.valueOf(table.getValueAt(index, 0));
					}
				}
			});
			table.setModel(model);
			scrollPane.setViewportView(table);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Modificar");
				okButton.setEnabled(false);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!idSupli.equals("")) {
							RegSuministrador modSumi = new RegSuministrador(miAlma, idSupli);
							modSumi.setModal(true);
							modSumi.setVisible(true);
							loadSuplidores();
							btnEliminar.setEnabled(false);
							okButton.setEnabled(false);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!idSupli.equals("")) {
							miAlma.eliminarSuministrador(idSupli);
							loadSuplidores();
							btnEliminar.setEnabled(false);
							okButton.setEnabled(false);
						}
					}
				});
				btnEliminar.setEnabled(false);
				buttonPane.add(btnEliminar);
			}
			{
				JButton cancelButton = new JButton("Volver");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadSuplidores();
	}

	private void loadSuplidores() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (int i = 0; i < miAlma.getCantSumi(); i++) 
		{
			row[0] = miAlma.getMisSumis()[i].getId();
			row[1] = miAlma.getMisSumis()[i].getNombre();
			row[2] = miAlma.getMisSumis()[i].getPais();
			row[3] = miAlma.getMisSumis()[i].getTiempoEntrega();
			model.addRow(row);
		}
		
	}
}
