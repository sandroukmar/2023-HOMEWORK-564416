package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	private Stanza stanzaMagica;
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
		assertEquals("Spada", stanzaMagica.getAttrezzi()[0].getNome());
		assertEquals(3, stanzaMagica.getAttrezzi()[0].getPeso());
	}
	@Test
	void testAddAttrezzoInverte() {
		stanzaMagica.addAttrezzo(scudo);
		assertEquals("oducS", stanzaMagica.getAttrezzi()[1].getNome());
		assertEquals(4, stanzaMagica.getAttrezzi()[1].getPeso());
	}
}
