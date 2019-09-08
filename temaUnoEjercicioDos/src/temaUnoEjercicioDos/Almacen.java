package temaUnoEjercicioDos;

import java.util.ArrayList;

public class Almacen {
	//Atributos
	private ArrayList<Vino> vinos;
	private ArrayList<Suministrador> suministradores;
	//get y set
	public ArrayList<Vino> getVinos() {
		return vinos;
	}

	public void setVinos(ArrayList<Vino> vinos) {
		this.vinos = vinos;
	}

	public ArrayList<Suministrador> getSuministradores() {
		return suministradores;
	}

	public void setSuministradores(ArrayList<Suministrador> suministradores) {
		this.suministradores = suministradores;
	}
	
	//Constructores
	public Almacen() {
		this.vinos = new ArrayList<Vino>();
		this.suministradores = new ArrayList<Suministrador>();
	}
	
	public Almacen(ArrayList<Vino> vinos, ArrayList<Suministrador> suministradores) {
		this.vinos = vinos;
		this.suministradores = suministradores;
	}
	
	//Operaciones
	
	private int promedioRango(int array[], int desde, int hasta) {
		int promedio = 0;
		for(int i=desde;i<hasta;i++) {
			promedio+=array[i];
		}
		return promedio/3;
	}
	
	public boolean verificarSiDebePedir(Vino vino) {
		try {
			vino.getVentas().toString();
			vino.getSuministrador().getNombre();
		} catch (Exception e) {
			System.out.println("Error: No fue declarada la venta o el suministrador.");
			return false;
		}
		if (vino.getVentas()[0] > promedioRango(vino.getVentas(), 1, 4) && vino.getSuministrador().getTiempo() < 30 && vino.getDisponibilidadReal() < vino.getDisponibilidadMin()) {
			return true;
		}
		return false;
	}
	
	//funcion temporal
	private void pedirASuministrador(Vino vino, int cantidad) {
		Suministrador suministrador = vino.getSuministrador();
		System.out.print("Se ha enviado el mensaje que contiene el pedido de " + cantidad + " botella");
		if(cantidad > 1) {
			System.out.print("s");
		}
		System.out.println(" del vino \"" + vino.getNombre() +"\" al suministrador " + suministrador.getNombre() + ", localizado en "+ suministrador.getPais() +".");
	}
		
	public void verificarYPedirVinos() {
		if(this.vinos.size()==0) {
			System.out.println("No hay vinos.");
		}
		int cantVinoPedidos;
		for(Vino vino:this.vinos) {
			if(verificarSiDebePedir(vino)) {
				cantVinoPedidos = vino.getDisponibilidadMax() - vino.getDisponibilidadReal();
				if(cantVinoPedidos == 0) {
					continue;
				}
				pedirASuministrador(vino, cantVinoPedidos);
			}
		}
	}
	
	public static void main(String[] args) {
		Almacen deLaGiraldilla = new Almacen();
		Suministrador nuevoSuministrador = new Suministrador("Zhenpeng","China",20);
		deLaGiraldilla.suministradores.add(nuevoSuministrador);
 		int[] arreglo = {7,1,2,3,4,5,6,7,8,9}; 
		Vino nuevoVino = new Vino("Bonita Uva", "Uvas", 1999, "tinto", 20, 5, 2, arreglo, nuevoSuministrador);
		deLaGiraldilla.vinos.add(nuevoVino);
		nuevoSuministrador = new Suministrador("Juan","México", 31);
		deLaGiraldilla.suministradores.add(nuevoSuministrador);
		nuevoVino = new Vino("Muy Bueno","Manzanas",2010,"blanco",20,5,2,arreglo,nuevoSuministrador);
		deLaGiraldilla.vinos.add(nuevoVino);
		deLaGiraldilla.verificarYPedirVinos();
		for(Suministrador suministrador:deLaGiraldilla.suministradores) {
			System.out.println(suministrador.getNombre()+":"+suministrador.getPais()+":"+suministrador.getTiempo());
		}
	}
}
