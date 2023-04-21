package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

class PartitaTest {
	private Partita partita;
	private Labirinto labirinto;

	@BeforeEach
	void setUp(){
		partita = new Partita();
		labirinto = partita.getLabirinto();
	}

	@Test
	void testVintaFalso() {
		assertFalse(partita.vinta());
	}
	@Test
	void testVinta() {
		partita.setStanzaCorrente(labirinto.getStanzaVincente());
		assertTrue(partita.vinta());
	}

	@Test
	void testIsFinitaNonFinita() {
		assertFalse(partita.isFinita());
	}
	@Test
	void testIsFinitaVinta() {
		partita.setStanzaCorrente(labirinto.getStanzaVincente());
		assertTrue(partita.isFinita());
	}
	@Test
	void testIsFinitaEFinita() {
		partita.setFinita();
		assertTrue(partita.isFinita());
	}
	@Test
	void testIsFinitaZeroCFU() {
		partita.getGiocatore().setCfu(0);
		assertTrue(partita.isFinita());
	}
}