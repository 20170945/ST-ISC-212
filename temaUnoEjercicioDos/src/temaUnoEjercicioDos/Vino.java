package temaUnoEjercicioDos;

public class Vino {
	private String nombre;
	private int year;
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
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
	
	
	
}
