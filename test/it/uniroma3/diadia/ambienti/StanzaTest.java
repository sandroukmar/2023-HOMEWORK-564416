package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	private Stanza vuota;
	private Stanza stanza1;
	private Stanza stanza2;
	private Stanza stanza3;
	private Stanza stanza_ad1;
	private Stanza stanza_ad2;
	private Attrezzo spada;
	private Attrezzo scudo;

	@BeforeEach
	void setUp() {
		vuota = new Stanza("vuota");
		stanza1 = new Stanza("N11");
		stanza2 = new Stanza("N12");
		stanza3 = new Stanza("N13");
		stanza_ad1 = new Stanza("N1");
		stanza_ad2 = new Stanza("N2");
		spada = new Attrezzo("spada", 3);
		scudo = new Attrezzo("scudo", 2);
		
		stanza1.impostaStanzaAdiacente("nord", stanza_ad1);
		stanza2.impostaStanzaAdiacente("nord", stanza_ad1);
		stanza2.impostaStanzaAdiacente("est", stanza_ad2);
		stanza3.impostaStanzaAdiacente("nord", stanza_ad1);
		stanza3.impostaStanzaAdiacente("nord", stanza_ad2);

		stanza1.addAttrezzo(spada);
		stanza2.addAttrezzo(spada);
		stanza2.addAttrezzo(scudo);
		stanza3.addAttrezzo(spada);
		stanza3.addAttrezzo(spada);
	}

	@Test
	void testImpostaStanzaAdiacenteSingola() {
		assertEquals("N1", stanza1.getStanzaAdiacente("nord").getNome());
	}
	@Test
	void testImpostaStanzaAdiacenteInDueDirezioni() {
		assertEquals("N1", stanza2.getStanzaAdiacente("nord").getNome());
		assertEquals("N2", stanza2.getStanzaAdiacente("est").getNome());
	}
	@Test
	void testImpostaStanzaAdiacenteSovrascrivendo() {
		assertEquals("N2", stanza3.getStanzaAdiacente("nord").getNome());
	}

	@Test
	void testAddAttrezzoSingolo() {
		assertEquals(spada, stanza1.getAttrezzi()[0]);
	}
	@Test
	void testAddAttrezzoDueVolte() {
		assertEquals(spada, stanza2.getAttrezzi()[0]);
		assertEquals(scudo, stanza2.getAttrezzi()[1]);
	}
	@Test
	void testAddAttrezzoDueVolteLoStesso() {
		assertEquals(spada, stanza3.getAttrezzi()[0]);
		assertEquals(spada, stanza3.getAttrezzi()[1]);
	}

	@Test
	void testHasAttrezzoVuota() {
		assertFalse(vuota.hasAttrezzo("spada"));
	}
	@Test
	void testHasAttrezzoUnico() {
		assertTrue(stanza1.hasAttrezzo("spada"));
	}
	@Test
	void testHasAttrezzoInPrimaPosizione() {
		assertTrue(stanza2.hasAttrezzo("spada"));
	}
	@Test
	void testHasAttrezzoInUltimaPosizione() {
		assertTrue(stanza2.hasAttrezzo("scudo"));
	}

	@Test
	void testRemoveAttrezzoSingolo() {
		assertTrue(stanza1.removeAttrezzo("spada"));
		assertNull(stanza1.getAttrezzo("spada"));
	}
	@Test
	void testRemoveAttrezzoCheNonCE() {
		assertFalse(stanza1.removeAttrezzo("scudo"));
	}
	@Test
	void testRemoveAttrezzoDaStanzaVuota() {
		assertFalse(vuota.removeAttrezzo("spada"));
	}
}
