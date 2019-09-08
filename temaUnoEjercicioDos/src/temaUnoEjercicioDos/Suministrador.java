package temaUnoEjercicioDos;

public class Suministrador {
	private String nombre;
	private String pais;
	private int tiempo;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	public Suministrador(String nombre, String pais, int tiempo) {
		this.nombre = nombre;
		this.pais = pais;
		this.tiempo = tiempo;
	}
}
