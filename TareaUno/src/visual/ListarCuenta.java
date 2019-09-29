package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Banco;
import logico.Cliente;
import logico.Cuenta;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListarCuenta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private Object[] row;
	private Banco popular;
	private Cliente cliente;
	private String idCuenta;
	private JButton btnRehabilitar;
	private JButton btnBloquear;
	private JButton btnCancelar;
	private JButton btnAbrirCuenta;

	/**
	 * Launch the application.
	 */
	/**
	public static void main(String[] args) {
		try {
			ListarCuenta dialog = new ListarCuenta();
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
	public ListarCuenta(Banco popular, Cliente cliente) {
		setResizable(false);
		this.popular = popular;
		this.cliente = cliente;
		setTitle((cliente==null)? "Cuentas" : "Cuentas de "+cliente.getCedula());
		setBounds(100, 100, 800, 374);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
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
				String[] header = {"N\u00FAmero","Tipo","Estado","Saldo","D\u00EDa de corte","Fecha de apertura"};
				model.setColumnIdentifiers(header);
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = table.getSelectedRow();
						String estado = String.valueOf(table.getValueAt(index, 2));
						if(estado.equalsIgnoreCase("bloqueada")) {
							btnBloquear.setEnabled(false);
							btnCancelar.setEnabled(true);
							btnRehabilitar.setEnabled(true);
						} else if (estado.equalsIgnoreCase("habilitada")) {
							btnBloquear.setEnabled(true);
							btnCancelar.setEnabled(true);
							btnRehabilitar.setEnabled(false);
						} else {
							lock();
						}
						idCuenta = String.valueOf(table.getValueAt(index, 0));
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
				btnRehabilitar = new JButton("Rehabilitar");
				btnRehabilitar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(popular.cambiarEstadoCuenta(popular.buscarCuenta(idCuenta), "rehabilitar")) {
							JOptionPane.showMessageDialog(null, "Operaci\u00F3n satisfatoria");
							load();
							lock();
						} else {
							JOptionPane.showMessageDialog(null, "Operaci\u00F3n no satisfatoria:\nYa existe uno de esto habilitada.");
						}
					}
				});
				if(!(cliente==null)){
					btnAbrirCuenta = new JButton("Abrir cuenta");
					btnAbrirCuenta.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							RegCuenta regCuenta = new RegCuenta(popular, cliente);
							regCuenta.setModal(true);
							regCuenta.setVisible(true);
							load();
							lock();
						}
					});
					buttonPane.add(btnAbrirCuenta);
				}
				btnRehabilitar.setEnabled(false);
				buttonPane.add(btnRehabilitar);
			}
			{
				btnBloquear = new JButton("Bloquear");
				btnBloquear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(popular.cambiarEstadoCuenta(popular.buscarCuenta(idCuenta), "bloquear")) {
							JOptionPane.showMessageDialog(null, "Operaci\u00F3n satisfatoria");
							load();
							lock();
						} else {
							JOptionPane.showMessageDialog(null, "Operaci\u00F3n no satisfatoria");
						}
					}
				});
				btnBloquear.setEnabled(false);
				buttonPane.add(btnBloquear);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						popular.cambiarEstadoCuenta(popular.buscarCuenta(idCuenta), "cancelar");
						JOptionPane.showMessageDialog(null, "Operaci\u00F3n satisfatoria");
						load();
						lock();
					}
				});
				btnCancelar.setEnabled(false);
				buttonPane.add(btnCancelar);
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
		load();
		
	}
	
	private void lock() {
		btnBloquear.setEnabled(false);
		btnCancelar.setEnabled(false);
		btnRehabilitar.setEnabled(false);
	}
	
	private void load() {
		if(cliente==null) {
			loadCuenta(popular.getCuentas(), popular.getCantCuentas());
		} else {
			loadCuenta(cliente.getCuentas(), cliente.getCantCuentas());
		}
	}
	
	private void loadCuenta(Cuenta[] cuentas, int cant) {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (int i = 0; i < cant; i++) 
		{
			row[0]=cuentas[i].getCodigo();
			row[1]=cuentas[i].getTipo();
			row[2]=cuentas[i].getEstado();
			row[3]=cuentas[i].getSaldo();
			row[4]=cuentas[i].getDiaCorteMensual();
			int[] fecha = cuentas[i].getFechaApertura();
			row[5]=(fecha[0]+"/"+fecha[1]+"/"+fecha[2]);
			model.addRow(row);
		}
		
	}

}
