package logico;

public class Cuenta {
	private String codigo;
	private String tipo;
	private String estado;
	private float saldo = 0;
	private float dineroIngresado = 0;
	private int puntosCajeados = 0;
	private float interes;
	private int[] fechaApertura;
	private int[] diaCorteMensual;
	
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
	public float getDineroIngresado() {
		return dineroIngresado;
	}
	public float getPuntosCajeados() {
		return puntosCajeados;
	}
	public float getInteres() {
		return interes;
	}
	public int[] getFechaApertura() {
		return fechaApertura;
	}
	public int[] getDiaCorteMensual() {
		return diaCorteMensual;
	}
	
	public Cuenta(String codigo, String tipo, String estado, float interes, int[] fechaApertura, int[] diaCorteMensual) {
		super();
		int i;
		this.codigo = codigo;
		this.tipo = tipo;
		this.estado = estado;
		this.interes = interes;
		this.fechaApertura = new int[3];
		this.diaCorteMensual = new int[2];
		i = 0;
		while(i<3) {
			this.fechaApertura[i] = fechaApertura[i];
			if(i<2) {
				this.diaCorteMensual[i] = diaCorteMensual[i];
			}
			i++;
		}
	}
	
	public int puntos() {
		return (int) (this.dineroIngresado/6) - puntosCajeados;
	}
	
	protected boolean retirar(float cantidad) {
		boolean paso = false;
		if(this.saldo-cantidad>0 && this.estado.equalsIgnoreCase("habilitado")) {
			if(this.tipo.equalsIgnoreCase("cuenta corriente")) {
				paso = true;
			}
			else if(this.tipo.equalsIgnoreCase("fondo inversion") && this.dineroIngresado-this.saldo<=500) {
				paso = true;
				if(this.dineroIngresado-this.saldo+cantidad>500) {
					this.bloquear();
				}
			}
		}
		if(paso) {
			this.saldo -= cantidad;
		}
		return paso;
	}
	
	protected boolean ingresar(float cantidad) {
		boolean paso = false;
		if(this.estado.equalsIgnoreCase("habilitada")) {
			paso = true;
			this.saldo += cantidad;
		}
		return paso;
	}
	
	protected void bloquear() {
		if(this.estado.equalsIgnoreCase("habilitada")) {
			this.estado = "bloqueada";
		}
	}
	
	protected void rehabilitar() {
		if(this.estado.equalsIgnoreCase("bloqueada")) {
			this.estado = "habilitada";
		}
	}
	
	protected void cancelar() {
		this.estado = "cancelada";
	}
	
	protected boolean cajearPuntos(int cantidad) {
		boolean paso = false;
		if(cantidad<=this.puntos()) {
			puntosCajeados+=1;
			paso = true;
		}
		return paso;
	}
	
	protected float revisionMensual() {
		if(this.estado.equalsIgnoreCase("habilitada")) {
			return (float) (this.saldo*(1+this.interes) - 0.6);
		}
		return 0;
	}
}
