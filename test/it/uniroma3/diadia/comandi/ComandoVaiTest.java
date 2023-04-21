package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComandoVaiTest {
	private IO io;
	private Partita partita;
	private Comando comandoVai;
	private Stanza stanzaIniziale;
	private Stanza stanza2;
	
	
	@BeforeEach
	void setUp() {
		io = new IOConsole();
		partita = new Partita();
		comandoVai = new ComandoVai(io);
		stanzaIniziale = new Stanza("Stanza Iniziale");
		stanza2 = new Stanza("Stanza 2");
		partita.setStanzaCorrente(stanzaIniziale);
		partita.getStanzaCorrente().impostaStanzaAdiacente("nord", stanza2);
		partita.getStanzaCorrente().impostaStanzaAdiacente("est", null);
		partita.getStanzaCorrente().impostaStanzaAdiacente("sud", null);
		partita.getStanzaCorrente().impostaStanzaAdiacente("ovest", null);
	}

	@Test
	void testEseguiVaiStanzaInStanzaPresente() {
		comandoVai.setParametro("nord");
		comandoVai.esegui(partita);
		assertEquals(stanza2, partita.getStanzaCorrente());
	}
	@Test
	void testEseguiVaiStanzaInStanzaAssente() {
		comandoVai.setParametro("est");
		comandoVai.esegui(partita);
		assertEquals(stanzaIniziale, partita.getStanzaCorrente());
	}
}
