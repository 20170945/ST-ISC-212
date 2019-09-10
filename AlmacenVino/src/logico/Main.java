package logico;

public class Main {
	
	public static void main(String args[]) {
		Almacen alma = new Almacen();
		Suministrador s1 = new Suministrador("s-1", "Juan", "Chile", 20);
		Vino v1 = new Vino("v-1","Carlos Rosi", "Tinto", 2015, 10, 20, 15, s1);
		int[] ventas = new int[10];
		for (int i = 0; i < 10; i++) {
			ventas[i] = (i)*10;
		}
		v1.setVentas(ventas);
		alma.insertarSuministrador(s1);
		alma.insertarVino(v1);
		System.out.println(alma.hacerPedido("v-1"));
	}
}
