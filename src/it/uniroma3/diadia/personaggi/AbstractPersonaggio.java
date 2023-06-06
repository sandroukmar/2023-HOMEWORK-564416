package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {
	private String nome;
	private String presentazione;
	private boolean haSalutato;
	
	public AbstractPersonaggio(String nome, String presentazione) {
		this.nome = nome;
		this.presentazione = presentazione;
		this.haSalutato = false;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getPresentazione() {
		return this.presentazione;
	}
	
	public boolean haSalutato() {
		return this.haSalutato;
	}
	
	public String saluta() {
		StringBuilder risposta = new StringBuilder("Caio, io sono ");
		risposta.append(this.getNome()+". ");
		if(!this.haSalutato()) {
			risposta.append(this.getPresentazione());
			this.haSalutato = true;
		}
		else {
			risposta.append("Ci siamo già presentati");
		}
		return risposta.toString();
	}
	
	abstract public String agisci(Partita partita);
	
	abstract public String riceviRegalo(Attrezzo attrezzo, Partita partita);
	
	@Override
	public String toString() {
		return this.getNome();
	}
}
