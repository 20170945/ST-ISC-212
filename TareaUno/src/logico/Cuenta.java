package logico;

public class Cuenta {
	private String cedulaCliente;
	private String codigo;
	private String tipo;
	private String estado;
	private float saldo = 0;
	private float interes;
	private int[] fechaApertura;
	private int diaCorteMensual;

	public String getCedulaCliente() {
		return cedulaCliente;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public String getEstado() {
		return estado;
	}

	public float getSaldo() {
		return saldo;
	}

	public float getInteres() {
		return interes;
	}

	public int[] getFechaApertura() {
		return fechaApertura;
	}

	public int getDiaCorteMensual() {
		return diaCorteMensual;
	}

	public Cuenta(String cedulaCliente, String codigo, String tipo, float interes, int[] fechaApertura,
			int diaCorteMensual) {
		super();
		int i;
		this.cedulaCliente = cedulaCliente;
		this.codigo = codigo;
		this.tipo = tipo;
		this.estado = "habilitada";
		this.interes = interes;
		this.fechaApertura = new int[3];
		i = 0;
		while (i < 3) {
			this.fechaApertura[i] = fechaApertura[i];
			i++;
		}
		this.diaCorteMensual = fechaApertura[0];
	}

	public int puntos() {
		return (int) (this.saldo / 6);
	}

	public boolean retirar(String cedula, float cantidad) {
		boolean paso = false;
		if (this.cedulaCliente.equalsIgnoreCase(cedula)) {
			if (this.saldo - cantidad >= 0 && this.estado.equalsIgnoreCase("habilitada")) {
				if (this.tipo.equalsIgnoreCase("cuenta corriente")) {
					paso = true;
				} else if (this.tipo.equalsIgnoreCase("fondo de inversi\u00F3n")) {
					paso = true;
					if (cantidad > 500) {
						this.bloquear();
					}
				}
			}
			if (paso) {
				this.saldo -= cantidad;
			}
		}
		return paso;
	}

	public boolean ingresar(float cantidad) {
		boolean paso = false;
		if (this.estado.equalsIgnoreCase("habilitada")) {
			paso = true;
			this.saldo += cantidad;
		}
		return paso;
	}

	public boolean bloquear() {
		if (this.estado.equalsIgnoreCase("habilitada")) {
			this.estado = "bloqueada";
			return true;
		}
		return false;
	}

	public boolean rehabilitar(Banco popular) {
		Cliente cliente = popular.buscarCliente(getCedulaCliente());
		if (this.estado.equalsIgnoreCase("bloqueada")) {
			int i = 0;
			Cuenta[] cuentas = cliente.getCuentas();
			while(i<cliente.getCantCuentas()) {
				if(cuentas[i].getEstado().contentEquals("habilitada") && cuentas[i].getTipo().equalsIgnoreCase(this.tipo)) {
					return false;
				}
				i++;
			}
			this.estado = "habilitada";
			return true;
		}
		return false;
	}

	public void cancelar() {
		this.estado = "cancelada";
	}

	public float revisionMensual() {
		if (this.estado.equalsIgnoreCase("habilitada")) {
			return (float) (this.saldo * (1 + this.interes) - 0.6);
		}
		return 0;
	}
}
