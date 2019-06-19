package wp;

public class Nodo implements Comparable<Nodo> {
	
	private int numeroNodo;
	private int color;
	private int grado;
	
	public Nodo(int numeroNodo) {
		this.numeroNodo = numeroNodo;
		this.color = 0;
		this.grado = 0;
	}
	
	public int getNumeroNodo() {
		return this.numeroNodo;
	}
	
	public int getColor() {
		return this.color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public void aumentarGrado() {
		this.grado++; 
	}

	@Override
	public int compareTo(Nodo nodo) {
		return nodo.grado - this.grado;
	}
}
