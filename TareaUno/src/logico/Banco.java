package logico;

public class Banco {
	private Cliente[] clientes;
	private Cuenta[] cuentas;
	private static int cantClientes = 0;
	private static int cantCuentas = 0;
	private static int generadorCodigoCuenta = 1;

	public Cliente[] getClientes() {
		return clientes;
	}

	public Cuenta[] getCuentas() {
		return cuentas;
	}

	public static int getCantClientes() {
		return cantClientes;
	}

	public static int getCantCuentas() {
		return cantCuentas;
	}

	public static int getGeneradorCodigoCuenta() {
		return generadorCodigoCuenta;
	}

	public Banco() {
		super();
		this.clientes = new Cliente[50];
		this.cuentas = new Cuenta[50];
	}

	public Cliente buscarCliente(String cedula) {
		Cliente aux = null;
		int i = 0;
		while (i < cantClientes) {
			if (clientes[i].getCedula().equalsIgnoreCase(cedula)) {
				aux = clientes[i];
				break;
			}
			i++;
		}
		return aux;
	}

	public boolean insertarCliente(Cliente cliente) {
		if (buscarCliente(cliente.getCedula()) == null) {
			clientes[cantClientes] = cliente;
			cantClientes++;
			return true;
		}
		return false;
	}

	public void eliminarCliente(String cedula) {
		int i = 0, j = 0;
		Cuenta[] cuentasAEliminar = null;
		while (i < cantClientes) {
			if (this.clientes[i].getCedula().equalsIgnoreCase(cedula)) {
				cantClientes--;
				cuentasAEliminar = clientes[i].getCuentas();
				while (j < this.clientes[i].getCantCuentas()) {
					eliminarCuenta(cuentasAEliminar[j].getCodigo());
					j++;
				}
				while (i < cantClientes) {
					clientes[i] = clientes[i + 1];
					i++;
				}
				clientes[i] = null;
			}
			i++;
		}
	}

	public Cuenta buscarCuenta(String codigo) {
		Cuenta aux = null;
		int i = 0;
		while (i < cantClientes) {
			if (cuentas[i].getCodigo().equalsIgnoreCase(codigo)) {
				aux = cuentas[i];
				break;
			}
			i++;
		}
		return aux;
	}

	private void eliminarCuenta(String codigo) {
		int i = 0;
		while (i < cantCuentas) {
			if (this.cuentas[i].getCodigo().equalsIgnoreCase(codigo)) {
				cantCuentas--;
				while (i < cantCuentas) {
					cuentas[i] = cuentas[i + 1];
					i++;
				}
				cuentas[i] = null;
			}
			i++;
		}
	}

	public boolean abrirCuenta(Cliente cliente, Cuenta cuenta) {
		boolean paso = cliente.insertarCuenta(cuenta);
		if (paso) {
			this.cuentas[cantCuentas] = cuenta;
			this.cantCuentas++;
			this.generadorCodigoCuenta++;
		}
		return paso;
	}

	public int totalPuntos(Cliente cliente) {
		int sum = 0, i = 0;
		Cuenta[] lasCuentas = cliente.getCuentas();
		while (i < cliente.getCantCuentas()) {
			sum += lasCuentas[i].puntos();
		}
		return sum;
	}

	public boolean retirarDeCuenta(String cedulaCliente, String codigoCuenta, float cantidad) {
		return buscarCuenta(codigoCuenta).retirar(cedulaCliente, cantidad);
	}

	public boolean ingresarACuenta(String codigo, float cantidad) {
		return buscarCuenta(codigo).ingresar(cantidad);
	}

	public boolean cambiarEstadoCuenta(Cuenta cuenta, String estado) {
		boolean paso = false;
		if (estado.equalsIgnoreCase("rehabilitar")) {
			paso = cuenta.rehabilitar(this);
		} else if (estado.equalsIgnoreCase("bloquear")) {
			paso = cuenta.bloquear();
		} else if (estado.equalsIgnoreCase("cancelar")) {
			cuenta.cancelar();
		}
		return paso;
	}

	public float revTotal(Cliente cliente) {
		float saldoTotal = 0;
		int i = 0;
		Cuenta[] lasCuentas = cliente.getCuentas();
		while (i < cliente.getCantCuentas()) {
			saldoTotal += lasCuentas[i].revisionMensual();
		}
		return saldoTotal;
	}
}
