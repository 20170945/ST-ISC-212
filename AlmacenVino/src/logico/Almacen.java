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
		while(!encontrado & i<cantVinos) {
			if(misVinos[i].getId().equalsIgnoreCase(idVino)) {
				aux = misVinos[i];
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	private Suministrador buscarSuministrador(String idSumi) {
		Suministrador aux = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado &&  i<cantSumi) {
			if(misSumis[i].getId().equalsIgnoreCase(idSumi)) {
				aux = misSumis[i];
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	public int cantVinoDeTipo(String tipo) {
		int cantVinosTipo=0;
		for(int i=0;i<cantVinos;i++) {
			if(misVinos[i].getTipo().equalsIgnoreCase(tipo)) {
				cantVinosTipo+=misVinos[i].getDispReal();
			}
		}
		return cantVinosTipo;
	}
	
	public float estimarGanacias() {
		float gananciaEstimada = 0;
		for(int i=0;i<cantVinos;i++) {
			gananciaEstimada+=misVinos[i].getDispReal()*(misVinos[i].getPrecioDeVenta()-misVinos[i].getPrecioDeCompra());
		}
		return gananciaEstimada;
	}
	
	public String vinoMasRentable() {
		String nombre = misVinos[0].getName();
		float aux = misVinos[0].gananciasTotal();
		for (int i = 1; i<cantVinos; i++) {
			if(aux<misVinos[i].gananciasTotal()) {
				aux = misVinos[i].gananciasTotal();
				nombre = misVinos[i].getName();
			}
		}
		return nombre;
	}
	
	/*public Vino vinoMasRentable() {
		
		if(cantVinos==0) {
			return null;
		}
		Vino mRentable = misVinos[0];
		for(int i=1;i<cantVinos;i++) {
			if(misVinos[i].rentaActual()>mRentable.rentaActual()) {
				mRentable = misVinos[i];
			}
		}
		return mRentable;
		
	}*/
	
}
