package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {
	private IO io;
	private Partita partita;
	private Comando comandoPosa;
	private Attrezzo scudo;

	@BeforeEach
	void setUp() {
		io = new IOConsole(new Scanner(System.in));
		partita = new Partita();
		comandoPosa = new ComandoPosa();
		comandoPosa.setIO(io);
		scudo = new Attrezzo("scudo", 2);
		partita.getGiocatore().getBorsa().addAttrezzo(scudo);
	}

	@Test
	void testEseguiPosaAttrezzoPresente() {
		comandoPosa.setParametro("scudo");
		comandoPosa.esegui(partita);
		assertEquals(scudo, partita.getStanzaCorrente().getAttrezzo("scudo"));
		assertNull(partita.getGiocatore().getBorsa().getAttrezzo("scudo"));
	}
	@Test
	void testEseguiPosaAttrezzoAssente() {
		comandoPosa.setParametro("spada");
		comandoPosa.esegui(partita);
		assertNull(partita.getStanzaCorrente().getAttrezzo("spada"));
	}
}
