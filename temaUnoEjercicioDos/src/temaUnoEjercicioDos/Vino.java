package temaUnoEjercicioDos;

public class Vino {
	//Atributos
	private String nombre;
	private String cosecha;
	private int anno;
	private String tipo;
	private int disponibilidadMin = 0;
	private int disponibilidadMax = 0;
	private int disponibilidadReal;
	private int[] ventas;
	private Suministrador suministrador;
	
	//get y set
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCosecha() {
		return cosecha;
	}
	public void setCosecha(String cosecha) {
		this.cosecha = cosecha;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		String[] aceptables = {"tinto","blanco","rosado"};
		for(String verificar:aceptables) {
			if(tipo==verificar) {
				this.tipo = tipo;
				return;
			}
		}
		System.out.print("El tipo debe ser uno de los siguientes: ");
		for(int i=0; i<(aceptables.length-1); i++) {
			System.out.print(aceptables[i]+", ");
		}
		System.out.println(aceptables[aceptables.length-1] + ".");
	}
	public int getDisponibilidadMin() {
		return disponibilidadMin;
	}
	public void setDisponibilidadMin(int disponibilidadMin) {
		if(disponibilidadMin>0) {
			this.disponibilidadMin = disponibilidadMin;
		} else {
			System.out.println("La disponibilidad mínima debe ser mayor que 0.");
		}
	}
	public int getDisponibilidadMax() {
		return disponibilidadMax;
	}
	public void setDisponibilidadMax(int disponibilidadMax) {
		if(disponibilidadMax>this.disponibilidadMin) {
			this.disponibilidadMax = disponibilidadMax;
		} else {
			System.out.println("La disponibilidad máxima debe ser mayor que la disponibilidad mínima.");
		}
	}
	public int getDisponibilidadReal() {
		return disponibilidadReal;
	}
	public void setDisponibilidadReal(int disponibilidadReal) {
		if (this.disponibilidadMax <= this.disponibilidadMin) {
			System.out.println("Defina primero la disponibilidad máxima.");
			return;
		}
		if(disponibilidadReal>=0 && disponibilidadReal <= this.disponibilidadMax) {
			this.disponibilidadReal = disponibilidadReal;
		} else {
			System.out.println("La disponibilidad real debe ser entre 0 y " + this.disponibilidadMax + ".");
		}
	}
	public int[] getVentas() {
		return ventas;
	}
	public void setVentas(int[] ventas) {
		if(ventas.length==10) {
			this.ventas = ventas;
		} else {
			System.out.println("La lista debe de contener 10 elementos.");
		}
	}
	public Suministrador getSuministrador() {
		return suministrador;
	}
	public void setSuministrador(Suministrador suministrador) {
		this.suministrador = suministrador;
	}
	
	//Constructores
	public Vino() {
		
	}
	
	public Vino(String nombre, String cosecha, int anno, String tipo, int disponibilidadMax, int disponibilidadMin, int disponibilidadReal, int[] ventas, Suministrador suministrador) {
		this.nombre = nombre;
		this.cosecha = cosecha;
		this.anno = anno;
		this.setTipo(tipo);
		this.setDisponibilidadMax(disponibilidadMax);
		this.setDisponibilidadMin(disponibilidadMin);
		this.setDisponibilidadReal(disponibilidadReal);
		this.setVentas(ventas);
		this.suministrador = suministrador;
	}
	
	//Operaciones
	public void pushVenta(int valor) {
		for(int i=this.ventas.length-1;i>0;i--) {
			this.ventas[i]=this.ventas[i-1];
		}
		this.ventas[0]=valor;
	}
	
	private int promedioVentasRango(int desde, int hasta) {
		int promedio = 0;
		for(int i=desde;i<=hasta;i++) {
			promedio += this.ventas[i];
		}
		return promedio/3;
	}
	
	public boolean verificarSiDebePedir() {
		try {
			this.getVentas().toString();
			this.suministrador.getNombre();
		} catch (Exception e) {
			System.out.println("Error: No fue declarada la venta o el suministrador.");
			return false;
		}
		if (this.ventas[0] > this.promedioVentasRango(1,3) && this.suministrador.getTiempo() < 30 && this.disponibilidadReal < this.disponibilidadMin) {
			return true;
		}
		return false;
	}
}
