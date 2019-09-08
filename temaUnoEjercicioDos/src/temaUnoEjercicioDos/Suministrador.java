package temaUnoEjercicioDos;

public class Suministrador {
	//Atributos
	private String nombre;
	private String pais;
	private int tiempo;
	//get y set
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
		if(tiempo>0) {
			this.tiempo = tiempo;
		} else {
			System.out.println("El tiempo de entraga del suministrador debe ser positivo.");
		}
	}
	
	//Constructores
	public Suministrador() {
		
	}
	
	public Suministrador(String nombre, String pais, int tiempo) {
		this.nombre = nombre;
		this.pais = pais;
		this.setTiempo(tiempo);
	}
}
