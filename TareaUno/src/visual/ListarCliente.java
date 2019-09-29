package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Banco;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class ListarCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Banco popular;
	private JTable table;
	private DefaultTableModel model;
	private Object[] row;
	private JButton okButton;
	private String idCliente;
	private JButton btnEliminar;
	private JButton btnVerCuentas;

	/**
	 * Launch the application.
	 */
	/**
	 * public static void main(String[] args) { try { ListarCliente dialog = new
	 * ListarCliente(); dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */

	/**
	 * Create the dialog.
	 */
	public ListarCliente(Banco popular) {
		setTitle("Clientes");
		setResizable(false);
		this.popular = popular;
		setBounds(100, 100, 750, 400);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{

				model = new DefaultTableModel();
				String[] headers = { "Cédula", "Nombre", "Apellidos" };
				model.setColumnIdentifiers(headers);
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (table.getSelectedRow() > -1) {
							int index = table.getSelectedRow();
							okButton.setEnabled(true);
							btnVerCuentas.setEnabled(true);
							btnEliminar.setEnabled(true);
							idCliente = String.valueOf(table.getValueAt(index, 0));
						}
					}
				});
				table.setModel(model);
				scrollPane.setViewportView(table);
			}
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
						RegCliente modClient = new RegCliente(popular, popular.buscarCliente(idCliente));
						modClient.setModal(true);
						modClient.setVisible(true);
						loadClientes();
						lock();
					}
				});
				{
					btnVerCuentas = new JButton("Ver cuentas");
					btnVerCuentas.setEnabled(false);
					btnVerCuentas.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ListarCuenta verCuenta = new ListarCuenta(popular, popular.buscarCliente(idCliente));
							verCuenta.setModal(true);
							verCuenta.setVisible(true);
							lock();
						}
					});
					buttonPane.add(btnVerCuentas);
				}
				buttonPane.add(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					btnEliminar = new JButton("Eliminar");
					btnEliminar.setEnabled(false);
					btnEliminar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							popular.eliminarCliente(idCliente);
							loadClientes();
							lock();
						}
					});
					buttonPane.add(btnEliminar);
				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadClientes();
	}

	private void lock() {
		okButton.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnVerCuentas.setEnabled(false);
	}

	private void loadClientes() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (int i = 0; i < popular.getCantClientes(); i++) {
			row[0] = popular.getClientes()[i].getCedula();
			row[1] = popular.getClientes()[i].getNombre();
			row[2] = popular.getClientes()[i].getApellidos();
			model.addRow(row);
		}

	}

}
