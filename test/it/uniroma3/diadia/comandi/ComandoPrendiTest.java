package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPrendiTest {
	private IO io;
	private Partita partita;
	private Comando comandoPrendi;
	private Attrezzo spada;
	private Stanza stanza;

	@BeforeEach
	void setUp() {
		io = new IOConsole(new Scanner(System.in));
		partita = new Partita();
		comandoPrendi = new ComandoPrendi();
		comandoPrendi.setIO(io);
		spada = new Attrezzo("spada", 3);
		stanza = new Stanza("stanza");
		stanza.addAttrezzo(spada);
		partita.setStanzaCorrente(stanza);
	}

	@Test
	void testEseguiPrendiAttrezzoPresente() {
		comandoPrendi.setParametro("spada");
		comandoPrendi.esegui(partita);
		assertEquals(spada, partita.getGiocatore().getBorsa().getAttrezzo("spada"));
		assertNull(partita.getStanzaCorrente().getAttrezzo("spada"));
	}
	@Test
	void testEseguiPrendiAttrezzoAssente() {
		comandoPrendi.setParametro("scudo");
		comandoPrendi.esegui(partita);
		assertNull(partita.getGiocatore().getBorsa().getAttrezzo("scudo"));
	}
}
