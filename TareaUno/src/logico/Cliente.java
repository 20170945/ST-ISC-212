package logico;

public class Cliente {
	private String cedula;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String telefono;
	private int cantCuentas = 0;
	private Cuenta[] cuentas;

	public Cliente(String cedula, String nombre, String apellidos, String direccion, String telefono) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.cuentas = new Cuenta[50];
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCedula() {
		return cedula;
	}

	public int getCantCuentas() {
		return cantCuentas;
	}

	public Cuenta[] getCuentas() {
		return cuentas;
	}

	public boolean insertarCuenta(Cuenta cuenta) {
		int i = 0;
		while (i < cantCuentas) {
			if (cuentas[i].getEstado().equalsIgnoreCase("habilitada")
					&& cuentas[i].getTipo().equalsIgnoreCase(cuenta.getTipo())) {
				return false;
			}
			i++;
		}
		cuentas[cantCuentas] = cuenta;
		cantCuentas++;
		return true;
	}

	public void eliminarCuenta(String codigo) {
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
}
