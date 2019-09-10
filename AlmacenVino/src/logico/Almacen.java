package logico;

public class Almacen {
	private Vino[] misVinos;
	private Suministrador[] misSumis;
	private static int cantVinos;
	private static int cantSumi;
	public Almacen() {
		super();
		cantSumi = 0;
		cantVinos = 0;
		misSumis = new Suministrador[50];
		misVinos = new Vino[50];
	}
	public Vino[] getMisVinos() {
		return misVinos;
	}
	public void setMisVinos(Vino[] misVinos) {
		this.misVinos = misVinos;
	}
	public Suministrador[] getMisSumis() {
		return misSumis;
	}
	public void setMisSumis(Suministrador[] misSumis) {
		this.misSumis = misSumis;
	}
	public static int getCantVinos() {
		return cantVinos;
	}
	public static void setCantVinos(int cantVinos) {
		Almacen.cantVinos = cantVinos;
	}
	public static int getCantSumi() {
		return cantSumi;
	}
	public static void setCantSumi(int cantSumi) {
		Almacen.cantSumi = cantSumi;
	}
	
	public void insertarSuministrador(Suministrador sumi) {
		misSumis[cantSumi] = sumi;
		cantSumi++;
	}
	
	public void insertarVino(Vino vino) {
		misVinos[cantVinos] = vino;
		cantVinos++;
	}
	
	public boolean hacerPedido(String idVino) {
		boolean hacer = false;
		Vino aux = buscarVino(idVino);
		if(aux!=null) {
			if(aux.getMiSumi().getTiempoEntrega() < 30 && aux.getDispReal() < aux.getDispMin() && aux.promedioVentas()) {
				
			}
		}
		return hacer;
	}
	
	private Vino buscarVino(String idVino) {
		// TODO Auto-generated method stub
		Vino aux = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado) {
			if(misVinos[i].getId().equalsIgnoreCase(idVino)) {
				aux = misVinos[i];
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	private Suministrador buscarSuministrador(String idSumi) {
		// TODO Auto-generated method stub
		Suministrador aux = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado) {
			if(misSumis[i].getId().equalsIgnoreCase(idSumi)) {
				aux = misSumis[i];
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
}
