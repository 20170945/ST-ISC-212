package temaUnoEjercicioDos;

public class Vino {
	private String nombre;
	private String cosecha;
	private int anno;
	private String tipo;
	private int disponibilidadMin;
	private int disponibilidadMax;
	private int disponibilidadReal;
	private int[] ventas;
	private Suministrador suministrador;
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
		this.tipo = tipo;
	}
	public int getDisponibilidadMin() {
		return disponibilidadMin;
	}
	public void setDisponibilidadMin(int disponibilidadMin) {
		this.disponibilidadMin = disponibilidadMin;
	}
	public int getDisponibilidadMax() {
		return disponibilidadMax;
	}
	public void setDisponibilidadMax(int disponibilidadMax) {
		this.disponibilidadMax = disponibilidadMax;
	}
	public int getDisponibilidadReal() {
		return disponibilidadReal;
	}
	public void setDisponibilidadReal(int disponibilidadReal) {
		this.disponibilidadReal = disponibilidadReal;
	}
	public int[] getVentas() {
		return ventas;
	}
	public void setVentas(int[] ventas) {
		this.ventas = ventas;
	}
	public Suministrador getSuministrador() {
		return suministrador;
	}
	public void setSuministrador(Suministrador suministrador) {
		this.suministrador = suministrador;
	}
	public Vino(String nombre, String cosecha, int anno, String tipo, int disponibilidadMax, int disponibilidadMin, int disponibilidadReal, int[] ventas, Suministrador suministrador) {
		this.nombre = nombre;
		this.cosecha = cosecha;
		this.anno = anno;
		this.tipo = tipo;
		this.disponibilidadMax = disponibilidadMax;
		this.disponibilidadMin = disponibilidadMin;
		this.disponibilidadReal = disponibilidadReal;
		this.ventas = ventas;
		this.suministrador = suministrador;
	}
	
}
