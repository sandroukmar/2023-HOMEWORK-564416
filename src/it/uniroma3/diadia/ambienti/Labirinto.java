package it.uniroma3.diadia.ambienti;


import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;


public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public Labirinto(){
		creaStanze();
	}

	private Labirinto(LabirintoBuilder labirintoBuilder) {
		stanzaIniziale = labirintoBuilder.getStanzaIniziale();
		stanzaVincente = labirintoBuilder.getStanzaVincente();
	}

	public Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente();
	}


	private void creaStanze() {

		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);

		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");

		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
		stanzaIniziale = atrio;  
		stanzaVincente = biblioteca;
	}

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}
	
	

	public static class LabirintoBuilder {
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

		public LabirintoBuilder addCane(String nome, String presentazione) {
			Cane cane = new Cane(nome, presentazione);
			this.stanzaCorrente.setPersonaggio(cane);
			return this;
		}

		public LabirintoBuilder addCane(String nome, String presentazione, Attrezzo attrezzo) {
			Cane cane = new Cane(nome, presentazione, attrezzo);
			this.stanzaCorrente.setPersonaggio(cane);
			return this;
		}

		public LabirintoBuilder addMago(String nome, String presentazione, Attrezzo attrezzo) {
			Mago mago = new Mago(nome, presentazione, attrezzo);
			this.stanzaCorrente.setPersonaggio(mago);
			return this;
		}

		public LabirintoBuilder addStrega(String nome, String presentazione) {
			Strega strega = new Strega(nome, presentazione);
			this.stanzaCorrente.setPersonaggio(strega);
			return this;
		}

		public Labirinto getLabirinto(){
			return new Labirinto(this);
		}
	}
}
