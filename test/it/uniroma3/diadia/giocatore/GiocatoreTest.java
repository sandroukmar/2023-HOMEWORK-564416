package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GiocatoreTest {
	private Giocatore giocatore;
	private Borsa borsa;

	@BeforeEach
	void setUp(){
		giocatore = new Giocatore();
		borsa = new Borsa();
	}

	@Test
	void testGetCfuIniziali() {
		assertEquals(20, giocatore.getCfu());
	}
	@Test
	void testGetCfuConSet() {
		giocatore.setCfu(5);
		assertEquals(5, giocatore.getCfu());
	}

	@Test
	void testGetBorsa() {
		giocatore.setBorsa(borsa);
		assertEquals(borsa, giocatore.getBorsa());
	}

}
