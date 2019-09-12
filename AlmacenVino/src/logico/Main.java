package logico;

public class Main {
	public static void main(String args[]) {
		Almacen alma = new Almacen();
		Suministrador s1 = new Suministrador("s-1", "Juan", "Chile", 20);
		Vino v1 = new Vino("v-1","Carlos Rosi", "Tinto", 2015, 10, 20, 15, s1, 10, 12);
		int[] ventas = new int[10];
		for (int i = 0; i < 10; i++) {
			ventas[i] = (i)*10;
		}
		Vino v2 = new Vino("v-2","No es un ron", "Blanco", 2015, 10, 20, 15, s1, 1, 11);
		v1.setVentas(ventas);
		v2.setVentas(ventas);
		alma.insertarSuministrador(s1);
		alma.insertarVino(v1);
		alma.insertarVino(v2);
		System.out.println(alma.hacerPedido("v-1"));
		System.out.println(alma.cantVinoDeTipo("tinto"));
		System.out.println(alma.estimarGanacias());
		System.out.println(alma.vinoMasRentable());
	}
}
