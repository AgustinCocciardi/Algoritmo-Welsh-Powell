package wp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WelshPowell {

	private int cantidadNodos;
	private int cantidadAristas;
	private int[][] matrizAdyacencia;
	private Nodo[] nodos;
	private ArrayList<Integer> noPintados = new ArrayList<Integer>();
	
	public WelshPowell(Scanner entrada) {
		int nodo1, nodo2;
		this.cantidadNodos = entrada.nextInt();
		this.cantidadAristas = entrada.nextInt();
		this.matrizAdyacencia = new int[this.cantidadNodos][this.cantidadNodos];
		for(int[] rows : matrizAdyacencia)
			Arrays.fill(rows, Integer.MAX_VALUE);
		for(int i=0; i<this.cantidadAristas; i++) {
			nodo1 = entrada.nextInt() - 1;
			nodo2 = entrada.nextInt() - 1;
			this.matrizAdyacencia[nodo1][nodo2] = 1;
			this.matrizAdyacencia[nodo2][nodo1] = 1;
		}
		this.nodos = new Nodo[this.cantidadNodos];
		for(int i=0; i<this.cantidadNodos; i++) {
			this.nodos[i] = new Nodo(i);
			for(int j=0; j<this.cantidadNodos; j++) {
				if(i != j) {
					if(this.matrizAdyacencia[i][j] != Integer.MAX_VALUE)
						this.nodos[i].aumentarGrado();
				}
			}
		}
		Arrays.sort(nodos);
		for(int i=0; i<this.cantidadNodos; i++) {
			this.noPintados.add(this.nodos[i].getNumeroNodo());
		}
	}

	public void colorear() {
		int color, recorrido;
		boolean puedoPintar, sePinto;
		ArrayList<Integer> pintados = new ArrayList<Integer>();
		Integer actual = this.noPintados.remove(0), auxiliar;
		pintados.add(actual);
		this.nodos[0].setColor(1);
		while(this.noPintados.isEmpty() == false) {
			for(int i=0; i<this.cantidadNodos -1; i++) {
				actual = this.noPintados.get(0);
				sePinto = false;
				color = 1;
				while(sePinto == false) {
					puedoPintar = true;
					recorrido = 0;
					while(recorrido < pintados.size() && puedoPintar == true) {
						auxiliar = pintados.get(recorrido);
						if(this.matrizAdyacencia[auxiliar][actual] != Integer.MAX_VALUE
								&& this.nodos[recorrido].getColor() == color)
							puedoPintar = false;
						recorrido++;
					}
					if(puedoPintar == true) {
						pintados.add(actual);
						this.noPintados.remove(actual);
						this.nodos[i+1].setColor(color);
						sePinto = true;
					}
					else
						color++;
				}
				
			}
		}
	}
	
	public void mostrarGrafo() {
		for(int i=0; i<this.cantidadNodos; i++) {
			System.out.println("Nodo: " + (this.nodos[i].getNumeroNodo()+1) + " color: " + this.nodos[i].getColor());
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner entrada = new Scanner(new FileReader("grafo.in"));
		WelshPowell welshpowell = new WelshPowell(entrada);
		entrada.close();
		welshpowell.colorear();
		welshpowell.mostrarGrafo();
	}

}
