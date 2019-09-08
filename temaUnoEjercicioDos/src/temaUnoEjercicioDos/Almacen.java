package temaUnoEjercicioDos;

import java.time.Year;
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
	
	//funcion temporal
	private void pedirASuministrador(Vino vino, int cantidad) {
		Suministrador suministrador = vino.getSuministrador();
		System.out.print("Se ha enviado el mensaje al pedido que detalla" + cantidad + " botella");
		if(cantidad > 1) {
			System.out.print("s");
		}
		System.out.print(" del vino \"" + vino.getNombre() +"\" al suministrador " + suministrador.getNombre() + ", localizado en "+ suministrador.getPais() +".");
	}
		
	public void verificarYPedirVinos() {
		if(this.vinos.size()==0) {
			System.out.println("No hay vinos.");
		}
		int cantVinoPedidos;
		for(Vino vino:this.vinos) {
			if(vino.verificarSiDebePedir()) {
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
		int[] arreglo = {7,1,2,3,4,5,6,7,8,9}; 
		Vino nuevoVino = new Vino("Bonita Uva", "Uvas", 1999, "tinto", 20, 5, 2, arreglo, nuevoSuministrador);
		deLaGiraldilla.vinos.add(nuevoVino);
		deLaGiraldilla.verificarYPedirVinos();
	}
}
