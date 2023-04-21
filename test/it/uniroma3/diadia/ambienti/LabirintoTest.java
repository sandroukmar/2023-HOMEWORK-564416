package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class LabirintoTest {
	private Labirinto labirinto;

	@BeforeEach
	void setUp() throws Exception {
		labirinto = new Labirinto();
	}

	@Test
	void testGetStanzaInizialeTrue() {
		assertEquals("Atrio", labirinto.getStanzaIniziale().getNome());
	}
	@Test
	void testGetStanzaInizialeFalse() {
		assertFalse("Aula N10".equals(labirinto.getStanzaIniziale().getNome()));
	}

	@Test
	void testGetStanzaVincenteTrue() {
		assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
	}
	@Test
	void testGetStanzaVincenteFalse() {
		assertFalse("Aula N10".equals(labirinto.getStanzaVincente().getNome()));
	}

}
