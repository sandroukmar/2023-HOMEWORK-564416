package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;


class StanzaMagicaTest {
	private StanzaMagica stanzaMagica;
	private Attrezzo spada;
	private Attrezzo scudo;

	
	@BeforeEach
	void setUp() {
		stanzaMagica = new StanzaMagica("Stanza Magica", 1);
		spada = new Attrezzo("Spada", 3);
		scudo = new Attrezzo("Scudo", 2);
		stanzaMagica.addAttrezzo(spada);
	}
	
	
	@Test
	void testAddAttrezzoNonInverte() {
		assertTrue(stanzaMagica.hasAttrezzo("Spada"));
		assertEquals(spada, stanzaMagica.getAttrezzo("Spada"));
	}
	@Test
	void testAddAttrezzoInverte() {
		stanzaMagica.addAttrezzo(scudo);
		assertTrue(stanzaMagica.hasAttrezzo("oducS"));
		assertEquals(4, stanzaMagica.getAttrezzo("oducS").getPeso());
	}
}
