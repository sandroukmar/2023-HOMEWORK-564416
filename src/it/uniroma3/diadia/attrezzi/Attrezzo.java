package it.uniroma3.diadia.attrezzi;


public class Attrezzo {
	private String nome;
	private int peso;

	
	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}

	
	public String getNome() {
		return this.nome;
	}
	
	public int getPeso() {
		return this.peso;
	}
	
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	@Override
	public boolean equals(Object obj) {
		Attrezzo other = (Attrezzo) obj;
		return this.nome.equals(other.getNome()) && this.peso == other.getPeso();
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode()+this.peso;
	}
	
	@Override
	public String toString() {
		return this.getNome()+" ("+this.getPeso()+"kg)";
	}
}