package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private Stanza stanzaCorrente;
	private Map<String, Stanza> stanze;
	
	
	public LabirintoBuilder() {
		stanze = new HashMap<String, Stanza>();
	}
	
	
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public Map<String, Stanza> getStanze() {
		return this.stanze;
	}

	public LabirintoBuilder addStanzaIniziale(String nomeStanzaIniziale) {
		if(this.stanzaVincente != null && nomeStanzaIniziale == this.stanzaVincente.getNome()) {
			this.stanzaIniziale = this.stanzaVincente;
		}
		else {
			this.stanzaIniziale = new Stanza(nomeStanzaIniziale);
			this.stanze.put(nomeStanzaIniziale, this.stanzaIniziale);
			this.stanzaCorrente = this.stanzaIniziale;
		}
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nomeStanzaVincente) {
		if(this.stanzaIniziale != null && nomeStanzaVincente == this.stanzaIniziale.getNome()) {
			this.stanzaVincente = this.stanzaIniziale;
		}
		else {
			this.stanzaVincente = new Stanza(nomeStanzaVincente);
			this.stanze.put(nomeStanzaVincente, this.stanzaVincente);
			this.stanzaCorrente = this.stanzaVincente;
		}
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String nomeStanza, String nomeStanzaAdiacente, String direzione) {
		Stanza stanza = this.stanze.get(nomeStanza);
		Stanza stanzaAdiacente = this.stanze.get(nomeStanzaAdiacente);
		stanza.impostaStanzaAdiacente(direzione, stanzaAdiacente);
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		Attrezzo attrezzo = new Attrezzo(nome, peso);
		this.stanzaCorrente.addAttrezzo(attrezzo);
		return this;
	}
	
	public LabirintoBuilder addStanza(String nome) {
		Stanza stanza = new Stanza(nome);
		this.stanze.put(nome, stanza);
		this.stanzaCorrente = stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nome, int sogliaMagica) {
		StanzaMagica stanzaMagica = new StanzaMagica(nome, sogliaMagica);
		this.stanze.put(nome, stanzaMagica);
		this.stanzaCorrente = stanzaMagica;
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nome, String direzioneBloccata, String attrezzoSblocco) {
		StanzaBloccata stanzaBloccata = new StanzaBloccata(nome, attrezzoSblocco, direzioneBloccata);
		this.stanze.put(nome, stanzaBloccata);
		this.stanzaCorrente = stanzaBloccata;
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nome, String attrezzoLuminoso) {
		StanzaBuia stanzaBuia = new StanzaBuia(nome, attrezzoLuminoso);
		this.stanze.put(nome, stanzaBuia);
		this.stanzaCorrente = stanzaBuia;
		return this;
	}
	
	public Labirinto getLabirinto(){
		return new Labirinto(this);
	}
}
