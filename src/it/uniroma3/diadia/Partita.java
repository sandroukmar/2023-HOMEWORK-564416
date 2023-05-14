package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;


public class Partita {
	private Labirinto labirinto;
	private Giocatore giocatore;
	private Stanza stanzaCorrente;
	private boolean finita;

	
	public Partita(){
		this(new Labirinto());
	}
	
	public Partita(Labirinto labirinto) {
		this.labirinto = labirinto;
		this.giocatore = new Giocatore();
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
		this.finita = false;
	}
	
	
	void setLabirinto(Labirinto labirinto){
		this.labirinto = labirinto;
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
	
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	public boolean vinta() {
		return this.getStanzaCorrente() == this.labirinto.getStanzaVincente();
	}
	
	public boolean isFinita() {
		return this.finita || vinta() || (giocatore.getCfu() == 0);
	}
	
	public void setFinita() {
		this.finita = true;
	}
	
	public boolean giocatoreIsVivo() {
		return this.giocatore.getCfu() > 0;
	}
}
