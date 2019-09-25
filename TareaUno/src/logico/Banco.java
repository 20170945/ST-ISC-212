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
	
	private Cliente buscarCliente(String cedula) {
		Cliente aux = null;
		int i = 0;
		while(i<cantClientes) {
			if(clientes[i].getCedula().equalsIgnoreCase(cedula)) {
				aux = clientes[i];
				break;
			}
			i++;
		}
		return aux;
	}
	
	public boolean insertarCliente(Cliente cliente) {
		if(buscarCliente(cliente.getCedula())==null) {
			clientes[cantClientes]=cliente;
			cantClientes++;
			return true;
		}
		return false;
	}

	public void eliminarCliente(String cedula) {
		int i=0;
		while(i<cantClientes) {
			if(this.clientes[i].getCedula().equalsIgnoreCase(cedula)) {
				cantClientes--;
				while(i<cantClientes) {
					clientes[i] = clientes[i+1];
					i++;
				}
				clientes[i]=null;
			}
			i++;
		}
	}
	
	private Cuenta buscarCuenta(String codigo) {
		Cuenta aux = null;
		int i = 0;
		while(i<cantClientes) {
			if(cuentas[i].getCodigo().equalsIgnoreCase(codigo)) {
				aux = cuentas[i];
				break;
			}
			i++;
		}
		return aux;
	}
	
	public void insertarCuenta(Cuenta cuenta) {
		cuentas[cantCuentas] = cuenta;
		cantCuentas++;
		generadorCodigoCuenta++;
	}
	
	public void eliminarCuenta(String codigo) {
		int i=0;
		while(i<cantCuentas) {
			if(this.cuentas[i].getCodigo().equalsIgnoreCase(codigo)) {
				cantCuentas--;
				while(i<cantCuentas) {
					cuentas[i] = cuentas[i+1];
					i++;
				}
				cuentas[i]=null;
			}
			i++;
		}
	}
	
	public void addAccountClient(Cliente cliente, Cuenta cuenta) {
		cliente.insertarCuenta(cuenta);
	}
	
	public int totalPuntos(Cliente cliente) {
		int sum=0, i=0;
		Cuenta[] lasCuentas = cliente.getCuentas();
		while(i<cliente.getCantCuentas()) {
			sum+=lasCuentas[i].puntos();
		}
		return sum;
	}
	
	public boolean retirarDeCuenta(String cedulaCliente, String codigoCuenta, float cantidad) {
		boolean paso = false;
		Cliente cliente = buscarCliente(cedulaCliente);
		if(cliente!=null) {
			int i=0;
			Cuenta[] lasCuentas = cliente.getCuentas();
			while(i<cliente.getCantCuentas()) {
				if(lasCuentas[i].getCodigo().equalsIgnoreCase(codigoCuenta)) {
					paso = lasCuentas[i].retirar(cantidad);
					break;
				}
			}
		}
		return paso;
	}
	
	public boolean ingresarACuenta(String codigo, float cantidad) {
		return buscarCuenta(codigo).ingresar(cantidad);
	}
	
	public void cambiarEstadoCuenta(Cuenta cuenta, String estado) {
		if(estado.equalsIgnoreCase("rehabilitar")) {
			cuenta.rehabilitar();
		} else if(estado.equalsIgnoreCase("bloquear")) {
			cuenta.bloquear();
		} else if(estado.equalsIgnoreCase("cancelar")) {
			cuenta.cancelar();
		}
	}
	
	public float revTotal(Cliente cliente) {
		float saldoTotal = 0;
		int i=0;
		Cuenta[] lasCuentas = cliente.getCuentas();
		while(i<cliente.getCantCuentas()) {
			saldoTotal+=lasCuentas[i].revisionMensual();
		}
		return saldoTotal;
	}
}
