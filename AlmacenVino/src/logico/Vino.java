package logico;

public class Vino {
	private String id;
	private String name;
	private String tipo;
	private int cosecha;
	private int dispMin;
	private int dispMax;
	private int dispReal;
	private int[] ventas;
	private Suministrador miSumi;
	public Vino(String id, String name, String tipo, int cosecha, int dispMin, int dispMax, int dispReal,
			Suministrador miSumi) {
		super();
		this.id = id;
		this.name = name;
		this.tipo = tipo;
		this.cosecha = cosecha;
		this.dispMin = dispMin;
		this.dispMax = dispMax;
		this.dispReal = dispReal;
		this.miSumi = miSumi;
		this.ventas = new int[10];
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getCosecha() {
		return cosecha;
	}
	public void setCosecha(int cosecha) {
		this.cosecha = cosecha;
	}
	public int getDispMin() {
		return dispMin;
	}
	public void setDispMin(int dispMin) {
		this.dispMin = dispMin;
	}
	public int getDispMax() {
		return dispMax;
	}
	public void setDispMax(int dispMax) {
		this.dispMax = dispMax;
	}
	public int getDispReal() {
		return dispReal;
	}
	public void setDispReal(int dispReal) {
		this.dispReal = dispReal;
	}
	public int[] getVentas() {
		return ventas;
	}
	public void setVentas(int[] ventas) {
		this.ventas = ventas;
	}
	public Suministrador getMiSumi() {
		return miSumi;
	}
	public void setMiSumi(Suministrador miSumi) {
		this.miSumi = miSumi;
	}
	public boolean promedioVentas() {
		boolean promedio = false;
		int suma = 0;
		for (int i = 6; i<8; i++) {
			suma+= ventas[i];
		}
		if(ventas[9]>suma/3) {
			promedio = true;
		}
		return promedio;
	}
}
