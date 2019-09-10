package logico;

public class Suministrador {
	private String id;
	private String nombre;
	private String pais;
	private int tiempoEntrega;
	
	public Suministrador(String id, String nombre, String pais, int tiempoEntrega) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
		this.tiempoEntrega = tiempoEntrega;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public int getTiempoEntrega() {
		return tiempoEntrega;
	}

	public void setTiempoEntrega(int tiempoEntrega) {
		this.tiempoEntrega = tiempoEntrega;
	}
}
